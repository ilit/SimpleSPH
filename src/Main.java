import processing.core.PApplet;

public class Main
{
    static public void main(String[] passedArgs)
    {
        String[] appletArgs = new String[]
                                      {
                                              "--window-color=#666666",
                                              "--stop-color=#cccccc",
                                              "ProcessingApplet",
                                      };
        PApplet.main(appletArgs);
    }
}
