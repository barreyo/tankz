
package model;

/**
 * A interface for a powerup.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public interface IPowerup extends IWorldObject {
    
    /**
     * Indicates to the powerup that it was picked up.
     */
    public void powerupWasPickedUp();
    
    /**
     * Activates the powerup.
     * 
     * @param player the player who uses it.
     */
    public void usePowerup(IPlayer player);
    
    /**
     * Indicate that a player is holding this powerup.
     * 
     * @param held if held: true, otherwise false.
     */
    public void setHeldByPlayer(boolean held);
    
    /**
     * Determine if a player is holding this powerup.
     * 
     * @return if held: true, otherwise false.
     */
    public boolean isHeldByPlayer();
}
