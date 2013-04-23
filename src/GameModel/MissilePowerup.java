/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Garpetun
 */
public class MissilePowerup extends APowerup {

    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        IArmedVehicle vehicle = player.getVehicle();
        
        player.getVehicle().shoot();
    }
    
}
