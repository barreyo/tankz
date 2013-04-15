
package GameModel.Player;

import GameModel.IObservable;
import GameModel.gameEntity.Powerup.EPowerup;
import GameModel.gameEntity.Powerup.PowerupSlot;
import GameModel.gameEntity.Vehicle.IArmedVehicle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A representation of a player.
 * 
 * @author Albin
 * @author Daniel
 */
public class Player implements IPlayer, IObservable {
    private String name;
    private IArmedVehicle vehicle;
    private int kills, deaths;
    private boolean isActive;
    private EPowerup powerup;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
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

    
    public String getName() {
        return name;
    }

    public IArmedVehicle getVehicle() {
        return vehicle;
    }

    public int getKills() {
        return kills;
    }

    public void incrementKills() {
        kills++;
    }

    public int getDeaths() {
        return deaths;
    }

    public void incrementDeaths() {
        deaths++;
    }

    public void resetStats() {
        deaths = 0;
        kills = 0;
    }

    public boolean isActive() {
        return isActive;
    }

    public void activatePlayer(){
        if (!isActive) {
            isActive = true;
        }
    }
    
    public void deactivatePlayer() {
        if (isActive) {
            isActive = false;
        }
    }
    
    /**
     * @inheritdoc
     */
    @Override
    public EPowerup getPowerup() {
        return powerup;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setPowerup(EPowerup powerup) {
        this.powerup = powerup;
        pcs.firePropertyChange(null, null, null);
    }

    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    public boolean equals(IPlayer player) {
        return false;
        
    }
    
    /**
     * 
     * @return name, vehicle, kills, deaths and isActive in the format:
     * Player{name=xxx, vehicle=xxx, kills=xxx, deaths=xxx, isActive=xxxx
     */
    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", vehicle=" + vehicle + ", "
                + "kills=" + kills + ", deaths=" + deaths + ", isActive="
                + isActive + '}';
    } 

    /**
     * 
     * @return hashCode based on the name. 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
     /**
     * Equals method that compares the name of the Player.
     *
     * @param obj the reference object with which to compare.
     * @return true if this Player is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
}