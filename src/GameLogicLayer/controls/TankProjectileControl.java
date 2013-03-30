
package GameLogicLayer.controls;

import GameModelLayer.gameEntity.Projectile.IProjectile;
import GameViewLayer.gameEntity.Projectile.IProjectileSpatial;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;

/**
 * WIll BE EDITED
 *
 * @author Daniel
 */
public class TankProjectileControl extends BaseControl implements PhysicsCollisionListener{
    private IProjectile projectileModel;
    private IProjectileSpatial procetileSpatial;
    private PhysicsSpace phsyicsSpace;
    
    /**
     *
     * @param projectileModel
     * @param projectileSpatial
     * @param physicsSpace
     */
    public TankProjectileControl(IProjectile projectileModel, 
                         IProjectileSpatial projectileSpatial,
                         PhysicsSpace physicsSpace) {
        this.projectileModel = projectileModel;
        this.procetileSpatial = projectileSpatial;
        this.phsyicsSpace = physicsSpace;
        physicsSpace.addCollisionListener(this);
    }

    /**
     *
     * @param event
     */
    @Override
    public void collision(PhysicsCollisionEvent event) {
        // TODO
    } 

    @Override
    void controlUpdate(float tpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
