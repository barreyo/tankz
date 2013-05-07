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
     * Returns the rotation.
     * 
     * @return rotation
     */
    public Quaternion getRotation();
    
    /**
     * Sets exploding to true, and sends event.
     */
    public void impact();
    
    /**
     *
     * @param pos
     */
    public void setPosition(Vector3f pos);
    
    /**
     *
     * @param initialPos
     * @param initialVelocity
     * @param initialRotation
     */
    public void launchProjectile(Vector3f initialPos, Vector3f initialVelocity, Quaternion initialRotation);

    /**
     *
     * @return
     */
    public Vector3f getLinearVelocity();

    /**
     *
     * @return
     */
    public Vector3f getInitialPosition();

    /**
     * Applies damage on the specified damageable object.
     * 
     * @param damageableObject 
     */
    public void doDamageOn(IDamageableObject damageableObject);
}
