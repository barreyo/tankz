package GameModel;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Daniel
 */
public abstract class AExplodingProjectile implements IExplodingProjectile {
    
    Vector3f position;
    Vector3f linearVelocity;
    Quaternion rotation;
    
    private static final float EXPLOSION_END_TIME = 4f;
    private static final float MAX_LIFE_TIME = 4f;
    private float projectileLifeTimer;
    private float explodingTimer;
    
    boolean exploding;
    
    final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public AExplodingProjectile(Vector3f initialPos, Vector3f velocity, Quaternion rotation) {
        this.position = initialPos.clone();
        this.linearVelocity = velocity.clone();
        this.rotation = rotation.clone();
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
        if (exploding) {
            explodingTimer += tpf;
            if (explodingTimer > EXPLOSION_END_TIME) {
                pcs.firePropertyChange(EXPLOSION_FINISHED, null, null);
            }
        } else {
            projectileLifeTimer += tpf;
            if (projectileLifeTimer > MAX_LIFE_TIME) {
                pcs.firePropertyChange(END_OF_LIFETIME, null, null);
            }
        }
    }
    
    /**
     * @inheritdoc
     */
    @Override
    public void impact() {
        exploding = true;
        pcs.firePropertyChange(IMPACT_MADE, null, null);
    }

    @Override
    public void updatePosition(Vector3f pos) {
        this.position = pos.clone();
    }
    
    @Override
    public void updateRotation(Quaternion rotation) {
        this.rotation = rotation;
    };
    
    @Override
    public void updateLinearVelocity(Vector3f velocity) {
        this.linearVelocity = velocity;
    };

    @Override
    public Vector3f getLinearVelocity() {
        return this.linearVelocity.clone();
    }

    @Override
    public void showInWorld() {
        pcs.firePropertyChange(SHOW, null, null);
    }

    @Override
    public void hideFromWorld() {
        pcs.firePropertyChange(HIDE, null, null);
    }

    @Override
    public void cleanup() {
        pcs.firePropertyChange(CLEANUP, null, null);
    }

    @Override
    public void addObserver(PropertyChangeListener l) {
       pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
}
