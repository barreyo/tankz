package GameControllers.entitycontrols;

import App.TanksAppAdapter;
import GameControllers.logic.SoundManager;
import GameModel.IExplodingProjectile;
import GameView.Sounds.ESounds;
import GameView.gameEntity.CanonBallEntity;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Daniel
 */
public class LinearProjectileControl extends AbstractControl implements PropertyChangeListener {
    private CanonBallEntity entity;
    private IExplodingProjectile projectileModel;

    private RigidBodyControl physicsControl;
    /**
     * Creates a tank projectile control.
     */
    public LinearProjectileControl(CanonBallEntity entity, IExplodingProjectile projModel, RigidBodyControl physicsControl) {

        this.entity = entity;
        this.projectileModel = projModel;
        this.physicsControl = physicsControl;
       
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(physicsControl);
        
        // We observe Model
        projModel.addObserver(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IExplodingProjectile.IMPACT_MADE)) {
            // Remove ourself as a rigid body control from physics space
            TanksAppAdapter.INSTANCE.removeFromPhysicsSpace(physicsControl);
      
            SoundManager.INSTANCE.play(ESounds.MISSILI_COLLISION_SOUND);
        } else if (evt.getPropertyName().equals(IExplodingProjectile.END_OF_LIFETIME)) {
            TanksAppAdapter.INSTANCE.removeFromPhysicsSpace(physicsControl);
        } 
    }

    @Override
    protected void controlUpdate(float tpf) {
        if (enabled) {
            projectileModel.update(tpf);
            if (spatial != null) {
                projectileModel.updatePosition(spatial.getWorldTranslation());
            }
            physicsControl.setLinearVelocity(projectileModel.getLinearVelocity());
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}