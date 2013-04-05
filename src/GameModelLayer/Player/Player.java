
package GameModelLayer.Player;

import GameModelLayer.gameEntity.Vehicle.IArmedVehicle;

/**
 * A representation of a player.
 * 
 * @author Albin
 * @author Daniel
 */
public class Player {
    private String name;
    private IArmedVehicle vehicle;
    private int kills, deaths;
    private boolean isActive;
    
    private static int numberOfActivePlayers;
    
    /**
     * Basic contructor for constructing a player
     * 
     * @param name sets the name of the player.
     * @param vehicle sets the vehicle of the player.
     */
    public Player(String name, IArmedVehicle vehicle) {
        this.name = name;
        this.vehicle = vehicle;
    }

    /**
     * Returns the name of the player.
     * 
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the vehicle of the player.
     * 
     * @return vehicle.
     */
    public IArmedVehicle getVehicle() {
        return vehicle;
    }

    /**
     * Returns the amount of kills the player has scored.
     * 
     * @return kills.
     */
    public int getKills() {
        return kills;
    }

    /**
     * Increments the amount of kills a player has done by one.
     */
    public void incrementKills() {
        kills++;
    }

    /**
     * Returns the amount of deaths the player has suffered.
     * 
     * @return deaths.
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Increments the amount of deaths the player has suffered by one.
     */
    public void incrementDeaths() {
        deaths++;
    }
    
    /**
     * Resets the amount of kills and death.
     * Sets them to zero.
     */
    public void resetStats() {
        deaths = 0;
        kills = 0;
    }
    
    /**
     * Returns a boolean indicating if the player is active.
     * @return a boolean indicating if the player is active
     */
    public boolean isActive() {
        return isActive;
    }
    
    /**
     * Activates the player.
     */
    public void activatePlayer(){
        if (!isActive) {
            isActive = true;
            numberOfActivePlayers++;
        }
    }
    
    /**
     * Deactivates the player.
     */
    public void deactivatePlayer() {
        if (isActive) {
            isActive = false;
            numberOfActivePlayers--;
        }
    }
    
    /**
     * Returns the number of active players.
     * @return the number of active players
     */
    public static int getNumberOfActivePlayers() {
        return numberOfActivePlayers;
    }
}