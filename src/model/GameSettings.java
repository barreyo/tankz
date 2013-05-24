
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import utilities.IObservable;

/**
 * Representation of the game rules.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class GameSettings implements IObservable {
    
    private long gameEndTimeMS;
    private int killsToWin;
    private long powerupSpawningIntervallMS;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Basic constructor.
     * 
     * @param gameEndTimeMS how long the game should last.
     * @param killsToWin how many kills a player needs to finish the game.
     * @param powerupSpawningIntervallMS how frequent powerups should spawn.
     */
    public GameSettings (long gameEndTimeMS, int killsToWin, long powerupSpawningIntervallMS){
        this.gameEndTimeMS = gameEndTimeMS;
        this.killsToWin = killsToWin;
        this.powerupSpawningIntervallMS = powerupSpawningIntervallMS;
        if (this.gameEndTimeMS < 0) {
            this.gameEndTimeMS *= -1;
        }
        if (this.killsToWin < 0) {
            this.killsToWin *= -1;
        }
        if (this.powerupSpawningIntervallMS < 0) {
            this.powerupSpawningIntervallMS *= -1;
        }
    }
    
    /**
     * Returns the game end time, ie how long the game lasts.
     * 
     * @return gameEndTimeMS 
     */
    public long getGameEndTimeMS(){
        return gameEndTimeMS;
    }
    
    /**
     * Returns number of kills to win.
     * 
     * @return killsToWin
     */
    public int getKillsToWin() {
        return killsToWin;
    }
    
    /**
     * Get the powerup spawning interval in milliseconds.
     * 
     * @return powerup spawning interval in millisecs.
     */
    public long getPowerupSpawningIntervallMS() {
        return powerupSpawningIntervallMS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
    
}
