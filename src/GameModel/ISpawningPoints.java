
package GameModel;

import com.jme3.math.Vector3f;

/**
 * Interface for modelling all the spawning points in the game.
 * 
 * @author perthoresson
 */
public interface ISpawningPoints {
    
    /**
     * 
     * @return if the spawning point is in use or not.
     */
    public boolean isInUse();
    
    /**
     * Sets if spawning point is in use or not.
     * 
     * @param inUse 
     */
    public void setInUse(boolean inUse);
    
    /**
     * 
     * @return the position of the spawningpoint
     */
    public Vector3f getPosition();
    
}
