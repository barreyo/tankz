
package GameModelLayer.Player;

import GameModelLayer.Powerup.EPowerup;
import GameModelLayer.Powerup.PowerupSlot;
import GameModelLayer.Vehicle.IVehicleModel;

/**
 * A representation of a player.
 * 
 * @author Albin
 * @author Daniel
 */
public class PlayerModel {
    private String name;
    private IVehicleModel vehicle;
    private int kills, deaths;
    private PowerupSlot powerupSlot;
    
    /**
     * Basic contructor for constructing a player
     * 
     * @param name sets the name of the player.
     * @param vehicle sets the vehicle of the player.
     */
    public PlayerModel(String name, IVehicleModel vehicle, PowerupSlot slot) {
        this.name = name;
        this.vehicle = vehicle;
        this.powerupSlot = slot;
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
    public IVehicleModel getVehicle() {
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
     * Returns the powerup in powerupSlot.
     * 
     * @return The powerup contained in powerupSlot
     */
    public synchronized EPowerup getPowerup() {
        return powerupSlot.getPowerup();
    }

    /**
     * Sets the powerup in the powerupSlot.
     * 
     * @param powerup The powerup to add in powerupSlot
     */
    public synchronized void setPowerup(EPowerup powerup) {
        powerupSlot.setPowerup(powerup);
    }
}