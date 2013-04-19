
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
     * Returns a list of all the players in the game
     */
    public Collection<IPlayer> getPlayers();
    
    /**
     * Returns a list of all the powerups in the game
     */
    public Collection<IPowerup> getPowerups();
    
    /**
     * Returns a list of all the spawningpoints in the game
     */
    public Collection<ISpawningPoints> getSpawningPoints();

    public void update(float tpf);

    public void cleanup();
    
    public static final String END_GAME = "END_GAME";
}
