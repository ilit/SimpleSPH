package model;

import java.util.List;
import java.util.Random;

public class InitialConditions
{
    public static final int INITIAL_PARTICLES_AMOUNT = 350;
    public static final int BOX_SIZE = 8;

    private final List<Particle> particles;

    private final Random randomGenerator = new Random();

    public InitialConditions(List<Particle> particles)
    {
        this.particles = particles;
    }

    public void setup()
    {
        createRandomInitialParticles();

        //createCustom();
    }

    private void createCustom()
    {
        particles.add(new Particle(0.98f, 0.98f));
        particles.add(new Particle(1f, 1f));
    }

    private void createRandomInitialParticles()
    {
        for (int i = 0; i < INITIAL_PARTICLES_AMOUNT; i++)
        {
            particles.add
            (
                 new Particle
                 (
                     getRandomCoordinate(),
                     getRandomCoordinate()
                 )
            );
        }
    }

    private float getRandomCoordinate()
    {
        return 1 + randomGenerator.nextInt(BOX_SIZE - 2) + randomGenerator.nextFloat();
    }
}
