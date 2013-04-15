/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel.gameEntity.Powerup;

import GameModel.IObservable;
import GameModel.Player.IPlayer;
import GameModel.gameEntity.Vehicle.IArmedVehicle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Garpetun
 */
public class Powerup implements IPowerup {

    private EPowerup powerup;
    private boolean isVisible;
    
    private final float HASTE_FACTOR = 1000;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    public void addPowerup() {
        isVisible = true;
    }
    
    /**
     * Use the powerup, removing it from world.
     */
    public void removePowerup() {
        isVisible = false;
    }
}
