package GameModel;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Models a basic projectile that can damage at impact.
 * 
 * @author Daniel
 */
public final class MissileModel implements IExplodingProjectile {
    
    public static final int DAMAGE = 10;
    public static final float MASS = 10f;
    private static final float EXPLOSION_END_TIME = 4f;
    private static final float MAX_LIFE_TIME = 4f;
    
    private float projectileLifeTimer;
    private float explodingTimer;
    private Vector3f position;
    private Vector3f direction;
    private Quaternion rotation;
    
    private boolean exploding;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Instantiates the MissileModel.
     * 
     * @param damage damage the missile does
     * @param mass the mass of the missle
     */
    public MissileModel(Vector3f initialPos, Vector3f dir, Quaternion rotation) {
        this.position = initialPos.clone();
        this.direction = dir.clone();
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
            position = position.addLocal(direction.mult(0.1f*tpf).normalizeLocal());
            pcs.firePropertyChange(NEW_POS, null, position);
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
}
