
package model;

import java.util.Collection;
import utilities.IObservable;

/**
 * Interface of the tanks game.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
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
     * 
     * @return a list of all the players in game
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
