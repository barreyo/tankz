package GameModel;

import com.jme3.collision.CollisionResults;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Models a basic projectile that can damage at impact.
 * 
 * @author Daniel
 */
public final class MissileModel implements IExplodingProjectile {
    
    private static final int DAMAGE = 10;
    private static final float MASS = 0.1f;
    private static final float EXPLOSION_END_TIME = 4f;
    private static final float MAX_LIFE_TIME = 4f;
    
    private float projectileLifeTimer;
    private float explodingTimer;
    private Vector3f position;
    private Vector3f linearVelocity;
    private Quaternion rotation;
    
    private boolean exploding;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Instantiates the MissileModel.
     * 
     * @param damage damage the missile does
     * @param mass the mass of the missle
     */
    public MissileModel(Vector3f initialPos, Vector3f velocity, Quaternion rotation) {
        this.position = initialPos.clone();
        this.linearVelocity = velocity.clone();
        this.rotation = rotation.clone();
    }

    /**
     * Returns the damage the missile does.
     * 
     * @return damage
     */
    @Override
    public int getDamageOnImpact() {
        return DAMAGE;
    }

    /**
     * Returns the mass of the missile.
     * 
     * @return mass
     */
    @Override
    public float getMass() {
        return MASS;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void update(float tpf) {
        if (exploding) {
            explodingTimer += tpf;
            if (explodingTimer > MissileModel.EXPLOSION_END_TIME) {
                pcs.firePropertyChange(EXPLOSION_FINISHED, null, null);
            }
        } else {
            projectileLifeTimer += tpf;
            if (projectileLifeTimer > MissileModel.MAX_LIFE_TIME) {
                pcs.firePropertyChange(END_OF_LIFETIME, null, null);
            }
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Vector3f getPosition() {
        return position.clone();
    }
    
    /**
     * @inheritdoc
     */
    @Override
    public Quaternion getRotation() {
        return rotation.clone();
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
    public void showInWorld() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void hideFromWorld() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cleanup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector3f getLinearVelocity() {
        return linearVelocity.clone();
    }

    @Override
    public void updatePosition(Vector3f pos) {
        this.position = pos.clone();
    }
}
