package GameViewLayer.Map;

import GameViewLayer.gameEntity.GameEntity;
import java.util.LinkedList;
import java.util.List;

/**
 * A game map.
 * 
 * @author Daniel
 */
public interface GameMap {
    
    /**
     * Loads the map.
     */
    public void load();
    
    /**
     * Releases resources occupied by this map.
     */
    public void cleanup();
    
    /**
     * Returns a linked list of all game entities in the game map
     *
     * @return a linked list of all game entities in the game map
     */
    public List<GameEntity> getAllEntities();
}
