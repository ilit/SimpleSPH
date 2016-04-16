package model.fluid.smoothing_kernels;

import model.MathUtils;
import model.Particle;
import model.fluid.SphSimulator;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Spiky kernel by Desbrun et al.
 */
public class SpikyKernel
{
    private static final double PRECALCULATED_KERNEL_PART =
            -45 / (Math.PI * Math.pow(SphSimulator.KERNEL_SIZE, 6));

    /** kelager.06 formulae (4.14) */
    public Vector2D getGradient(Particle particle1, Particle particle2)
    {
        double xDiff = particle1.positionX - particle2.positionX;
        double yDiff = particle1.positionY - particle2.positionY;

        double len = MathUtils.getLength(particle1, particle2);

        if (len > SphSimulator.KERNEL_SIZE)
        {
            return new Vector2D(0, 0);
        }

        double f = PRECALCULATED_KERNEL_PART *
                          (SphSimulator.KERNEL_SIZE - len) * (SphSimulator.KERNEL_SIZE - len)
                          / len;
        return new Vector2D(xDiff * f, yDiff * f);
    }
}
