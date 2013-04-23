package GameModel;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * Models a basic projectile that can damage at impact.
 * 
 * @author Daniel
 */
public final class CanonBallModel extends AExplodingProjectile {
    
    private static final int DAMAGE = 10;
    private static final float MASS = 0.1f;
    
    /**
     * Instantiates the MissileModel.
     * 
     * @param damage damage the missile does
     * @param mass the mass of the missle
     */
    public CanonBallModel(Vector3f initialPos, Vector3f velocity, Quaternion rotation) {
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
}
