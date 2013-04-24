package GameModel;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * Interface for a basic projectile.
 * 
 * @author Daniel
 */
public interface IExplodingProjectile extends IWorldObject {
    
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
    
    /**
     * Returns the position.
     * 
     * @return position
     */
    public Vector3f getPosition();
    
    /**
     * Returns the rotation.
     * 
     * @return rotation
     */
    public Quaternion getRotation();
    
    /**
     * Sets exploding to true, and sends event.
     */
    public void impact();
    
    public void updatePosition(Vector3f pos);
    
    public void updateRotation(Quaternion rotation);
    
    public void updateLinearVelocity(Vector3f velocity);
    
    // Commands
    public static final String END_OF_LIFETIME = "END_OF_LIFETIME";
    public static final String IMPACT_MADE = "IMPACT_MADE";
    public static final String EXPLOSION_FINISHED = "EXPLOSION_FINISHED";
    public static final String ROTATE = "ROTATE";
    public static final String SHOW = "SHOW";
    public static final String HIDE = "HIDE";
    public static final String CLEANUP = "CLEANUP";

    public Vector3f getLinearVelocity();
}
