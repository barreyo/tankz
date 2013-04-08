
package GameLogicLayer.controls;

import GameLogicLayer.Effects.EffectsManager;
import com.jme3.scene.control.Control;

/**
 * Manages controls.
 * 
 * @author Daniel
 */
public class ControlManager {
    private static ControlManager instance;
    
    private ControlManager() {}
    
    public static synchronized ControlManager getInstance() {
        if (instance == null) {
            instance = new ControlManager();
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
