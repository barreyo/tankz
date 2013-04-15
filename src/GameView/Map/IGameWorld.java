package GameView.Map;

/**
 * A game map.
 * 
 * @author Daniel
 */
public interface IGameWorld {
    
    /**
     * Loads the map.
     */
    public void load();
    
    /**
     * Releases resources occupied by this map.
     */
    public void cleanup();
}
