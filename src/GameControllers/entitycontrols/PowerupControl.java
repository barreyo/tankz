package GameControllers.entitycontrols;

import App.TanksAppAdapter;
import GameModel.IArmedVehicle;
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
 * Control class for the powerup entity.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class PowerupControl extends AbstractControl implements PhysicsCollisionListener, PropertyChangeListener {
    
    private PowerupEntity powerupEntity;
    private IPowerup powerupModel;
    private RigidBodyControl physicsControl;
    
    /**
     * Instatiate a control for the powerup entity.
     * 
     * @param entity entity to be controlled.
     * @param model model for the entity.
     * @param physicsControl physics control.
     */
    public PowerupControl(PowerupEntity entity, IPowerup model, RigidBodyControl physicsControl) {
        powerupEntity = entity;
        powerupModel = model;
        
        this.physicsControl = physicsControl;
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(physicsControl);
        physicsControl.setEnabled(false);
        
        // We observe view
        entity.addObserver(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void propertyChange(PropertyChangeEvent pce) {
        if (pce.getSource() == powerupModel && pce.getPropertyName().equals(Commands.SHOW)) {
            physicsControl.setEnabled(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void controlUpdate(float tpf) {
        powerupModel.update(tpf);
        spatial.rotate(tpf * 0.8f, tpf * 0.7f, tpf * 0.5f);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        // never used
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collision(PhysicsCollisionEvent event) {
        if (event.getNodeA() != null && event.getNodeB() != null) {
            IWorldObject objA = event.getNodeA().getUserData("Model");
            IWorldObject objB = event.getNodeB().getUserData("Model");
            if (objA == powerupModel && objB instanceof IArmedVehicle
                    || objB == powerupModel && objA instanceof IArmedVehicle) {
                physicsControl.setEnabled(false);
                powerupModel.powerupWasPickedUp();
                powerupEntity.hideFromWorld();
            }
        }
    }
}
