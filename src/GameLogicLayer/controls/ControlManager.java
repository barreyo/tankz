
package GameLogicLayer.controls;

import com.jme3.scene.control.Control;

/**
 *
 * @author Daniel
 */
public class ControlManager {
    public Control getControl(TanksControl tanksControl) {
        if (tanksControl != null) {
            return tanksControl.createControl();
        }
        return null;
    }
}
