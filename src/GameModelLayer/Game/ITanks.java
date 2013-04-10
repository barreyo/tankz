
package GameModelLayer.Game;

import GameModelLayer.Player.Player;
import GameModelLayer.gameEntity.Powerup.IPowerUp;
import java.util.List;

/**
 *
 * @author perthoresson
 */
public interface ITanks {
    
    /**
     * Starts the game
     */
    public void startGame();
    
    /**
     * Ends the game
     */
    public void endGame();
    
    /**
     * Pauses the game
     */
    public void pauseGame();
    
    /**
     * Removes a powerup from the game
     */
    public void removePowerup(IPowerUp powerUp);
    
    /**
     * Adds a random powerup to one of the powerup-spawningpoints
     */
    public void addRandomPowerup();
    
    /**
     * Returns a list of all the players in the game
     */
    public List<Player> getPlayers();
    
    /**
     * Returns a list of all the powerups in the game
     */
    public List<IPowerUp> getPowerups();
    
    /**
     * Returns a list of all the spawningpoints in the game
     */
    public List<ISpawningPoints> getSpawningPoints();
    
}
