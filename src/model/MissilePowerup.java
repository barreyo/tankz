
package model;

/**
 * Missile powerup fires a homing missile, that searches 
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class MissilePowerup extends APowerup {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        
        player.getVehicle().shootMissile(player);
        isHeldByPlayer = false;
    }
}
