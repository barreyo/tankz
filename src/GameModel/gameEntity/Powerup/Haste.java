/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel.gameEntity.Powerup;

import GameModel.Player.IPlayer;
import GameModel.gameEntity.Vehicle.IArmedVehicle;

/**
 *
 * @author Garpetun
 */
public class Haste extends APowerup {

    private final float SPEED_FACTOR = 1000;
    
    public void usePowerup(IPlayer player) {
        player.getVehicle().incrementAccelerationValue(SPEED_FACTOR);
    }
}
