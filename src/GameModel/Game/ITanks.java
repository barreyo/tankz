
package GameModel.Game;

import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import GameModel.gameEntity.Powerup.IPowerupBox;
import java.util.Collection;
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
    public void removePowerup(IPowerupBox powerup);
    
    /**
     * Adds a random powerup to one of the powerup-spawningpoints
     */
    public void addRandomPowerup();
    
    /**
     * Returns a list of all the players in the game
     */
    public Collection<IPlayer> getPlayers();
    
    /**
     * Returns a list of all the powerups in the game
     */
    public Collection<IPowerupBox> getPowerups();
    
    /**
     * Returns a list of all the spawningpoints in the game
     */
    public Collection<ISpawningPoints> getSpawningPoints();
    
}
