package view;

import model.ModelDebug;
import model.fluid.SphSimulator;
import processing.core.PApplet;
import processing.core.PFont;

public class DebugInfo
{
    private static final int DEBUG_ITEM_X_POS = Renderer.RENDERING_SCALE / 2;
    private static final int DEBUG_START_Y = Renderer.RENDERING_SCALE / 2;
    private static final int GAP = Renderer.RENDERING_SCALE / 3; /** Between info lines */
    public static final float ANNOTATION_X_OFFSET = 5f;

    private final PApplet pApplet;
    private final PFont font;
    private int debugDisplayLine;

    public DebugInfo(PApplet pApplet, PFont font)
    {
        this.pApplet = pApplet;
        this.font = font;
    }

    public void display()
    {
        pApplet.stroke(Renderer.WHITE); /** Line color */
        pApplet.fill(Renderer.WHITE); /** Font color */
        pApplet.fill(.6f, 1.0f, .8f); /** Font color */
        pApplet.strokeWeight(3); /** Line weight */

        debugDisplayLine = 0;

        pApplet.textFont(font, Renderer.MAJOR_FONT_SIZE);
        displayFramerate();

        pApplet.textFont(font, Renderer.MINOR_FONT_SIZE); /** For annotations */

        displaySizeOneLine();
        displayText("Sample");

        displayText("");
        displayPolyKernelSize();
        displayText("Density kernel radius");

        displayText("");
        displaySpikyKernelSize();
        displayText("Pressure kernel radius ");

        displayText("");

        displayText("Mass-density:");
        displayMinMassDens();
        displayMaxMassDens();
        displayAvgMassDens();
    }

    private void displayMinMassDens()
    {
        displayText(String.format("%.9f", ModelDebug.instance.getMinMassDens()) + " min");
    }

    private void displayMaxMassDens()
    {
        displayText(String.format("%.9f", ModelDebug.instance.getMaxMassDens()) + " max");
    }

    private void displayAvgMassDens()
    {
        displayText(String.format("%.9f", ModelDebug.instance.getAvgMassDens()) + " avg");
    }

    private void displayFramerate()
    {
        displayText((int)pApplet.frameRate + " FPS");
    }

    private void displaySizeOneLine()
    {
        int y = getNextDebugLineY();
        int x1 = DEBUG_ITEM_X_POS;
        int x2 = x1 + Renderer.RENDERING_SCALE;
        pApplet.line(x1, y, x2, y);
        displayAnnotationForLine("1,0", x2, y);
    }

    private void displayPolyKernelSize()
    {
        int y = getNextDebugLineY();
        int x1 = DEBUG_ITEM_X_POS;
        int x2 = (int) (x1 + SphSimulator.KERNEL_SIZE_SQ * Renderer.RENDERING_SCALE);
        pApplet.line(x1, y, x2, y);
        String kernelRadius = String.format("%.3f", SphSimulator.KERNEL_SIZE_SQ);
        displayAnnotationForLine(kernelRadius, x2, y);
    }

    private void displaySpikyKernelSize()
    {
        int y = getNextDebugLineY();
        int x1 = DEBUG_ITEM_X_POS;
        int x2 = (int) (x1 + SphSimulator.KERNEL_SIZE * Renderer.RENDERING_SCALE);
        pApplet.line(x1, y, x2, y);
        String kernelRadius = String.format("%.3f", SphSimulator.KERNEL_SIZE);
        displayAnnotationForLine(kernelRadius, x2, y);
    }

    /**
     * Support methods
     */

    private void displayAnnotationForLine(String text, int x2, int y)
    {
        float x = x2 + ANNOTATION_X_OFFSET;
        pApplet.text(text, x, y);
    }

    private void displayText(String text)
    {
        int y = getNextDebugLineY();
        pApplet.text(text, DEBUG_ITEM_X_POS, y);
    }

    private int getNextDebugLineY()
    {
        return DEBUG_START_Y + debugDisplayLine++ * GAP;
    }
}
