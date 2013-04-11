package GameView.Map;

import GameView.gameEntity.AGameEntity;
import java.util.List;

/**
 * A game map.
 * 
 * @author Daniel
 */
public interface IGameMap {
    
    /**
     * Loads the map.
     */
    public void load();
    
    /**
     * Releases resources occupied by this map.
     */
    public void cleanup();
    
    /**
     * Returns a list of all game entities in the game map
     *
     * @return a list of all game entities in the game map
     */
    public List<AGameEntity> getAllEntities();
}
