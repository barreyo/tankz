/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel.gameEntity.Powerup;

import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Garpetun
 */
public class Powerup implements IPowerup {

    private EPowerup powerup;
    private boolean isVisible;
    private Vector3f position;
    
    private final float HASTE_FACTOR = 1000;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Powerup() {
        powerup = EPowerup.HASTE;
        position = new Vector3f(10,5,10);
    }
    
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

    public Vector3f getPosition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
