package model.fluid.smoothing_kernels;

import model.MathUtils;
import model.Particle;
import model.fluid.SphSimulator;

/**
 * 6th degree polynomial kernel
 */
public class Polynomial6Kernel
{
    private static final double PRECALCULATED_KERNEL_PART =
            315 / (64 * Math.PI * Math.pow(SphSimulator.KERNEL_SIZE, 9));

    /** kelager.06 formulae (4.3) */
    public double calculate(Particle p1, Particle p2)
    {
        double lengthSquared = MathUtils.getLengthSquared(p1, p2);

        if (lengthSquared > SphSimulator.KERNEL_SIZE_SQ)
            return 0;

        double squaresDiff = SphSimulator.KERNEL_SIZE_SQ - lengthSquared;

        return PRECALCULATED_KERNEL_PART * squaresDiff * squaresDiff * squaresDiff;
    }
}
