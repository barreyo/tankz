
package GameControllers.entitycontrols;

import com.jme3.scene.control.Control;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enum of controls in tanks game.
 * 
 * @author Daniel
 */
public enum EControls {
    
    /**
     * A tank vehicle control.
     */
    VEHICLE_CONTROL(TanksVehicleControl.class),
    /**
     * A tank projectile control.
     */
    MISSILE_CONTROL(MissileControl.class);

    private Class<? extends Control> control;

    EControls(Class<? extends Control> control) {
        this.control = control;
    }

    /**
     * Returns the control.
     * 
     * @return The control.
     */
    public Class<? extends Control> getControl() {
        return control;
    }
}
