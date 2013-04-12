
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
    PROJECTILE_CONTROL(TankProjectileControl.class);

    private Class<? extends Control> control;

    EControls(Class<? extends Control> control) {
        this.control = control;
    }

    /**
     * Returns a new instance of the control.
     * 
     * @return A new instance of the control.
     */
    public BaseControl createControl() {
        try {  
            return (BaseControl) control.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(EControls.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
