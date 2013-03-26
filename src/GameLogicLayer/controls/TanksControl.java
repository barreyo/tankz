
package GameLogicLayer.controls;

/**
 *
 * @author Daniel
 */
public enum TanksControl {
    
    FIRE_WEAPON_CONTROL(FireWeaponControl.class),
    VEHICLE_CONTROL(TanksVehicleControl.class);

    private Class<? extends BaseControl> control;

    TanksControl(Class<? extends BaseControl> control) {
        this.control = control;
    }

    public BaseControl createControl() {
        try {
            return (BaseControl) control.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Class<? extends BaseControl> getControl() {
        return control;
    }
}
