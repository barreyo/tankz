package GameModel;

import GameUtilities.Commands;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Daniel
 */
public abstract class AExplodingProjectile implements IExplodingProjectile {
    
    Vector3f initialPos;
    Vector3f position;
    Vector3f linearVelocity;
    Quaternion rotation;
    
    private static final long EXPLOSION_END_TIME = 2000;
    private static final long MAX_LIFE_TIME = 10000;
    private long lifeTimerStart;
    private long explodingTimerStart;
    
    boolean isInWorld;
    boolean exploding;
    
    IPlayer launcherPlayer;
    
    final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     *
     */
    public AExplodingProjectile() {
        this.position = Vector3f.ZERO;
        this.linearVelocity = Vector3f.ZERO;
        this.initialPos = Vector3f.ZERO;
        this.rotation = Quaternion.ZERO;
        isInWorld = false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3f getPosition() {
        return position.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quaternion getRotation() {
        return rotation.clone();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        if (isInWorld) {
            if (exploding) {
                if (System.currentTimeMillis() - explodingTimerStart >= EXPLOSION_END_TIME) {
                    exploding = false;
                    pcs.firePropertyChange(Commands.EXPLOSION_FINISHED, null, null);
                }
            } else {
                if (System.currentTimeMillis() - lifeTimerStart >= MAX_LIFE_TIME) {
                    hideFromWorld();
                }
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void impact() {
        exploding = true;
        explodingTimerStart = System.currentTimeMillis();
        hideFromWorld();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Vector3f pos) {
        this.position = new Vector3f(pos);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void launchProjectile(Vector3f initialPos, Vector3f initialVelocity, Quaternion initialRotation, IPlayer player) {
        this.initialPos = new Vector3f(initialPos);
        this.linearVelocity =  new Vector3f(initialVelocity);
        this.rotation =  new Quaternion(initialRotation);
        this.launcherPlayer = player;
        showInWorld();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3f getInitialPosition() {
        return new Vector3f(initialPos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3f getLinearVelocity() {
        return this.linearVelocity.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showInWorld() {
        exploding = false;
        isInWorld = true;
        lifeTimerStart = System.currentTimeMillis();
        pcs.firePropertyChange(Commands.SHOW, null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideFromWorld() {
        isInWorld = false;
        pcs.firePropertyChange(Commands.HIDE, null, null);
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public boolean isShownInWorld() {
        return this.isInWorld;
    }
}
