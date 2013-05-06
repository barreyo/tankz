
package GameModel;

import com.jme3.math.Vector3f;

/**
 * Interface for modelling all the spawning points in the game.
 * 
 * @author perthoresson
 */
public interface ISpawningPoint {
    
    /**
     * 
     * @return if the spawning point is in use or not.
     */
    boolean isOccupied();
    
    /**
     * Sets if spawning point is in use or not.
     * 
     * @param inUse 
     */
    void setOccupied(boolean inUse);
    
    /**
     * 
     * @return the position of the spawningpoint
     */
    Vector3f getPosition();
    
    /**
     * 
     * @param occupier 
     */
    void setOccupier(IWorldObject occupier);
    
    /**
     * 
     * @return 
     */
    IWorldObject getOccupier();
}
