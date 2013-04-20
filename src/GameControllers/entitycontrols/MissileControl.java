package GameControllers.entitycontrols;

import GameControllers.logic.SoundManager;
import GameModel.IExplodingProjectile;
import GameView.Sounds.ESounds;
import GameView.gameEntity.MissileProjectileEntity;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.effect.ParticleEmitter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;

/**
 *
 * @author Daniel
 */
public class MissileControl extends RigidBodyControl implements PhysicsCollisionListener, PropertyChangeListener {
    private MissileProjectileEntity entity;
    private IExplodingProjectile projectileModel;
    private Collection<ParticleEmitter> effects;
    
    private boolean isListening;

    /**
     * Creates a tank projectile control.
     */
    public MissileControl(MissileProjectileEntity entity, IExplodingProjectile projModel) {
        super(entity.getCollisionShape(), projModel.getMass());

        this.entity = entity;
        this.projectileModel = projModel;
        
        isListening = true;
        
        // We observe view
        entity.addObserver(this);
        
        // Get effect
        effects = entity.getEffects();
    }

    public void collision(PhysicsCollisionEvent event) {
        if (space == null) {
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
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (enabled) {
            if (!isListening && space != null) {
                space.removeCollisionListener(this);
            }
            projectileModel.update(tpf);
        } 
    }

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