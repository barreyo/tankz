package GameModel;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 *
 * @author Daniel
 */
public final class MissileModel extends AExplodingProjectile {
    private static final int DAMAGE = 20;
    private static final float MASS = 0.1f;
    
    /**
     * Instantiates the MissileModel.
     * 
     * @param damage damage the missile does
     * @param mass the mass of the missle
     */
    public MissileModel(Vector3f initialPos, Vector3f velocity, Quaternion rotation) {
        super(initialPos, velocity, rotation);
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
        super.update(tpf);
        if (!exploding && isMoving) {
            turn(tpf);
            move(tpf);
        }
    }
    
    private boolean isMoving;
   
    private Vector3f target;
    private Quaternion turnTo = new Quaternion();

    public void moveTo(Vector3f target) {
        isMoving = true;
        this.target = target;
    }

    private void move(float tpf) {
        linearVelocity = target.subtractLocal(position).normalizeLocal().multLocal(50f);
        pcs.firePropertyChange(MOVE, null, null);
    }

    private void turn(float tpf) {
        Vector3f targetVec = new Vector3f(target);
        targetVec.subtractLocal(position);
        turnTo.lookAt(targetVec, Vector3f.UNIT_Y);
        Quaternion newRotation = turnTo;
        pcs.firePropertyChange(ROTATE, rotation, newRotation);
    }
}
