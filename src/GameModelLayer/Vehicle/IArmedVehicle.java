/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModelLayer.Vehicle;

import GameModelLayer.Powerup.EPowerup;
import GameModelLayer.Weapon.IWeaponModel;

/**
 *
 * @author Daniel
 */
public interface IArmedVehicle extends IVehicle {
    /**
     *
     * @param model
     */
    void setWeaponModel(IWeaponModel model);
    
    /**
     *
     * @return
     */
    IWeaponModel getWeaponModel();
    
    /**
     * Returns the powerup in powerupSlot.
     * 
     * @return The powerup contained in powerupSlot
     */
    public EPowerup getPowerup();

    /**
     * Sets the powerup in the powerupSlot.
     * 
     * @param powerup The powerup to add in powerupSlot
     */
    public void setPowerup(EPowerup powerup);
}
