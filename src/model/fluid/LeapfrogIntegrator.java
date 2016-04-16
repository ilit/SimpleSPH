package model.fluid;

import model.InitialConditions;
import model.Particle;

import java.util.Random;

class LeapfrogIntegrator
{
    private static final int LEFT_THRESHOLD = 0;
    private static final int RIGHT_THRESHOLD = InitialConditions.BOX_SIZE;
    private static final double BOUNCE_K = 0.03f;

    public void updateLeapedVelocity(Particle particle)
    {
        particle.leapedVelocityX = particle.leapedVelocityX + particle.accelerationX * SphSimulator.TIMESTEP;
        particle.leapedVelocityY = particle.leapedVelocityY + particle.accelerationY * SphSimulator.TIMESTEP;
    }

    public void updatePosition(Particle particle)
    {
        particle.positionX += particle.leapedVelocityX;
        particle.positionY += particle.leapedVelocityY;

        borderConditions(particle);
    }

    private void borderConditions(Particle particle)
    {
        if (particle.positionX < LEFT_THRESHOLD)
        {
            particle.positionX = LEFT_THRESHOLD + bounceRandom();
            particle.leapedVelocityX = 0;
            particle.accelerationX = 0;
        }
        if (particle.positionY < LEFT_THRESHOLD)
        {
            particle.positionY = LEFT_THRESHOLD + bounceRandom();
            particle.leapedVelocityY = 0;
            particle.accelerationY = 0;
        }

        if (particle.positionX > RIGHT_THRESHOLD)
        {
            particle.positionX = RIGHT_THRESHOLD + bounceRandom();
            particle.leapedVelocityX = 0;
            particle.accelerationX = 0;
        }
        if (particle.positionY > RIGHT_THRESHOLD)
        {
            particle.positionY = RIGHT_THRESHOLD + bounceRandom();
            particle.leapedVelocityY = 0;
            particle.accelerationY = 0;
        }
    }

    private final Random randomGenerator = new Random();
    /** To avoid particles stuck into straight line border */
    private double bounceRandom()
    {
        return randomGenerator.nextFloat() * BOUNCE_K;
    }
}
