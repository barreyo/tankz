
package model;

import com.jme3.math.Vector3f;

/**
 *
 * @author perthoresson
 */
public class LandmineModel extends AExplodingObject {
    private static final int DAMAGE = 30;
    private static final float MASS = 500f;
    private static final long EXPLOSION_END_TIME = 2000;
    private static final long MAX_LIFE_TIME = 300000;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public float getMass() {
        return MASS;
    }

    public void dropMine(Vector3f initialPos, IPlayer player) {
        this.initialPos = new Vector3f(initialPos);
        this.launcherPlayer = player;
        showInWorld();
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
