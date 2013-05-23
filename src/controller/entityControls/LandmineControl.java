
package controller.entityControls;

import application.TanksAppAdapter;
import model.IDamageableObject;
import model.IWorldObject;
import model.LandmineModel;
import utilities.Commands;
import utilities.Constants;
import view.sounds.ESounds;
import view.entity.LandmineEntity;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import controller.managers.SoundManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Control class for a landmine.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class LandmineControl extends AbstractControl implements PhysicsCollisionListener, PropertyChangeListener {
    
    private LandmineModel landmineModel;
    private LandmineEntity entity;
    private RigidBodyControl physicsControl;
    
    /**
     * Instatiate a landmine control.
     * 
     * @param model model.
     * @param entity entity to be controlled.
     * @param physicsControl physics shape.
     */
    public LandmineControl (LandmineModel model, 
            LandmineEntity entity, RigidBodyControl physicsControl ){
        
        this.landmineModel = model;
        this.entity = entity;
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
    protected void controlUpdate(float tpf) {
        if (landmineModel.isShownInWorld()) {
            landmineModel.update(tpf);
            if (spatial != null) {
                landmineModel.setPosition((spatial.getWorldTranslation()));
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
            if (objA == landmineModel && objB instanceof IDamageableObject) {
                physicsControl.setEnabled(false);
                entity.impact();
                landmineModel.impact();
                if (!SoundManager.INSTANCE.isSoundFXMuted()) {
                    SoundManager.INSTANCE.play(ESounds.MISSILE_COLLISION_SOUND);
                }
                landmineModel.doDamageOn((IDamageableObject)objB);
            } else if (objB == landmineModel && objA instanceof IDamageableObject) {
                physicsControl.setEnabled(false);
                entity.impact();
                landmineModel.impact();
                if (!SoundManager.INSTANCE.isSoundFXMuted()) {
                    SoundManager.INSTANCE.play(ESounds.MISSILE_COLLISION_SOUND);
                }
                landmineModel.doDamageOn((IDamageableObject)objA);
            } 
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String command = evt.getPropertyName();
        Object source = evt.getSource();
        if (source == landmineModel) {
            if (command.equals(Commands.SHOW)) {
                physicsControl.setEnabled(true);
            } else if (command.equals(Commands.HIDE)) {
                physicsControl.setEnabled(false);
            }
        }
    }
    
}
