package GameModel;

import GameUtilities.Commands;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.io.IOException;

/**
 *
 * @author Daniel
 */
public final class MissileModel extends AExplodingProjectile {
    private static final int DAMAGE = 20;
    private static final float MASS = 0.1f;
        
    private boolean hasAttackTarget;
    private Vector3f attackTarget = new Vector3f();
    private Vector3f launchTarget = new Vector3f();
    
    private long launchTimerStart;
    private static final long LAUNCH_END_TIME = 1000;

    /**
     * {@inheritDoc}
     */
    @Override
    public float getMass() {
        return MASS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        if (isInWorld) {
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
        launchTarget = new Vector3f(initialPos).addLocal(0f, 100f, 0f);
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
        linearVelocity = new Vector3f(target).subtractLocal(position).normalizeLocal().multLocal(20f);
    }

    private void turn(Vector3f target) {
        Vector3f targetVec = new Vector3f(target);
        targetVec.subtractLocal(position);
        rotation.lookAt(targetVec, Vector3f.UNIT_Y);
        pcs.firePropertyChange(Commands.ROTATE, null, rotation);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doDamageOn(IDamageableObject damageableObject) {
        if (damageableObject.applyDamageToKill(DAMAGE)) {
            if (launcherPlayer != null) {
                launcherPlayer.incrementKills();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
