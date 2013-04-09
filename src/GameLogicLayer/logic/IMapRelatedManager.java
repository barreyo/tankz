package GameLogicLayer.logic;

/**
 * Interface for a game manager in the game Tanks.
 *
 * @author Daniel
 */
public interface IMapRelatedManager {
    /**
     * Load data relevant to the specified map
     * 
     * @param map
     */
    public void load(int map);
    /**
     * Releases resources held by this manager.
     */
    public void cleanup(); 
}
