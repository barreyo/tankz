package GameControllers.entitycontrols;

import GameModel.gameEntity.Projectile.IExplodingProjectile;
import GameView.gameEntity.MissileProjectileEntity;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.effect.ParticleEmitter;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 *
 * @author Daniel
 */
public class MissileControl extends RigidBodyControl implements PhysicsCollisionListener, PropertyChangeListener {
    private MissileProjectileEntity entity;
    private IExplodingProjectile projectileModel;
    private ParticleEmitter effect;

    /**
     * Creates a tank projectile control.
     */
    public MissileControl(MissileProjectileEntity entity, IExplodingProjectile projModel) {
        super(entity.getCollisionShape(), projModel.getMass());

        this.entity = entity;
        this.projectileModel = projModel;
        
        // We observe view
        entity.addObserver(this);
        
        // Get effect
        effect = entity.getEffect();
    }

    public void collision(PhysicsCollisionEvent event) {
        if (space == null) {
            return;
        }
        if (event.getObjectA() == this || event.getObjectB() == this) {
            // Impact made, alert model
            projectileModel.impact();
            
            // We dont have to listen for collisions any more
            space.removeCollisionListener(this);
     
            // Remove ourself as a rigid body control from physics space
            space.remove(this);
            
            // Now we control the effect instead
            effect.addControl(this);
        }
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (enabled) {
            projectileModel.update(tpf);
        }
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Reading not supported.");
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Saving not supported.");
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IExplodingProjectile.END_OF_LIFETIME)) {
            if (space != null) {
                space.removeCollisionListener(this);
                space.remove(this);
                entity.removeObserver(this);
            }
        } else if (evt.getPropertyName().equals(IExplodingProjectile.EXPLOSION_FINISHED)) {
            effect.removeControl(this);
            entity.removeObserver(this);
        }
    }
}
