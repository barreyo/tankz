package GameModel;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Per
 */
public abstract class APowerup implements IPowerup {
    
    private Vector3f position;
    private Quaternion rotation;
    private boolean isHeldByPlayer;
    private boolean isInWorld;
    protected boolean isActive;
    protected float activateTimer;
    
    public static final float MASS = 10f;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public APowerup(){
        position = Vector3f.ZERO;
    }
    
    @Override
    public void showInWorld(){
        isInWorld = true;
        pcs.firePropertyChange(SHOW, null, null);
    }
    
    @Override
    public void hideFromWorld() {
        isInWorld = false;
        pcs.firePropertyChange(HIDE, null, null);
    }
    
    @Override
    public void playerPickedUpPowerup() {
        this.setHeldByPlayer(true);
    }
    
    @Override
    public float getMASS(){
        return MASS;
    }

    @Override
    public Vector3f getPosition(){
        return position.clone();
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
        this.position = position.clone();
    }
    
    @Override
    public boolean isHeldByPlayer() {
        return isHeldByPlayer;
    }
    
    @Override
    public boolean isInWorld() {
        return isInWorld;
    }
    
    @Override
    public void setHeldByPlayer(boolean held) {
        isHeldByPlayer = held;
    }
    
    @Override 
    public void cleanup() {
        
    }
    
    @Override
    public void update(float tpf) {
    }      
    
    @Override
    public void usePowerup(IPlayer player) {
        setHeldByPlayer(false);
    }
    
    public boolean isActivated() {
        return isActive;
    }
    
    public float getTimer() {
        return activateTimer;
    }
}
