package controller.entityControls;

import application.TanksAppAdapter;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import controller.managers.SoundManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import model.IDamageableObject;
import model.IExplodingProjectile;
import model.IWorldObject;
import utilities.Commands;
import utilities.Constants;
import view.entity.AExplodingEntity;
import view.sounds.ESounds;

/**
 * Control class for linear projectiles.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class LinearProjectileControl extends AbstractControl implements PhysicsCollisionListener, PropertyChangeListener {
    
    private AExplodingEntity entity;
    private IExplodingProjectile projectileModel;

    private RigidBodyControl physicsControl;
    
    /**
     * Creates a linear projectile control.
     * 
     * @param entity entity to be controlled.
     * @param projModel entity model.
     * @param physicsControl physics shape.
     */
    public LinearProjectileControl(AExplodingEntity entity, IExplodingProjectile projModel, RigidBodyControl physicsControl) {

        this.entity = entity;
        this.projectileModel = projModel;
        this.physicsControl = physicsControl;
        
        entity.addControl(physicsControl);
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(physicsControl);
        physicsControl.setEnabled(false);
        
        // We observe Model
        entity.addObserver(this);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String command = evt.getPropertyName();
        Object source = evt.getSource();
        if (source == projectileModel) {
            if (command.equals(Commands.SHOW)) {
                physicsControl.setEnabled(true);
                physicsControl.setLinearVelocity(new Vector3f(projectileModel.getLinearVelocity()));
            } else if (command.equals(Commands.HIDE)) {
                physicsControl.setEnabled(false);
            } else if (command.equals(Commands.CLEANUP)) {
                cleanup();
            }
        }
    }
    
    private void cleanup() {
        // Remove this as a control
        entity.removeControl(this);
        entity.removeControl(physicsControl);
        entity.removeObserver(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void controlUpdate(float tpf) {
        if (projectileModel.isShownInWorld()) {
            projectileModel.update(tpf);
            if (spatial != null) {
                projectileModel.setPosition((spatial.getWorldTranslation()));
            }
            if (physicsControl.isEnabled()) {
                physicsControl.setLinearVelocity((projectileModel.getLinearVelocity()));
            }
        }
    }

    /**
     * This is not used, should only be implemented in advance render use cases.
     */
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        // not used
    }

    /**
     * This is not used since we use MVC and do not only control visual spatials.
     * 
     * Will throw UnsupportedOperationException.
     * 
     * @throws UnsupportedOperationException since this functionality is not suppoerted.
     */
    @Override
    public Control cloneForSpatial(Spatial spatial) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collision(PhysicsCollisionEvent event) {
        if (event.getNodeA() != null && event.getNodeB() != null) {
            IWorldObject objA = event.getNodeA().getUserData(Constants.USER_DATA_MODEL);
            IWorldObject objB = event.getNodeB().getUserData(Constants.USER_DATA_MODEL);
            if (objA == projectileModel || objB == projectileModel) {
                physicsControl.setEnabled(false);
                entity.impact();
                projectileModel.impact();
                if (!SoundManager.INSTANCE.isSoundFXMuted()) {
                    SoundManager.INSTANCE.play(ESounds.MISSILE_COLLISION_SOUND);
                }
                if (objA instanceof IDamageableObject) {
                    projectileModel.doDamageOn((IDamageableObject)objA);
                } else if (objB instanceof IDamageableObject) {
                    projectileModel.doDamageOn((IDamageableObject)objB);
                }
            }
        }
    }
}