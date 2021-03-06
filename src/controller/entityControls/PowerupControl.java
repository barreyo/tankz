package controller.entityControls;

import application.TanksAppAdapter;
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
import model.IArmedVehicle;
import model.IPowerup;
import model.IWorldObject;
import utilities.Commands;
import utilities.Constants;
import view.entity.PowerupEntity;

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
        } else if (pce.getSource() == powerupModel && pce.getPropertyName().equals(Commands.CLEANUP)) {
            cleanup();
        }
    }
    
    private void cleanup() {
        // Remove this as a control
        powerupEntity.removeControl(this);
        powerupEntity.removeControl(physicsControl);
        powerupEntity.removeObserver(this);
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
            if (objA == powerupModel && objB instanceof IArmedVehicle
                    || objB == powerupModel && objA instanceof IArmedVehicle) {
                physicsControl.setEnabled(false);
                powerupModel.powerupWasPickedUp();
                powerupModel.hideFromWorld();
            }
        }
    }
}
