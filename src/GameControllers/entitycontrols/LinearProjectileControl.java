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
import com.jme3.math.Vector3f;
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
        physicsControl.setEnabled(false);
        
        // We observe Model
        entity.addObserver(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Commands.END_OF_LIFETIME)) {
            TanksAppAdapter.INSTANCE.removeFromPhysicsSpace(physicsControl);
        } else if (evt.getPropertyName().equals(Commands.SHOW)) {
            physicsControl.setEnabled(true);
            physicsControl.setLinearVelocity(new Vector3f(projectileModel.getLinearVelocity()));
        } else if (evt.getPropertyName().equals(Commands.HIDE)) {
            physicsControl.setEnabled(false);
        }
    }

    @Override
    protected void controlUpdate(float tpf) {
        if (projectileModel.isInWorld()) {
            projectileModel.update(tpf);
            if (spatial != null) {
                projectileModel.updatePosition((spatial.getWorldTranslation()));
            }
            if (physicsControl.isEnabled()) {
                physicsControl.setLinearVelocity((projectileModel.getLinearVelocity()));
            }
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
        if (event.getNodeA() != null && event.getNodeB() != null) {
            IWorldObject objA = event.getNodeA().getUserData("Model");
            IWorldObject objB = event.getNodeB().getUserData("Model");
            if (objA == projectileModel || objB == projectileModel) {
                physicsControl.setEnabled(false);
                entity.impact();
                projectileModel.impact();
                SoundManager.INSTANCE.play(ESounds.MISSILI_COLLISION_SOUND);
            }
        }
    }
}