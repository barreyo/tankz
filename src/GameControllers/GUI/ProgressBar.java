
package GameControllers.GUI;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import java.util.Properties;

/**
 * 
 * @author Barre
 */
public class ProgressBar implements Controller {
    
    private Element progressBarElement;
    private Element progressTextElement;
    
    public void bind(Nifty nifty, Screen screen, Element element, Properties parameter, Attributes controlDefinitionAttributes) {
        progressBarElement = element.findElementByName("progress");
        progressTextElement = element.findElementByName("progress-text");
    }

    public void init(Properties parameter, Attributes controlDefinitionAttributes) {
        // Not supported
    }

    public void onStartScreen() {
        // Not supported
    }

    public void onFocus(boolean getFocus) {
        // Not supported
    }

    public boolean inputEvent(NiftyInputEvent inputEvent) {
        return false; // Not supported
    }
    
    /**
     * Update the progressbar.
     * 
     * @param value 
     * @param nowLoading 
     */
    public void setProgress(final float value, final String nowLoading) {
        float progressTemp = value;
        
        if (progressTemp < 0.0f) {
            progressTemp = 0.0f;
        } else if (progressTemp > 1.0f) {
            progressTemp = 1.0f;
        }
        // Updates the visual part of the progressbar
        final int MIN_WIDTH = 14;
        int pixelWidth = (int) (MIN_WIDTH + (progressBarElement.getParent().getWidth() - MIN_WIDTH) * progressTemp);
        progressBarElement.setConstraintWidth(new SizeValue(pixelWidth + "px"));
        progressBarElement.getParent().layoutElements();
        
        // Sets the text under the progressbar
        progressTextElement.getRenderer(TextRenderer.class).setText(nowLoading);
    }
    
}
