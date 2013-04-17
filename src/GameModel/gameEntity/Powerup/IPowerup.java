/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel.gameEntity.Powerup;

import GameModel.Player.IPlayer;
import com.jme3.math.Vector3f;

/**
 *
 * @author Per
 */
public interface IPowerup {
    
    /**
     * Adds a powerup to the world.
     */
    public void addPowerup();
    
    /**
     * Removes a powerup from the world.
     */
    public void removePowerup();
    
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
     * 
     * Activates the powerup.
     * 
     * @param player 
     */
    public void usePowerup(IPlayer player);
}
