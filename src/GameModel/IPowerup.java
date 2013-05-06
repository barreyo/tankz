
package GameModel;

import com.jme3.math.Vector3f;

/**
 * A interface for a powerup.
 * 
 * @author Per
 */
public interface IPowerup extends IWorldObject {
    
    /**
     * Indicates to the powerup that it was picked up.
     */
    public void powerupWasPickedUp();
    
    /**
     * Sets the position of this powerup.
     * 
     * @param pos the position to be set.
     */
    public void setPosition(Vector3f pos);
    
    /**
     * 
     * Activates the powerup.
     * 
     * @param player 
     */
    public void usePowerup(IPlayer player);
    
    /**
     *  
     * @param held
     */
    public void setHeldByPlayer(boolean held);
    
    /**
     * 
     * @return 
     */
    public boolean isHeldByPlayer();
}
