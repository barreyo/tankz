
package GameModel;

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
