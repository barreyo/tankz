
package model;

/**
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class LandminePowerup extends APowerup{
    /**
     * {@inheritDoc}
     */
    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        
        player.getVehicle().dropLandmine(player);
        isHeldByPlayer = false;
    }
}
