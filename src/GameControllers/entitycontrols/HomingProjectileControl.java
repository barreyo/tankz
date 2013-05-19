package GameControllers.entitycontrols;

import App.TanksAppAdapter;
import GameControllers.gameManagers.SoundManager;
import GameModel.IDamageableObject;
import GameModel.IWorldObject;
import GameModel.MissileModel;
import GameUtilities.Commands;
import GameUtilities.Constants;
import GameView.Sounds.ESounds;
import GameView.gameEntity.AExplodingEntity;
import GameView.gameEntity.AGameEntity;
import GameView.gameEntity.MissileEntity;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.GhostControl;
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
 * Control for the homing missile, a missile that follows the target.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class HomingProjectileControl extends AbstractControl implements PhysicsCollisionListener, PropertyChangeListener {

    private AExplodingEntity entity;
    private MissileModel projectileModel;
    private RigidBodyControl physicsControl;
    private boolean hasAggro;
    private Spatial target;
    private GhostControl aggroGhost;

    /**
     * Creates a tank projectile control.
     * 
     * @param entity entity to be controlled.
     * @param projModel model for the entity.
     * @param physicsControl physics shape.
     * @param aggroControl target searching controller.
     */
    public HomingProjectileControl(AExplodingEntity entity, MissileModel projModel,
            RigidBodyControl physicsControl, GhostControl aggroControl) {

        this.entity = entity;
        this.projectileModel = projModel;
        this.physicsControl = physicsControl;
        this.aggroGhost = aggroControl;

        entity.addControl(physicsControl);
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(physicsControl);
        physicsControl.setEnabled(false);
        
        entity.addControl(aggroControl);
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(aggroControl);
        aggroGhost.setEnabled(false);

        // We observe view
        entity.addObserver(this);
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
                Spatial targetSpat = null;
                if (event.getObjectA() == aggroGhost) {
                    targetSpat = event.getNodeB();
                }
                if (event.getObjectB() == aggroGhost) {
                    targetSpat = event.getNodeA();
                }
                if (targetSpat != null) {
                    if (this.target == null || targetSpat.getWorldTranslation().distance(projectileModel.getPosition())
                            < this.target.getWorldTranslation().distance(projectileModel.getPosition())) {
                        hasAggro = true;
                        this.target = targetSpat;
                        projectileModel.setAttackTarget(target.getWorldTranslation().clone());
                    }
                    return;
                }
                physicsControl.setEnabled(false);
                aggroGhost.setEnabled(false);
                projectileModel.impact();
                entity.impact();
                if (!SoundManager.INSTANCE.isSoundFXMuted()) {
                    SoundManager.INSTANCE.play(ESounds.MISSILI_COLLISION_SOUND);
                }
                if (objA instanceof IDamageableObject) {
                    projectileModel.doDamageOn((IDamageableObject)objA);
                } else if (objB instanceof IDamageableObject) {
                    projectileModel.doDamageOn((IDamageableObject)objB);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        String command = evt.getPropertyName();
        Object source = evt.getSource();
        if (source == projectileModel) {
            if (command.equals(Commands.SHOW)) {
                this.target = null;
                hasAggro = false;
                physicsControl.setEnabled(true);
                aggroGhost.setEnabled(true);
                physicsControl.setLinearVelocity(new Vector3f(projectileModel.getLinearVelocity()));
            } else if (command.equals(Commands.HIDE)) {
                physicsControl.setEnabled(false);
                aggroGhost.setEnabled(false);
            }
        }
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
            if (hasAggro && target != null) {
                projectileModel.setAttackTarget(target.getWorldTranslation().clone());
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
}
