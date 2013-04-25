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
    
    private static final float EXPLOSION_END_TIME = 4f;
    private static final long MAX_LIFE_TIME = 4000;
    private long lifeTimerStart;
    private long projectileLifeTimer;
    private float explodingTimer;
    private boolean isInWorld;
    
    boolean exploding;
    
    final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public AExplodingProjectile() {
        this.position = Vector3f.ZERO;
        this.linearVelocity = Vector3f.ZERO;
        this.rotation = Quaternion.ZERO;
        isInWorld = false;
    }
    
    
    @Override
    public Vector3f getPosition() {
        return position.clone();
    }

    @Override
    public Quaternion getRotation() {
        return rotation.clone();
    }
    
      /**
     * @inheritdoc
     */
    @Override
    public void update(float tpf) {
        if (isInWorld) {
            if (exploding) {
                explodingTimer += tpf;
                if (explodingTimer > EXPLOSION_END_TIME) {
                    explodingTimer = 0;
                    exploding = false;
                    pcs.firePropertyChange(Commands.EXPLOSION_FINISHED, null, null);
                }
            } else {
                projectileLifeTimer = System.currentTimeMillis();
                if (projectileLifeTimer - lifeTimerStart >= MAX_LIFE_TIME) {
                    projectileLifeTimer = 0;
                    hideFromWorld();
                }
            }
        }
    }
    
    /**
     * @inheritdoc
     */
    @Override
    public void impact() {
        exploding = true;
        hideFromWorld();
    }

    @Override
    public void updatePosition(Vector3f pos) {
        this.position = pos.clone();
    }
    
    @Override
    public void launchProjectile(Vector3f initialPos, Vector3f initialVelocity, Quaternion initialRotation) {
        this.initialPos = new Vector3f(initialPos);
        this.linearVelocity =  new Vector3f(initialVelocity);
        this.rotation =  new Quaternion(initialRotation);
        showInWorld();
    }
    
    @Override
    public Vector3f getInitialPosition() {
        return new Vector3f(initialPos);
    }

    @Override
    public Vector3f getLinearVelocity() {
        return this.linearVelocity.clone();
    }

    @Override
    public void showInWorld() {
        isInWorld = true;
        lifeTimerStart = System.currentTimeMillis();
        pcs.firePropertyChange(Commands.SHOW, null, null);
    }

    @Override
    public void hideFromWorld() {
        isInWorld = false;
        pcs.firePropertyChange(Commands.HIDE, null, null);
    }

    @Override
    public void cleanup() {
        pcs.firePropertyChange(Commands.CLEANUP, null, null);
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
    public boolean isInWorld() {
        return this.isInWorld;
    }
}
