package GameModel.gameEntity.Projectile;

import GameModel.Game.UserSettings;
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
    private static final float EXPLOSION_END_TIME = 0.5f;
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
        projectileLifeTimer += tpf;
        position = position.addLocal(direction.normalizeLocal());
        pcs.firePropertyChange(NEW_POS, null, position);
        if (projectileLifeTimer > MissileModel.MAX_LIFE_TIME) {
            pcs.firePropertyChange(END_OF_LIFETIME, null, null);
        }
        if (exploding) {
            explodingTimer += tpf;
            if (explodingTimer > MissileModel.EXPLOSION_END_TIME) {
                pcs.firePropertyChange(EXPLOSION_FINISHED, null, null);
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
        for (int i = 0; i < UserSettings.INSTANCE.getPlayers().size(); i++) {
            UserSettings.INSTANCE.getPlayers().get(i).getVehicle().decrementHealth(10);
            System.out.println(UserSettings.INSTANCE.getPlayers().get(i).getVehicle().getHealth());
        }
        pcs.firePropertyChange(IMPACT_MADE, null, null);
    }

    /**
     * 
     * @return projectLifeTimer, position and direction 
     * in the format: "MissileModel{projectileLifeTimer=xxx, position=xxxf, 
     * direction=xxxf"
     */
    @Override
    public String toString() {
        return "MissileModel{" + "projectileLifeTimer=" + projectileLifeTimer 
                + ", position=" + position + ", direction=" + direction + '}';
    }

    /**
     * 
     * @return hashCode based on projectileLifeTimer, position and direction.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Float.floatToIntBits(this.projectileLifeTimer);
        hash = 37 * hash + (this.position != null ? this.position.hashCode() : 0);
        hash = 37 * hash + (this.direction != null ? this.direction.hashCode() : 0);
        return hash;
    }

    /**
     * Equals method that compares projectileLifeTimer, position and direction.
     *
     * @param obj the reference object with which to compare.
     * @return true if this MissileModel is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MissileModel other = (MissileModel) obj;
        if (Float.floatToIntBits(this.projectileLifeTimer) != Float.floatToIntBits(other.projectileLifeTimer)) {
            return false;
        }
        if (this.position != other.position && (this.position == null || !this.position.equals(other.position))) {
            return false;
        }
        if (this.direction != other.direction && (this.direction == null || !this.direction.equals(other.direction))) {
            return false;
        }
        return true;
    }

    
}
