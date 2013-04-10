
package GameModelLayer.Game;

/**
 *
 * @author perthoresson
 */
public interface ISpawningPoints {
    
    /**
     * Returns if the spawning point is in use or not.
     * 
     * @return 
     */
    public boolean isInUse();
    
    /**
     * Sets if spawning point is in use or not.
     * 
     * @param inUse 
     */
    public void setInUse(boolean inUse);
    
}
