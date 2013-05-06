
package GameModel;

import GameUtilities.IObservable;
import java.util.Collection;

/**
 * Interface with all the methods the gamestate needs.
 * 
 * @author perthoresson
 */
public interface ITanks extends IObservable {
    
    /**
     * Starts the game.
     */
    public void startGame();
    
    /**
     * Ends the game.
     */
    public void endGame();
    
    /**
     * Pauses the game.
     */
    public void pauseGame();
    
    /**
     * Resumes a paused game.
     */
    public void resumeGame();
    
    /**
     * Returns a list of all the players in the game.
     * @return 
     */
    public Collection<IPlayer> getPlayers();
    
    /**
     * Update for the game model.
     * @param tpf 
     */
    public void update(float tpf);
    
    /**
     * Notifies the game model to cleanup.
     */
    public void cleanup();

    /**
     * Notifies the game what powerup was picked up.
     * 
     * @param powerup the powerup that was picked up
     */
    public void powerupPickedUp(IPowerup powerup);
}
