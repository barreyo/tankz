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
    
    private float launchingTimer;
    private static final float LAUNCH_END_TIME = 1f;
    

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
        launchingTimer += tpf;
        if (launchingTimer >= LAUNCH_END_TIME) {
            if (!exploding && isMoving) {
                turn(tpf);
                move(tpf);
            }
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
    }

    private void turn(float tpf) {
        Vector3f targetVec = new Vector3f(target);
        targetVec.subtractLocal(position);
        turnTo.lookAt(targetVec, Vector3f.UNIT_Y);
        pcs.firePropertyChange(Commands.ROTATE, rotation, turnTo);
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
