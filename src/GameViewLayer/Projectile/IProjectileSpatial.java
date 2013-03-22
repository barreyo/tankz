
package GameViewLayer.Projectile;

import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.scene.Spatial;

/**
 * 
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
    
    SphereCollisionShape getProjectileCollisionShape();
    
}
