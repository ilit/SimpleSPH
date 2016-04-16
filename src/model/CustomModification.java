package model;

public class CustomModification
{
    public static void exec()
    {
        Model.particles.stream()
        .filter
        (
            particle ->
            particle.positionX > InitialConditions.BOX_SIZE * 0.7 &&
            particle.positionX < InitialConditions.BOX_SIZE * 1.0 &&
            particle.positionY > InitialConditions.BOX_SIZE * 0.3 &&
            particle.positionY < InitialConditions.BOX_SIZE * 0.7
        )
        .forEach
        (
            particle -> particle.leapedVelocityY -= 1E-3
        );
    }
}
