package model;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * Interface for a basic projectile.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public interface IExplodingProjectile extends IExplodingObject {
    
    /**
     * Launches the projectile with.
     * 
     * @param initialPos start pos.
     * @param initialVelocity start speed.
     * @param initialRotation start rotation.
     */
    public void launchProjectile(Vector3f initialPos, Vector3f initialVelocity, Quaternion initialRotation, IPlayer player);

    /**
     * Get the linear velocity.
     *  
     * @return The linear velocity
     */
    public Vector3f getLinearVelocity();
}
