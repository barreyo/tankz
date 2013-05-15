
package GameModel;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 *
 * @author Bex
 */
public interface IExplodingObject extends IWorldObject {
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
     * @return
     */
    public Vector3f getInitialPosition();

    /**
     * Applies damage on the specified damageable object.
     * 
     * @param damageableObject 
     */
    public void doDamageOn(IDamageableObject damageableObject);
    
    public long getExplosionEndTime();
    
    public long getLifeTime();
}
