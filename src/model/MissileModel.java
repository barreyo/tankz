package model;

import utilities.Commands;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import utilities.Constants;

/**
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public final class MissileModel extends AExplodingProjectile { 
    private boolean hasAttackTarget;
    private Vector3f attackTarget = new Vector3f();
    private Vector3f launchTarget = new Vector3f();
    
    private long launchTimerStart;
    private static final long LAUNCH_END_TIME = 1000;
    
    public MissileModel() {
        super(Constants.MISSILE_MASS, Constants.MISSILE_DAMAGE, 
                Constants.MISSILE_EXPLOSION_END_TIME, Constants.MISSILE_LIFE_TIME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        if (isInWorld) {
            pcs.firePropertyChange(Commands.SHOW_FLAME, null, null);
            super.update(tpf);
            if (!exploding) {
                if (System.currentTimeMillis() - launchTimerStart >= LAUNCH_END_TIME) {
                    if (hasAttackTarget) {
                        turn(attackTarget);
                        move(attackTarget);
                    }
                } else {
                    turn(launchTarget);
                    move(launchTarget);
                }
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void launchProjectile(Vector3f initialPos, Vector3f initialVelocity, Quaternion initialRotation, IPlayer player) {
        super.launchProjectile(initialPos, initialVelocity, initialRotation, player);
        launchTarget = initialPos.add(0f, 140f, 0f);
        launchTimerStart = System.currentTimeMillis();
        attackTarget = new Vector3f();
        hasAttackTarget = false;
    }

    /**
     *
     * @param target
     */
    public void setAttackTarget(Vector3f target) {
        hasAttackTarget = true;
        this.attackTarget = target;
    }

    private void move(Vector3f target) {
        linearVelocity = target.subtract(position).normalize().mult(40f);
    }

    private void turn(Vector3f target) {
        Vector3f targetVec = target.subtract(position);
        Quaternion oldRotation = new Quaternion(rotation);
        rotation.lookAt(targetVec, Vector3f.UNIT_Y);
        pcs.firePropertyChange(Commands.ROTATE, oldRotation, rotation);
    }
}
