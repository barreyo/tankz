/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import GameUtilities.Commands;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

/**
 * 
 * @author backman
 */
public class AtomicBombModel extends AExplodingProjectile {

    private static final int DAMAGE = 40;
    private static final float MASS = 20f;
    
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
            if (launcherPlayer != null) {
                launcherPlayer.incrementKills();
            }
        }
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
