
package GameModelLayer.Player;

import GameModelLayer.Powerup.EPowerup;
import GameModelLayer.Powerup.PowerupSlot;
import GameModelLayer.Vehicle.IVehicle;

/**
 * A representation of a player.
 * 
 * @author Albin
 * @author Daniel
 */
public class PlayerModel {
    private String name;
    private IVehicle vehicle;
    private int kills, deaths;
    
    /**
     * Basic contructor for constructing a player
     * 
     * @param name sets the name of the player.
     * @param vehicle sets the vehicle of the player.
     */
    public PlayerModel(String name, IVehicle vehicle) {
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
    public IVehicle getVehicle() {
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