
package GameView.GUI;

import com.jme3.font.BitmapText;
import java.beans.PropertyChangeEvent;

/**
 * Graphical representation of the in game timer.
 *
 * @author backman
 */
public class TimerView extends AHudElement {
    
    private BitmapText bitmapText;
    
    public TimerView() {
        
    }

    /**
     * {@inheritdoc}
     */
    public void propertyChange(PropertyChangeEvent pce) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
