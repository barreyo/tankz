package GameModelLayer.Powerup;

/**
 * A slot for a powerup.
 *
 * @author Daniel
 */
public class PowerupSlot {
    private IPowerupModel powerup;

    /**
     * @return The powerup contained in this slot.
     */
    public synchronized IPowerupModel getPowerup() {
        return powerup;
    }

    /**
     * @param powerup The powerup to set in this slot.
     */
    public synchronized void setPowerup(IPowerupModel powerup) {
        this.powerup = powerup;
    }
}
