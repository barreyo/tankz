package GameModel.gameEntity.Projectile;

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
     *
     * @param damage
     * @param mass
     */
    public MissileModel(Vector3f initialPos, Vector3f dir, Quaternion rotation) {
        this.position = initialPos.clone();
        this.direction = dir.clone();
        this.rotation = rotation.clone();
    }

    /**
     *
     * @return
     */
    public int getDamageOnImpact() {
        return DAMAGE;
    }

    /**
     *
     * @return
     */
    public float getMass() {
        return MASS;
    }

    public void update(float tpf) {
        if (exploding) {
            explodingTimer += tpf;
            if (explodingTimer > MissileModel.EXPLOSION_END_TIME) {
                pcs.firePropertyChange(EXPLOSION_FINISHED, null, null);
            }
        } else {
            projectileLifeTimer += tpf;
            position = position.addLocal(direction.normalizeLocal());
            pcs.firePropertyChange(NEW_POS, null, position);
            if (projectileLifeTimer > MissileModel.MAX_LIFE_TIME) {
                pcs.firePropertyChange(END_OF_LIFETIME, null, null);
            }
        }
    }

    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    public Vector3f getPosition() {
        return position.clone();
    }
    
    public Quaternion getRotation() {
        return rotation.clone();
    }

    public void impact() {
        exploding = true;
        pcs.firePropertyChange(IMPACT_MADE, null, null);
    }
}
