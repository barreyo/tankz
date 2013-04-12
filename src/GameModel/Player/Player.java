
package GameModel.Player;

import GameModel.IObservable;
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
    
    private static int numberOfActivePlayers;
    
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
            numberOfActivePlayers++;
        }
    }
    
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


    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
}