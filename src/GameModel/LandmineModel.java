
package GameModel;

import GameUtilities.Commands;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

/**
 *
 * @author perthoresson
 */
public class LandmineModel implements IWorldObject {
    private static final int DAMAGE = 30;
    private static final float MASS = 100f;
    
    private Vector3f initialPos;
    private Vector3f position;
    private Quaternion rotation;
    private boolean isInWorld;
    private boolean exploding;
    
    private static final long EXPLOSION_END_TIME = 2000;
    private long lifeTimerStart;
    private long explodingTimerStart;
    
    private IPlayer launcherPlayer;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Constructor for LandmineModel
     */
    public LandmineModel(){
        this.initialPos = Vector3f.ZERO;
        this.position = Vector3f.ZERO;
        this.rotation = Quaternion.ZERO;
        isInWorld = false;
    }
   

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3f getPosition() {
        return new Vector3f(position);
    }
    
    public Vector3f getInitialPosition() {
        return new Vector3f(initialPos);
    }

    /**
     * 
     * @return The velocity of the landmine. 
     */
    public Quaternion getRotation() {
        return rotation.clone();
    }
    
    /**
     *
     * @param rotation
     */
    public void updateRotation(Quaternion rotation) {
        this.rotation = rotation.clone();
    }

    /**
     * 
     * @return the mass of the missile
     */
    @Override
    public float getMass() {
        return MASS;
    }
    

    @Override
    public void showInWorld() {
        exploding = false;
        isInWorld = true;
        lifeTimerStart = System.currentTimeMillis();
        pcs.firePropertyChange(Commands.SHOW, null, null);
    }

    @Override
    public void hideFromWorld() {
        isInWorld = false;
        pcs.firePropertyChange(Commands.HIDE, null, null);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isShownInWorld() {
        return isInWorld;
    }

    /**
     *
     * @param tpf
     */
    @Override
    public void update(float tpf) {
        if (isInWorld) {
            if (exploding) {
                if (System.currentTimeMillis() - explodingTimerStart >= EXPLOSION_END_TIME) {
                    exploding = false;
                    pcs.firePropertyChange(Commands.EXPLOSION_FINISHED, null, null);
                }
            } 
        }
    }

    /**
     *
     */
    @Override
    public void cleanup() {
        pcs.firePropertyChange(Commands.CLEANUP, null, null);
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
     *
     * @param ex
     * @throws IOException
     */
    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param im
     * @throws IOException
     */
    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPosition(Vector3f pos) {
        this.position = new Vector3f(pos);
    }

    public void dropMine(Vector3f initialPos, IPlayer player) {
        this.initialPos = new Vector3f(initialPos);
        this.launcherPlayer = player;
        showInWorld();
    }

    public void impact() {
        exploding = true;
        explodingTimerStart = System.currentTimeMillis();
        hideFromWorld();
    }

    /**
     * {@inheritDoc}
     */
    public void doDamageOn(IDamageableObject damageableObject) {
        if (damageableObject.applyDamageToKill(DAMAGE)) {
            if (launcherPlayer != null && damageableObject != launcherPlayer.getVehicle()) {
                launcherPlayer.incrementKills();
            }
        }
    }
}
