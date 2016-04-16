package model.fluid.smoothing_kernels;

import model.MathUtils;
import model.Particle;
import model.fluid.SphSimulator;

/**
 * Viscosity kernel
 */
public class ViscosityKernel
{
    private static final double PRECALCULATED_KERNEL_PART =
            45 / (Math.PI * Math.pow(SphSimulator.KERNEL_SIZE, 6));

    /** kelager.06 formulae (4.22) */
    public double getLaplacian(Particle p1, Particle p2)
    {
        double len = MathUtils.getLength(p1, p2);

        if (len * len > SphSimulator.KERNEL_SIZE_SQ)
            return 0;

        return PRECALCULATED_KERNEL_PART * (SphSimulator.KERNEL_SIZE - len);
    }
}
