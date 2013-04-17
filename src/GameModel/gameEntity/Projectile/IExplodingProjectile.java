package GameModel.gameEntity.Projectile;

import GameUtilities.IObservable;
import com.jme3.bullet.PhysicsTickListener;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * Interface for a basic projectile.
 * 
 * @author Daniel
 */
public interface IExplodingProjectile extends IObservable {
    /**
     * Returns the damage done on impact.
     *
     * @return the damage done on impact
     */
    public int getDamageOnImpact();

    /**
     * Returns the mass of the projcetile in kg.
     *
     * @return the mass of the projectile in kg
     */
    public float getMass();
   
    public void update(float tpf);
    
    public Vector3f getPosition();
    
    public Quaternion getRotation();
    
    public void impact();
    
    // Commands
    public static final String NEW_POS = "NEW_POS";
    public static final String END_OF_LIFETIME = "END_OF_LIFETIME";
    public static final String IMPACT_MADE = "IMPACT_MADE";
    public static final String EXPLOSION_FINISHED = "EXPLOSION_FINISHED";
}
