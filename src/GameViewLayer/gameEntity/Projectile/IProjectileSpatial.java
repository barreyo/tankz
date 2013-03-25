
package GameViewLayer.gameEntity.Projectile;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.scene.Spatial;

/**
 * A visual representation of a projectile with a collisonshape
 *
 * @author Daniel
 */
public interface IProjectileSpatial {
    /**
     * Getter for the spatial of the projectile.
     *
     * @return Spatial of the projectile
     */
    Spatial getProjectileSpatial();
    
    /**
     * Getter for the Collision shape of the projectile.
     *
     * @return The Collision shape of the projectile
     */
    CollisionShape getProjectileCollisionShape();
    
}
