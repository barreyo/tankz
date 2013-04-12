
package GameModel.Game;

import GameModel.IObservable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Representation of the rules.
 * 
 * @author perthoresson
 */
public class GameSettings implements IObservable {
    
    private float gameTime;
    private int killsToWin;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Basic constructor.
     * 
     * @param gameTime
     * @param killsToWin 
     */
    public GameSettings (float gameTime, int killsToWin){
        this.gameTime = gameTime;
        this.killsToWin = killsToWin;
    }
    
    /**
     * Sets the game time.
     * @param gameTime 
     */
    public void setGameTime (float gameTime){
        this.gameTime = gameTime;
    }
    
    /**
     * Sets number of kills to win.
     * @param kills 
     */
    public void setKillsToWin(int kills){
        this.killsToWin = kills;
    }
    
    /**
     * Returns the game time.
     * @return gameTime
     */
    public float getGameTime(){
        return gameTime;
    }
    
    /**
     * Returns number of kills to win.
     * 
     * @return killsToWin
     */
    public int killsToWin(){
        return killsToWin;
    }

    
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
    
}
