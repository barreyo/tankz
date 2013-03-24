/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Projectile;

import GameModelLayer.Projectile.IProjectile;
import GameViewLayer.Projectile.IProjectileSpatial;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;

/**
 *
 * @author Daniel
 */
public class TankProjectileManager implements PhysicsCollisionListener{
    private IProjectile projectileModel;
    private IProjectileSpatial procetileSpatial;
    private PhysicsSpace phsyicsSpace;
    
    /**
     *
     * @param projectileModel
     * @param projectileSpatial
     * @param physicsSpace
     */
    public TankProjectileManager(IProjectile projectileModel, 
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
}
