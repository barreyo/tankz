package GameModel;

import GameModel.IPlayer;
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
    
    public APowerup(){
        position = Vector3f.ZERO;
    }
    
    @Override
    public void showPowerupInWorld(){
        isVisible = true;
        pcs.firePropertyChange(null, null, null);
    }
    
    @Override
    public void removeFromWorld() {
        isVisible = false;
        pcs.firePropertyChange(null, null, null);
    }
    
    @Override
    public float getMASS(){
        return MASS;
    }

    @Override
    public Vector3f getPosition(){
        return position;
    }
    
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    @Override
    public void setPosition(Vector3f position) {
        this.position = position;
    }
}
