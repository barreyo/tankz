package GameModelLayer.Powerup;

/**
 * A slot for a powerup.
 *
 * @author Daniel
 */
public class PowerupSlot {
    private EPowerup powerup;
    
    /**
     * @return The powerup contained in this slot.
     */
    public synchronized EPowerup getPowerup() {
        return powerup;
    }

    /**
     * @param powerup The powerup to set in this slot.
     */
    public synchronized void setPowerup(EPowerup powerup) {
        this.powerup = powerup;
    }
}
