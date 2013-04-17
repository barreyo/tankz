package GameModel.gameEntity.Powerup;

import GameUtilities.IObservable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A slot for a powerup.
 *
 * @author Daniel
 */
public class PowerupSlot implements IObservable {
    private EPowerup powerup;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
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
    
    /**
     * Consumes the powerup in this slot.
     */
    public synchronized void consumePowerup() {
        powerup = null;
    }
    
    public boolean isEmpty() {
        return this.getPowerup()==null;
    }


    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    

    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
}
