
package model;

import utilities.IObservable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Representation of the rules.
 * 
 * @author perthoresson
 */
public class GameSettings implements IObservable {
    
    private long gameEndTimeMS;
    private int killsToWin;
    private long powerupSpawningIntervallMS;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Basic constructor.
     * 
     * @param gameEndTimeMS
     * @param killsToWin
     * @param powerupSpawningIntervallMS  
     */
    public GameSettings (long gameEndTimeMS, int killsToWin, long powerupSpawningIntervallMS){
        this.gameEndTimeMS = gameEndTimeMS;
        this.killsToWin = killsToWin;
        this.powerupSpawningIntervallMS = powerupSpawningIntervallMS;
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
     * 
     * @return the powe
     */
    public long getPowerupSpawningIntervallMS() {
        return powerupSpawningIntervallMS;
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
    
}
