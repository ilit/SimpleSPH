package model;

public class MathUtils
{
    public static double getLength(Particle p1, Particle p2)
    {
        return (float)Math.sqrt(getLengthSquared(p1, p2));
    }

    public static double getLengthSquared(Particle p1, Particle p2)
    {
        double xDiff = p1.positionX - p2.positionX;
        double yDiff = p1.positionY - p2.positionY;

        return xDiff * xDiff + yDiff * yDiff;
    }
}
