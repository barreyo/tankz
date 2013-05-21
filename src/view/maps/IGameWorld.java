package view.maps;

/**
 * A game map.
 * 
* @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
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
