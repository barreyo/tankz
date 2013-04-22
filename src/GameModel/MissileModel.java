package GameModel;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Daniel
 */
public class MissileModel implements IExplodingProjectile {
    private static final int DAMAGE = 20;
    private static final float MASS = 0.1f;
    private static final float EXPLOSION_END_TIME = 4f;
    private static final float MAX_LIFE_TIME = 10f;
    
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
            updateMoveableEntity(tpf);
        }
    }
    
    private boolean isMoving;
    private boolean isTurned;
    private boolean isTurning;
    private float speed;
    private float turnSpeedMultiplier;
    private float lerpAmount;
    private float lerpIncrease;
    
    private static final float MOVEMENT_ACCURACY = 0.5f;

    private Vector3f target;
    private Quaternion turnFrom;
    private Quaternion turnTo = new Quaternion();
    
    private void updateMoveableEntity(float tpf) {
        if (isMoving) {
            turn(tpf);
            move(tpf);
        }
    }

    public void moveTo(Vector3f target, float speed) {
        this.speed = speed;
        isMoving = true;
        this.target = target;
    }

    private void move(float tpf) {
        linearVelocity = target.subtractLocal(position).normalizeLocal().multLocal(20f);
        pcs.firePropertyChange(MOVE, null, null);
    }

    private void turn(float tpf) {
        Vector3f targetVec = new Vector3f(target);
        targetVec.subtractLocal(position);
        turnTo.lookAt(targetVec, Vector3f.UNIT_Y);
        turnTo.lookAt(targetVec, Vector3f.UNIT_Y);
        Quaternion newRotation = turnTo;
        pcs.firePropertyChange(ROTATE, rotation, newRotation);
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
    public Vector3f getLinearVelocity() {
        return linearVelocity.clone();
    }

    @Override
    public void updatePosition(Vector3f pos) {
        this.position = pos.clone();
    }
}
