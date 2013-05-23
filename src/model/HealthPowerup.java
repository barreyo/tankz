package model;

/**
 * A health power heals the player who uses it.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class HealthPowerup extends APowerup {

    private static final int HEAL = 40;

    /**
     * {@inheritDoc}
     */
    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);

        player.getVehicle().heal(HEAL);
        isHeldByPlayer = false;
    }
}
