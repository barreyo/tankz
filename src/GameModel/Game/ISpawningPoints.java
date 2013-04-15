
package GameModel.Game;

import com.jme3.math.Vector3f;

/**
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
     * Sets position of the spawningpoint.
     * 
     * @param position 
     */
    public void setPosition(Vector3f position);
    
    /**
     * 
     * @return the position of the spawningpoint
     */
    public Vector3f getPosition();
    
}
