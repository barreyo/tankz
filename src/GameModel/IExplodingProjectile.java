package GameModel;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * Interface for a basic projectile.
 * 
 * @author Daniel
 */
public interface IExplodingProjectile extends IExplodingObject {
    /**
     *
     * @param initialPos
     * @param initialVelocity
     * @param initialRotation
     */
    public void launchProjectile(Vector3f initialPos, Vector3f initialVelocity, Quaternion initialRotation, IPlayer player);

    /**
     *
     * @return
     */
    public Vector3f getLinearVelocity();
}
