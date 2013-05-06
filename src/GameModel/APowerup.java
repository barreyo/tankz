package GameModel;

import GameUtilities.Commands;
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
    
    /**
     *
     */
    public static final float MASS = 10f;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     *
     */
    public APowerup(){
        position = Vector3f.ZERO;
    }
    
    @Override
    public void showInWorld(){
        isInWorld = true;
        pcs.firePropertyChange(Commands.SHOW, null, null);
    }
    
    @Override
    public void hideFromWorld() {
        isInWorld = false;
        pcs.firePropertyChange(Commands.HIDE, null, null);
    }
    
    @Override
    public void playerPickedUpPowerup() {
        this.setHeldByPlayer(true);
    }
    
    @Override
    public float getMass(){
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
    
    /**
     *
     * @return
     */
    @Override
    public boolean isInWorld() {
        return isInWorld;
    }
    
    /**
     *
     * @param held
     */
    @Override
    public void setHeldByPlayer(boolean held) {
        isHeldByPlayer = held;
    }
    
    /**
     *
     */
    @Override 
    public void cleanup() {
        
    }
    
    /**
     *
     * @param tpf
     */
    @Override
    public void update(float tpf) {
    }      
    
    @Override
    public void usePowerup(IPlayer player) {
        setHeldByPlayer(false);
    }
}
