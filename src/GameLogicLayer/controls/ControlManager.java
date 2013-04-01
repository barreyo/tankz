
package GameLogicLayer.controls;

import com.jme3.scene.control.Control;

/**
 * Manages controls.
 * 
 * @author Daniel
 */
public class ControlManager {
    /**
     *
     * @param tanksControl 
     * @return
     */
    public Control getControl(EControls tanksControl) {
        if (tanksControl != null) {
            return tanksControl.createControl();
        }
        return null;
    }
}
