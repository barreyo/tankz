package GameControllers.entitycontrols;

import App.TanksAppAdapter;
import GameControllers.logic.SoundManager;
import GameModel.IExplodingProjectile;
import GameModel.IWorldObject;
import GameModel.MissileModel;
import GameUtilities.Commands;
import GameView.Sounds.ESounds;
import GameView.gameEntity.MissileEntity;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.PhysicsCollisionObject;
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
 *
 * @author Daniel
 */
public class HomingProjectileControl extends AbstractControl implements PhysicsCollisionListener, PropertyChangeListener {

    private MissileEntity entity;
    private MissileModel projectileModel;
    private RigidBodyControl physicsControl;
    private boolean hasAggro;
    private Spatial target;
    private GhostControl aggroGhost;

    /**
     * Creates a tank projectile control.
     */
    public HomingProjectileControl(MissileEntity entity, MissileModel projModel,
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

    @Override
    public void collision(PhysicsCollisionEvent event) {
        if (event.getNodeA() != null && event.getNodeB() != null) {
            IWorldObject objA = event.getNodeA().getUserData("Model");
            IWorldObject objB = event.getNodeB().getUserData("Model");
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
                SoundManager.INSTANCE.play(ESounds.MISSILI_COLLISION_SOUND);
            }
        }
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Commands.SHOW)) {
            this.target = null;
            hasAggro = false;
            physicsControl.setEnabled(true);
            aggroGhost.setEnabled(true);
            physicsControl.setLinearVelocity(new Vector3f(projectileModel.getLinearVelocity()));
        } else if (evt.getPropertyName().equals(Commands.HIDE)) {
            physicsControl.setEnabled(false);
            aggroGhost.setEnabled(false);
        }
    }

    IExplodingProjectile getProjectile() {
        return projectileModel;
    }

    @Override
    protected void controlUpdate(float tpf) {
        if (projectileModel.isInWorld()) {
            projectileModel.update(tpf);
            if (spatial != null) {
                projectileModel.updatePosition((spatial.getWorldTranslation()));
            }
            if (hasAggro && target != null) {
                projectileModel.setAttackTarget(target.getWorldTranslation().clone());
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
}
