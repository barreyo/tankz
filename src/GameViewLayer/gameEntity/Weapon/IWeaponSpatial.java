
package GameViewLayer.gameEntity.Weapon;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 * WILL BE REMOVDED
 * Spatial representation of a weapon.
 *
 * @author Daniel
 */
public interface IWeaponSpatial {
    /**
     * Gets the spatial representation of the weapon.
     *
     * @return The weaponspatial
     */
    Spatial getWeaponSpatial();  
    
    /**
     * Gets the direction of the weapon in the 3d room.
     *
     * @return The direction of the vehicle
     */
    Vector3f getAttackDirection();
    
    /**
     * Gets the position of the weapon in the 3d room.
     * 
     * @return The position of the vehicle
     */
    Vector3f getPosition();
    
    /**
     * Sets the direction of the weapon in the 3d room.
     *
     * @param direction The direction of the vehicle in the 3d room
     */
    void setAttackDirection(Vector3f direction);
    
    /**
     * Sets the position of the weapon in the 3d room.
     * 
     * @param position The position of the vehicle in the 3d room
     */
    void setPosition(Vector3f position);
}
