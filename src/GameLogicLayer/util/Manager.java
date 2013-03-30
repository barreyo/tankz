package GameLogicLayer.util;

/**
 *
 * @author Daniel
 */
public interface Manager {
    /**
     *
     * @param map
     */
    public void load(int map); //load everything relevant to that map
    /**
     *
     */
    public void cleanup();       //cleanup everything relevant to that map
}
