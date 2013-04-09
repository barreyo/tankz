
package GameLogicLayer.entitycontrols;

import GameLogicLayer.entitycontrols.EControls;
import com.jme3.scene.control.Control;

/**
 * Manages controls.
 * 
 * @author Daniel
 */
public final class ControlFactory {
    
    private ControlFactory() {}
   
    /**
     *
     * @param tanksControl 
     * @return
     */
    public static Control getControl(EControls tanksControl) {
        if (tanksControl != null) {
            return tanksControl.createControl();
        }
        return null;
    }
}
