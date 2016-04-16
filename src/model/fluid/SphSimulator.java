package model.fluid;

import model.ModelDebug;
import model.Particle;
import model.fluid.smoothing_kernels.Polynomial6Kernel;
import model.fluid.smoothing_kernels.SpikyKernel;
import model.fluid.smoothing_kernels.ViscosityKernel;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.List;

public class SphSimulator
{
    /** Some values are from kelager.06 Table (5.4) */
    public static final double KERNEL_SIZE = 1.1;
    public static final double STIFFNESS_CONSTANT = 4;
    public static final double MASS = 0.005;
    public static final double REST_DENSITY = 0;
    private static final double MU = 0.5; /** Viscosity coefficient */
    public static final double TIMESTEP = 0.08;
    public static final double KERNEL_SIZE_SQ = KERNEL_SIZE * KERNEL_SIZE;
    private static final double MINIMUM_MASS_DENSITY = 1E-6;

    private final List<Particle> particles;
    private final Polynomial6Kernel densityKernel = new Polynomial6Kernel();
    private final SpikyKernel pressureKernel = new SpikyKernel();
    private final ViscosityKernel viscosityKernel = new ViscosityKernel();
    private final LeapfrogIntegrator integrator = new LeapfrogIntegrator();

    public SphSimulator(List<Particle> particles)
    {
        this.particles = particles;
    }

    public void executeStep()
    {
        calculatePressureAndDensities(); /** SPH */

        /** Leapfrog integration step 1: update position */
        /**  x = x - v * ?t  */
        particles.forEach(integrator::updatePosition);

        /** Leapfrog integration step 2: update acceleration */
        /**  a = F(x)  */
        particles.forEach(Particle::resetAcceleration);
        updateAccelerations();

        /** Leapfrog integration step 3: update next velocity */
        /**  v = v + a * ?t  */
        particles.forEach(integrator::updateLeapedVelocity);
    }

    private void calculatePressureAndDensities()
    {
        ModelDebug.instance.resetStoredValues();

        for (Particle particle : particles)
        {
            particle.massDensity = 0;
            for (Particle neighbour : particles)
            {
                if (neighbour.equals(particle))
                    continue; /** Skip the particle itself */

                /** kelager.06 Formulae (4.6) */
                particle.massDensity += particle.mass * densityKernel.calculate(particle, neighbour);
            }

            /** Setup minimum mass-density to avoid simulation explosions */
            if (particle.massDensity < MINIMUM_MASS_DENSITY)
                particle.massDensity = MINIMUM_MASS_DENSITY;

            particle.updatePressure();

            ModelDebug.instance.updateWithParticle(particle);
        }
    }

    private void updateAccelerations()
    {
        for (Particle particle : particles)
        {
            for (Particle neighbour : particles)
            {
                if (neighbour.equals(particle))
                    continue; /** Skip the particle itself */

                /**
                 * Pressure
                 * kelager.06 Formulae (4.6)
                 */
                Vector2D spikyGradient = pressureKernel.getGradient(particle, neighbour);
                if (spikyGradient.getX() == 0 && spikyGradient.getY() == 0)
                    continue;

                double materialPart = ((particle.pressure + neighbour.pressure) / 2) *
                                             (neighbour.mass / neighbour.massDensity);
                particle.accelerationX += - materialPart * spikyGradient.getX();
                particle.accelerationY += - materialPart * spikyGradient.getY();

                /**
                 * Viscosity
                 * kelager.06 formulae (4.17)
                 */
                double viscousLaplacian = viscosityKernel.getLaplacian(particle, neighbour);
                double commonPart = MU * viscousLaplacian * neighbour.mass / neighbour.massDensity;
                particle.accelerationX += commonPart * (neighbour.leapedVelocityX - particle.leapedVelocityX);
                particle.accelerationY += commonPart * (neighbour.leapedVelocityY - particle.leapedVelocityY);
            }
        }
    }
}
