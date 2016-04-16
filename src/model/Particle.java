package model;

import model.fluid.SphSimulator;

public class Particle
{
    public double positionX, positionY;
    public double leapedVelocityX, leapedVelocityY;
    public double accelerationX, accelerationY;

    public double massDensity;
    public final double mass = SphSimulator.MASS;
    public double pressure;

    public Particle(float x, float y)
    {
        this.positionX = x;
        this.positionY = y;
    }

    public void updatePressure()
    {
        /** kelager.06 formulae (4.8) */
        pressure = SphSimulator.STIFFNESS_CONSTANT * (massDensity - SphSimulator.REST_DENSITY);
    }

    public void resetAcceleration()
    {
        accelerationX = 0;
        accelerationY = 0;
    }
}
