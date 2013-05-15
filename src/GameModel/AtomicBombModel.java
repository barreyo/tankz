/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import java.io.IOException;

/**
 * 
 * @author backman
 */
public class AtomicBombModel extends AExplodingProjectile {

    private static final int DAMAGE = 40;
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
