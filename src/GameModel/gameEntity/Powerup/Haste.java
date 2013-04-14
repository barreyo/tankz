/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel.gameEntity.Powerup;

import GameModel.gameEntity.Vehicle.IArmedVehicle;

/**
 *
 * @author Garpetun
 */
public class Haste extends APowerup {

    private final float SPEED_FACTOR = 1000;
    
    public void usePowerup(IArmedVehicle vehicle) {
        vehicle.incrementAccelerationValue(SPEED_FACTOR);
    }

    
}
