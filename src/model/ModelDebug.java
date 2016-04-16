package model;

public class ModelDebug
{
    public static final ModelDebug instance = new ModelDebug();

    private double minMassDens;
    private double maxMassDens;
    private double sumMassDens;

    public void resetStoredValues()
    {
        minMassDens = Double.MAX_VALUE;
        maxMassDens = 0;
        sumMassDens = 0;
    }

    public void updateWithParticle(Particle particle)
    {
        minMassDens = Math.min(minMassDens, particle.massDensity);
        maxMassDens = Math.max(maxMassDens, particle.massDensity);
        sumMassDens += particle.massDensity;

        if (particle.massDensity < 0)
            throw new Error("massDensity < 0");
    }

    public float translateTo01Scale(double massDensity)
    {
        double trimmed = massDensity - minMassDens;
        double maxMinDiff = maxMassDens - minMassDens;
        double scaled = trimmed / maxMinDiff;

        return (float) scaled;
    }

    public double getMinMassDens()
    {
        return minMassDens;
    }

    public double getMaxMassDens()
    {
        return maxMassDens;
    }

    public double getAvgMassDens() /** Average */
    {
        return sumMassDens / InitialConditions.INITIAL_PARTICLES_AMOUNT;
    }
}
