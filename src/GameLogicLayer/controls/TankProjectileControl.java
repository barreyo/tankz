
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
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * 
 *
 * @author Daniel
 */
public class TankProjectileControl extends BaseControl implements PhysicsCollisionListener{
    private PhysicsSpace physicsSpace;
    private MissileProjectile projectile;
    private RigidBodyControl physicsControl;
    private ParticleEmitter effect;
    
    private boolean effectPlaying;
    private static final float EFFECT_END_TIME = 0.5f;
    private static final float MAX_LIFE_TIME = 4f;
    
    private float effectTimer;
    private float projectileLifeTimer;
    
    public TankProjectileControl() {
        TanksGame app = TanksGame.getApp();
        physicsSpace = app.getBulletAppState().getPhysicsSpace();
        physicsSpace.addCollisionListener(this);
        effect = EEffects.EXPLOSION.getEmitter();
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
        if (spatial == null || projectile == null) {
            return;
        }
        physicsControl = projectile.getPhysicsControl();
        if (event.getObjectA() == physicsControl || event.getObjectB() == physicsControl) {
            if (effect != null && spatial.getParent() != null) {
                effectPlaying = true;
                effect.setLocalTranslation(spatial.getLocalTranslation());
                spatial.getParent().attachChild(effect);
                effect.emitAllParticles();  
            }
            physicsSpace.remove(physicsControl);
            spatial.removeFromParent();
            projectile.cleanup();
            effect.addControl(this);
        }
    }

    @Override
    void controlUpdate(float tpf) {
        if(enabled){
            if (projectile != null && spatial != null) {
                Vector3f oldLocation = spatial.getWorldTranslation();
                Vector3f newLocation = oldLocation.addLocal(projectile.getDirection().normalizeLocal());
                spatial.setLocalTranslation(newLocation);
                projectileLifeTimer += tpf;
                if (projectileLifeTimer > MAX_LIFE_TIME) {
                    physicsSpace.remove(physicsControl);
                    spatial.removeFromParent();
                    projectile.cleanup();
                }
            }
            if (effectPlaying) {
                effectTimer += tpf;
                if (effectTimer > EFFECT_END_TIME) {
                    effect.removeFromParent();
                    effect.removeControl(this);
                }
            }
        }
    }
}
