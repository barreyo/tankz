package GameControllers.entitycontrols;

import App.TanksAppAdapter;
import GameControllers.logic.SoundManager;
import GameModel.IExplodingProjectile;
import GameModel.MissileModel;
import GameView.Sounds.ESounds;
import GameView.gameEntity.MissileEntity;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.effect.ParticleEmitter;
import com.jme3.scene.Spatial;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;

/**
 *
 * @author Daniel
 */
public class HomingProjectileControl extends RigidBodyControl implements PhysicsCollisionListener, PropertyChangeListener {

    private MissileEntity entity;
    private MissileModel projectileModel;
    private Collection<ParticleEmitter> effects;
    
    private boolean isListening;
    
    private boolean hasAggro;
    private Spatial target;
    private GhostControl aggroGhost;
    
       // temp solution
    private TanksVehicleControl sender;

    /**
     * Creates a tank projectile control.
     */
    public HomingProjectileControl(MissileEntity entity, MissileModel projModel, TanksVehicleControl sender) {
        super(entity.getCollisionShape(), projModel.getMass());

        this.entity = entity;
        this.projectileModel = projModel;
        this.sender = sender;
        
        isListening = true;
        
        // We observe view
        entity.addObserver(this);

        // Get effect
        effects = entity.getEffects();
    }
    
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);

        if (spatial != null) {
            aggroGhost = new GhostControl(new SphereCollisionShape(100));
            aggroGhost.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_03);
            aggroGhost.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_02);
            spatial.addControl(aggroGhost);
            TanksAppAdapter.INSTANCE.addToPhysicsSpace(aggroGhost);
        }
    }
    
    public void collision(PhysicsCollisionEvent event) {
        if (spatial == null || space == null || event.getNodeA() == null || event.getNodeB() == null) {
            return;
        }
        if (event.getObjectA() == this || event.getObjectB() == this) {
            // Impact made, alert model
            projectileModel.impact();
            
            // We dont have to listen for collisions any more
            isListening = false;
     
            // Remove ourself as a rigid body control from physics space
            space.remove(this);
            
            // Now we control the effects instead
            for (ParticleEmitter effect : effects) {
                effect.addControl(this);
            }
            
            SoundManager.INSTANCE.play(ESounds.MISSILI_COLLISION_SOUND);  
        }

        PhysicsCollisionObject object = null;
        Spatial target = null;
        if (event.getObjectA() == aggroGhost && event.getObjectB() != sender) {
            object = event.getObjectA();
            target = event.getNodeB();
        }
        if (event.getObjectB() == aggroGhost && event.getObjectA() != sender) {
            object = event.getObjectB();
            target = event.getNodeA();
        }
        if (object != null && target != null && object == aggroGhost) {
            if (this.target == null || target.getWorldTranslation().distance(projectileModel.getPosition())
                    < this.target.getWorldTranslation().distance(projectileModel.getPosition())) {
                hasAggro = true;
                this.target = target;
            }
        }
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (enabled) {
            if (!isListening && space != null) {
                space.removeCollisionListener(this);
            }
            if (spatial != null) {
                projectileModel.updatePosition(spatial.getWorldTranslation().clone());
            }
            projectileModel.update(tpf);
            if (hasAggro && target != null) {
                projectileModel.moveTo(target.getWorldTranslation().clone());
            }
            this.setLinearVelocity(projectileModel.getLinearVelocity());
        } 
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IExplodingProjectile.END_OF_LIFETIME)) {
            if (space != null) {
                space.removeCollisionListener(this);
                space.remove(this);
                entity.removeObserver(this);
            }
        } else if (evt.getPropertyName().equals(IExplodingProjectile.EXPLOSION_FINISHED)) {
            for (ParticleEmitter effect : effects) {
                effect.removeControl(this);
            }
            entity.removeObserver(this);
        } 
    }

    IExplodingProjectile getProjectile() {
        return projectileModel;
    }
}
