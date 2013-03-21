
package GameLogicLayer.Controllers;

import GameLogicLayer.Vehicle.AVehicleController;

/**
 * A logical representation of a player.
 * 
 * @author Albin
 * @author Daniel
 */
public class Player {
    private String name;
    private AVehicleController vehicle;
    private int kills, deaths;
    
    /**
     * Basic contructor for constructing a player
     * 
     * @param name sets the name of the player.
     * @param vehicle sets the vehicle of the player.
     */
    public Player(String name, AVehicleController vehicle) {
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
    public AVehicleController getVehicle() {
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
}