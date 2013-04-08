
package GameLogicLayer.entitycontrols;

import GameLogicLayer.entitycontrols.EControls;
import com.jme3.scene.control.Control;

/**
 * Manages controls.
 * 
 * @author Daniel
 */
public class ControlFactory {
    private static ControlFactory instance;
    
    private ControlFactory() {}
    
    public static synchronized ControlFactory getInstance() {
        if (instance == null) {
            instance = new ControlFactory();
        }
        return instance;
    }
    /**
     *
     * @param tanksControl 
     * @return
     */
    public synchronized Control getControl(EControls tanksControl) {
        if (tanksControl != null) {
            return tanksControl.createControl();
        }
        return null;
    }
}
