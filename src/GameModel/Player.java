
package GameModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A representation of a player.
 * 
 * @author Albin
 * @author Daniel
 */
public class Player implements IPlayer {
    private String name;
    private IArmedVehicle vehicle;
    private int kills, deaths;
    private boolean isActive;
    private IPowerup powerup;
    
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

    /**
     * @inheritdoc
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @inheritdoc
     */
    @Override
    public IArmedVehicle getVehicle() {
        return vehicle;
    }

    /**
     * @inheritdoc
     */
    @Override
    public int getKills() {
        return kills;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void incrementKills() {
        kills++;
        pcs.firePropertyChange("ScoreUpdate", null, null);
    }

    /**
     * @inheritdoc
     */
    @Override
    public int getDeaths() {
        return deaths;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void incrementDeaths() {
        deaths++;
        pcs.firePropertyChange("ScoreUpdate", null, null);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void resetStats() {
        deaths = 0;
        kills = 0;
        pcs.firePropertyChange("ScoreUpdate", null, null);
    }

        /**
     * @inheritdoc
     */
    @Override
    public boolean isActive() {
        return isActive;
    }

        /**
     * @inheritdoc
     */
    @Override
    public void activatePlayer(){
        if (!isActive) {
            isActive = true;
        }
    }
    
        /**
     * @inheritdoc
     */
    @Override
    public void deactivatePlayer() {
        if (isActive) {
            isActive = false;
        }
    }
    
    /**
     * @inheritdoc
     */
    @Override
    public synchronized IPowerup getPowerup() {
        return powerup;
    }

    /**
     * @inheritdoc
     */
    @Override
    public synchronized void setPowerup(IPowerup powerup) {
        if (this.powerup != null) {
            this.powerup.setHeldByPlayer(false);
        }
        this.powerup = powerup;
        pcs.firePropertyChange(POWERUP_CHANGED, null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /**
     * Players are considered equal when their names
     * 
     * @param player to be compared.
     * @return true if they are equal, false if not.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void usePowerup() {
        if (powerup != null) {
            powerup.usePowerup(this);
            powerup = null;
            pcs.firePropertyChange(POWERUP_CHANGED, null, null);
        }
    }

    @Override
    public void cleanup() {
        vehicle.cleanup();
    }
}
