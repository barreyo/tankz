/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import java.util.List;

/**
 *
 * @author Garpetun
 */
public interface IMap {
    /**
     * Returns the PlayerSpawningPoints of the map.
     * @return the PlayerSpawningPoints of the map
     */
    public List<PlayerSpawningPoint> getPlayerSpawningPoints();
    
    /**
     * Returns the PowerupSpawningPoints of the map.
     * @return the PowerupSpawningPoints of the map
     */
    public List<PowerupSpawningPoint> getPowerupSpawningPoints();
}
