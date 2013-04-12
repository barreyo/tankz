
package GameControllers.entitycontrols;

import GameUtilities.TankAppAdapter;
import GameView.effects.EEffects;
import GameView.gameEntity.MissileProjectileEntity;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.effect.ParticleEmitter;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 * A control for a tank projectile.
 *
 * @author Daniel
 */
public class TankProjectileControl extends BaseControl implements PhysicsCollisionListener{
    private PhysicsSpace physicsSpace;
    private MissileProjectileEntity projectile;
    private RigidBodyControl physicsControl;
    private ParticleEmitter effect;
    
    private boolean effectShowing;
    private static final float EFFECT_END_TIME = 0.5f;
    private static final float MAX_LIFE_TIME = 4f;
    
    private float effectTimer;
    private float projectileLifeTimer;
    
    /**
     * Creates a tank projectile control.
     */
    public TankProjectileControl() {
       physicsSpace = TankAppAdapter.INSTANCE.getPhysicsSpace();
       effect = EEffects.EXPLOSION.getEmitter();
    }
    
     /**
     * @inheritdoc
     */
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);

        if (spatial != null) {
            // Get the visual representation of the tank
            projectile = spatial.getUserData("entity");
        }
        
        initControl();
    }
    
    private void addPhysicsControl() {
        physicsControl = new RigidBodyControl(projectile.getCollisionShape(), 0.001f);
        physicsControl.setCcdMotionThreshold(0.1f);
        physicsControl.setKinematic(true);
        physicsSpace.addCollisionListener(this);
        projectile.addControl(physicsControl);
        TankAppAdapter.INSTANCE.addToPhysicsSpace(physicsControl);
    }

    private void initControl() {
        if (physicsControl == null) {
            addPhysicsControl();
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
        if (event.getObjectA() == physicsControl || event.getObjectB() == physicsControl) {
            if (effect != null && spatial.getParent() != null) {
                effectShowing = true;
                effect.setLocalTranslation(spatial.getLocalTranslation());
                spatial.getParent().attachChild(effect);
                effect.emitAllParticles();  
            }
            physicsSpace.remove(physicsControl);
            spatial.removeFromParent();
            this.cleanup();
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
                    if (physicsControl != null) {
                        physicsSpace.remove(physicsControl);
                    }
                    spatial.removeFromParent();
                    this.cleanup();
                    physicsSpace.removeCollisionListener(this);
                }
            }
            if (effectShowing) {
                effectTimer += tpf;
                if (effectTimer > EFFECT_END_TIME) {
                    effect.removeFromParent();
                    effect.removeControl(this);
                    physicsSpace.removeCollisionListener(this);
                }
            }
        }
    }
}
