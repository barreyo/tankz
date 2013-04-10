
package GameModelLayer.Player;

import GameModelLayer.gameEntity.Vehicle.IArmedVehicle;

/**
 * A representation of a player.
 *
 * @author perthoresson
 */
public interface IPlayer {
    
    /**
     * Returns the name of the player.
     * 
     * @return name.
     */
    public String getName();
    
    /**
     * Returns the vehicle of the player.
     * 
     * @return vehicle.
     */
    public IArmedVehicle getVehicle();
    
    /**
     * Returns the amount of kills the player has scored.
     * 
     * @return kills.
     */
    public int getKills();
    
    /**
     * Increments the amount of kills a player has done by one.
     */
    public void incrementKills();
    
    /**
     * Returns the amount of deaths the player has suffered.
     * 
     * @return deaths.
     */
    public int getDeaths();
    
    /**
     * Increments the amount of deaths the player has suffered by one.
     */
    public void incrementDeaths();
    
    /**
     * Resets the amount of kills and death.
     * Sets them to zero.
     */
    public void resetStats();
    
    /**
     * Returns a boolean indicating if the player is active.
     * @return a boolean indicating if the player is active
     */
    public boolean isActive();
    
    /**
     * Activates the player.
     */
    public void activatePlayer();
    
    /**
     * Deactivates the player.
     */
    public void deactivatePlayer();
    
    
    
}
