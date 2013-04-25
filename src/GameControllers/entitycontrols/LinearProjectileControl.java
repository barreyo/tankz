package GameControllers.entitycontrols;

import App.TanksAppAdapter;
import GameControllers.logic.SoundManager;
import GameModel.IExplodingProjectile;
import GameModel.IWorldObject;
import GameUtilities.Commands;
import GameView.Sounds.ESounds;
import GameView.gameEntity.CanonBallEntity;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
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
public class LinearProjectileControl extends AbstractControl implements PhysicsCollisionListener, PropertyChangeListener {
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
        entity.addObserver(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Commands.END_OF_LIFETIME)) {
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

    @Override
    public void collision(PhysicsCollisionEvent event) {
        IWorldObject objA = event.getNodeA().getUserData("Model");
        IWorldObject objB = event.getNodeB().getUserData("Model");
        if (objA == projectileModel || objB == projectileModel) {
            TanksAppAdapter.INSTANCE.removeFromPhysicsSpace(physicsControl);
            projectileModel.impact();
            entity.impact();
            SoundManager.INSTANCE.play(ESounds.MISSILI_COLLISION_SOUND);
        }
    }
}