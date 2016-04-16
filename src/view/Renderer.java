package view;

import model.InitialConditions;
import model.Model;
import model.ModelDebug;
import model.Particle;
import processing.core.PApplet;
import processing.core.PFont;

public class Renderer
{
    /** Colors */
    public static final float WHITE = 1f;
    public static final float VERY_DARK_GRAY = 0.15f;
    public static final float BLACK = 0f;

    public static final int DISPLAY_SIZE = 1000;
    public static final int RENDERING_SCALE = DISPLAY_SIZE / InitialConditions.BOX_SIZE;

    public static final float PARTICLE_RENDERING_DIAMETER = 20;

    public static final String FONT = "Menlo";
    public static final int INITIAL_FONT_SIZE = 20; /** No idea why Processing requires this */
    public static final int MAJOR_FONT_SIZE = 32;
    public static final int MINOR_FONT_SIZE = 22;

    private PApplet pApplet;
    private DebugInfo debugInfo;

    PFont font;

    public Renderer(PApplet processingApplet)
    {
        pApplet = processingApplet;

        pApplet.size(DISPLAY_SIZE, DISPLAY_SIZE, PApplet.P2D);
    }

    public void setup()
    {
        pApplet.colorMode(PApplet.RGB, 1.0f);
        pApplet.ellipseMode(PApplet.CENTER);

        font = pApplet.createFont(FONT, INITIAL_FONT_SIZE);
        pApplet.textFont(font);

        debugInfo = new DebugInfo(pApplet, font);
    }

    public void draw()
    {
        pApplet.background(VERY_DARK_GRAY);

        displayParticles();

        debugInfo.display();
    }

    private void displayParticles()
    {
        pApplet.strokeWeight(1);
        pApplet.stroke(BLACK);
        Model.particles.forEach((particle) ->
        {
            float r = 0, g = 0f, b = 0.2f;
            r = ModelDebug.instance.translateTo01Scale(particle.massDensity);
            pApplet.fill(r, g, b);

            drawParticles(particle);
        });
    }

    private void drawParticles(Particle particle)
    {
        float x = (float)particle.positionX * RENDERING_SCALE;
        float y = (float)particle.positionY * RENDERING_SCALE;
        pApplet.ellipse(x, y,
                        PARTICLE_RENDERING_DIAMETER,
                        PARTICLE_RENDERING_DIAMETER);
    }
}
