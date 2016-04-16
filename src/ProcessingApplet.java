import model.CustomModification;
import model.Model;
import processing.core.PApplet;
import view.Renderer;

public class ProcessingApplet extends PApplet
{
    /** Also affects simulation execution steps per second */
    private static final int MAX_FPS = 60;

    Renderer renderer;

    /** Executed first by processing */
    public void settings()
    {
        renderer = new Renderer(this);
    }

    /** Executed second by processing */
    public void setup()
    {
        frameRate(MAX_FPS);

        renderer.setup();

        Model.applet = this;
        Model.initialConditions.setup();
    }

    public void draw()
    {
        Model.simulator.executeStep();
        renderer.draw();

        CustomModification.exec();
    }
}
