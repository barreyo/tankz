/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel.gameEntity.Powerup;

import GameModel.Player.IPlayer;
import GameUtilities.IObservable;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Per
 */
public abstract class APowerup implements IPowerup, IObservable {
    
    private Vector3f position;
    private boolean isVisible;
    
    public static final float MASS = 10f;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public APowerup(Vector3f position){
        this.position = position;
    }
    
    public void addPowerup(){
        isVisible = true;
        pcs.firePropertyChange(null, null, null);
    }
    public void removePowerup(){
        isVisible = false;
        pcs.firePropertyChange(null, null, null);
    }
    
    public float getMASS(){
        return MASS;
    }

    public Vector3f getPosition(){
        return position;
    }
    
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }
    
    public abstract void usePowerup(IPlayer player);
    
}
