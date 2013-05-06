package GameControllers.entitycontrols;

import App.TanksAppAdapter;
import GameModel.IArmedVehicle;
import GameModel.IPlayer;
import GameModel.IPowerup;
import GameModel.IWorldObject;
import GameUtilities.Commands;
import GameView.gameEntity.PowerupEntity;
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
 * @author Garpetun
 */
public class PowerupControl extends AbstractControl implements PhysicsCollisionListener, PropertyChangeListener {
    private PowerupEntity powerupEntity;
    private IPowerup powerupModel;
    private RigidBodyControl physicsControl;
    
    /**
     *
     * @param entity
     * @param model
     * @param physicsControl
     */
    public PowerupControl(PowerupEntity entity, IPowerup model, RigidBodyControl physicsControl) {
        powerupEntity = entity;
        powerupModel = model;
        
        this.physicsControl = physicsControl;
        
        // We observe view
        entity.addObserver(this);
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(Commands.SHOW)) {
            TanksAppAdapter.INSTANCE.addToPhysicsSpace(physicsControl);
        }
    }

    @Override
    protected void controlUpdate(float tpf) {
        powerupModel.update(tpf);
        spatial.rotate(tpf * 0.8f, tpf * 0.7f, tpf * 0.5f);
    }


    /**
     *
     * @param rm
     * @param vp
     */
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
       // Should only be used by advance users
    }

    /**
     *
     * @param spatial
     * @return
     */
    @Override
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported");
    }

    /**
     *
     * @param event
     */
    @Override
    public void collision(PhysicsCollisionEvent event) {
        if (event.getNodeA() != null && event.getNodeB() != null) {
            IWorldObject objA = event.getNodeA().getUserData("Model");
            IWorldObject objB = event.getNodeB().getUserData("Model");
            if (objA == powerupModel && objB instanceof IArmedVehicle
                    || objB == powerupModel && objA instanceof IArmedVehicle) {
                TanksAppAdapter.INSTANCE.removeFromPhysicsSpace(physicsControl);
                powerupModel.powerupWasPickedUp();
                powerupEntity.hideFromWorld();
            }
        }
    }
}
