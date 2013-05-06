
package GameModel;

import GameUtilities.IObservable;

/**
 * A representation of a player.
 *
 * @author perthoresson
 */
public interface IPlayer extends IObservable {
    
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
     * Returns the powerup in powerupSlot.
     * 
     * @return The powerup contained in powerupSlot
     */
    IPowerup getPowerup();

    /**
     * Sets the powerup in the powerupSlot.
     * 
     * @param powerup The powerup to add in powerupSlot
     */
    void setPowerup(IPowerup powerup);
    
    /**
     * Players are considered equal when their names are equal.
     * 
     * @param player player to compare with.
     * @return true if equal false otherwise.
     */
    @Override
    boolean equals(Object obj);

    /**
     * Uses the powerup that the player holds, removing it from the player.
     */
    public void usePowerup();

    /**
     * Clear objects in the class.
     */
    public void cleanup();
    
    /**
     * Connect to the update loop of JMonkeyEngine.
     * 
     * @param tpf time per frame.
     */
    public void update(float tpf);
    
    /**
     * Show the scoreboard for the player.
     */
    public void showScoreboard();
    
    /**
     * Hide the scoreboard for the player.
     */
    public void hideScoreboard();
    
    /**
     * 
     * @return wether or not the player is due to respawn. 
     */
    public boolean shouldRespawn();
    
    /**
     * 
     * @return the current time until respawn.
     */
    public int getDeathTime();
    
    /**
     * Sets if the player should respawn.
     */
    public void setRespawn(boolean respawn);
}
