
package GameModel;

import GameModel.IPlayer;
import GameUtilities.IObservable;
import com.jme3.math.Vector3f;

/**
 *
 * @author Per
 */
public interface IPowerup extends IObservable{
    
    /**
     * Adds the powerup to the world.
     */
    public void showPowerupInWorld();
    
    /**
     * Removes the powerup from the world.
     */
    public void removeFromWorld();
    
    /**
     * 
     * @return mass of the powerup.
     */
    public float getMASS();

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
     */
    public boolean isHeldByPlayer();

    public void cleanup();
    
    public static final String SHOW = "SHOW";
    public static final String HIDE = "HIDE";
    public static final String CLEANUP = "CLEANUP";
}
