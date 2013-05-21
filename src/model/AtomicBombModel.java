
package model;

import utilities.Commands;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.io.IOException;

/**
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class AtomicBombModel extends AExplodingProjectile {

    private static final int DAMAGE = 20;
    private static final float MASS = 20f;
    private static final long EXPLOSION_END_TIME = 2000;
    private static final long MAX_LIFE_TIME = 5000;
    
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
    public void doDamageOn(IDamageableObject damageableObject) {
        if (damageableObject.applyDamageToKill(DAMAGE)) {
            if (launcherPlayer != null && damageableObject != launcherPlayer.getVehicle()) {
                launcherPlayer.incrementKills();
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        if (isInWorld) {
            Quaternion oldRotation = new Quaternion(rotation);
            rotation.lookAt(position.add(linearVelocity.normalize()), Vector3f.UNIT_Y);
            pcs.firePropertyChange(Commands.ROTATE, oldRotation, rotation);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public long getExplosionEndTime() {
        return EXPLOSION_END_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLifeTime() {
        return MAX_LIFE_TIME;
    }
    
}
