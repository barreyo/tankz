/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel.gameEntity.Powerup;

import GameModel.Game.PowerupSpawningPoint;
import GameModel.IObservable;
import GameModel.gameEntity.Vehicle.IArmedVehicle;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Garpetun
 */
public abstract class APowerup implements IObservable {
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Use the powerup.
     */
    public abstract void usePowerup(IArmedVehicle vehicle);

    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
    
    //Add methods for getting spawningpoint, and randomizing a new one
    //And methods for position of course. But shouldn't spawningpoint perhaps
    // have a position?
}
