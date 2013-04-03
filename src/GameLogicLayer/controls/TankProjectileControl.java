
package GameLogicLayer.controls;

import GameLogicLayer.Game.TanksGame;
import GameViewLayer.effects.EEffects;
import GameViewLayer.gameEntity.MissileProjectile;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 * WIll BE EDITED
 *
 * @author Daniel
 */
public class TankProjectileControl extends BaseControl implements PhysicsCollisionListener{
    private PhysicsSpace physicsSpace;
    private MissileProjectile projectile;
    private RigidBodyControl physicsControl;
    
 
    
    
    public TankProjectileControl() {
        TanksGame app = TanksGame.getApp();
        physicsSpace = app.getBulletAppState().getPhysicsSpace();
        physicsSpace.addCollisionListener(this);
    }
    
     /**
     * @inheritdoc
     */
    @Override
    public synchronized void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);

        if (spatial != null) {
            // Get the visual representation of the tank
            projectile = spatial.getUserData("entity");
        }
    }

    /**
     *
     * @param event
     */
    @Override
    public synchronized void collision(PhysicsCollisionEvent event) {
        if (physicsSpace == null || spatial == null) {
            return;
        }
        physicsControl = projectile.getPhysicsControl();
        if (event.getObjectA() == physicsControl || event.getObjectB() == physicsControl) {
            ParticleEmitter effect = EEffects.EXPLOSION.getEmitter();
            if (effect != null && spatial.getParent() != null) {
                effect.setLocalTranslation(spatial.getLocalTranslation());
                spatial.getParent().attachChild(effect);
                effect.emitAllParticles();
            }
            physicsSpace.remove(physicsControl);
            spatial.removeFromParent();
            //physicsSpace.removeCollisionListener(this);
            projectile.cleanup();
        }
    }

    @Override
    synchronized void controlUpdate(float tpf) {
        Vector3f oldLocation = spatial.getWorldTranslation();
        Vector3f newLocation = oldLocation.addLocal(projectile.getDirection());
        spatial.setLocalTranslation(newLocation);
    }
}
