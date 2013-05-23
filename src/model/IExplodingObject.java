
package model;

import com.jme3.math.Vector3f;

/**
 * All object that can explode should implement this interface. Enables
 * explosion effects and the possibility to do damage.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public interface IExplodingObject extends IWorldObject {
    
    /**
     * Sets exploding to true, and sends event.
     */
    public void impact();

    /**
     * Get the start position.
     * 
     * @return start pos.
     */
    public Vector3f getInitialPosition();

    /**
     * Applies damage on the specified damageable object.
     * 
     * @param damageableObject 
     */
    public void doDamageOn(IDamageableObject damageableObject);
}
