package model;

import model.fluid.SphSimulator;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Model
{
    public static PApplet applet;
    public static final List<Particle> particles = new ArrayList<>();
    public static final InitialConditions initialConditions = new InitialConditions(particles);
    public static final SphSimulator simulator = new SphSimulator(particles);
}
