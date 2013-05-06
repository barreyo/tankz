
package GameModel;

import com.jme3.math.Vector3f;

/**
 *
 * @author Per
 */
public interface IPowerup extends IWorldObject {
    
    /**
     * 
     */
    public void playerPickedUpPowerup();
    
    /**
     * 
     * @return mass of the powerup.
     */
    public float getMass();

    /**
     * 
     * @return position of the powerup.
     */
    public Vector3f getPosition();
    
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
