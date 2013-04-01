
package GameLogicLayer.controls;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enum of controls in tanks game.
 * 
 * @author Daniel
 */
public enum TanksControl {
    
    /**
     * A weapon control.
     */
    FIRE_WEAPON_CONTROL(FireWeaponControl.class),
    /**
     * A vehicle control.
     */
    VEHICLE_CONTROL(TanksVehicleControl.class),
    PROJECTILE_CONTROL(TankProjectileControl.class);

    private Class<? extends BaseControl> control;

    TanksControl(Class<? extends BaseControl> control) {
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
            Logger.getLogger(TanksControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TanksControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Returns the control.
     * 
     * @return The control.
     */
    public Class<? extends BaseControl> getControl() {
        return control;
    }
}
