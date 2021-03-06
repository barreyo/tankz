
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import model.IArmedVehicle.VehicleState;
import utilities.Commands;

/**
 * A representation of a player.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class Player implements IPlayer {
    private String name;
    
    private IArmedVehicle vehicle;
    private int kills, deaths;
    private IPowerup powerup;
    
    private boolean respawn;
    private static final long DEATHTIME = 5;
    
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
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IArmedVehicle getVehicle() {
        return vehicle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getKills() {
        return kills;
    }

    /**
     * {{@inheritDoc}}
     */
    @Override
    public void incrementKills() {
        kills++;
        pcs.firePropertyChange(Commands.SCORE_UPDATE, null, null);
    }

    /**
     * {{@inheritDoc}}
     */
    @Override
    public int getDeaths() {
        return deaths;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementDeaths() {
        deaths++;
        pcs.firePropertyChange(Commands.SCORE_UPDATE, null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetStats() {
        deaths = 0;
        kills = 0;
        pcs.firePropertyChange(Commands.SCORE_UPDATE, null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setPowerup(IPowerup powerup) {
        if (this.powerup == null) {
            IPowerup oldPowerup = this.powerup;
            this.powerup = powerup;
            if (powerup instanceof AirCallPowerup) {
                pcs.firePropertyChange(Commands.SHOW_AIRCALL, null, null);
            }
            pcs.firePropertyChange(Commands.POWERUP_CHANGED, oldPowerup, this.powerup);
        }
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
     * {@inheritDoc}
     * 
     * @return name, vehicle, kills, deaths and isActive in the format:
     * Player{name=xxx, vehicle=xxx, kills=xxx, deaths=xxx, isActive=xxxx
     */
    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", vehicle=" + vehicle + ", "
                + "kills=" + kills + ", deaths=" + deaths + '}';
    } 

    /**
     * {@inheritDoc}
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
            IPowerup oldPowerup = powerup;
            powerup = null;
            pcs.firePropertyChange(Commands.POWERUP_CHANGED, oldPowerup, powerup);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanup() {
        vehicle.cleanup();
    }
    
    private boolean hasDiedThisDeath = false;
    private float deathTimer = DEATHTIME;
    private float secondTimer = 0;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        if (vehicle.getVehicleState() == VehicleState.DESTROYED && !hasDiedThisDeath) {
            this.incrementDeaths();
            hasDiedThisDeath = true;
            showScoreboard();
        } else if (vehicle.getVehicleState() == VehicleState.ALIVE) {
            hasDiedThisDeath = false;
        }
        
        if(vehicle.getVehicleState() == VehicleState.DESTROYED){ 
            deathTimer -= tpf;
            secondTimer += tpf;
            if(secondTimer >= 1.0f){
                pcs.firePropertyChange(Commands.SCORE_RESPAWN_UPDATE, null, null);
                secondTimer = 0;
            }
            
            if(deathTimer <= 0){
                respawn = true;
                deathTimer = DEATHTIME;
                hideScoreboard();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showScoreboard() {
        // Pass on to the view
        pcs.firePropertyChange(Commands.SHOW_SCOREBOARD, null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideScoreboard() {
        // Pass on to the view
        pcs.firePropertyChange(Commands.HIDE_SCOREBOARD, null, null);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setRespawn(boolean respawn){
        this.respawn = respawn;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldRespawn(){
        return respawn;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getDeathTime(){
        return (int)deathTimer;
    }
}
