
package GameControllers.entitycontrols;

import App.TanksAppAdapter;
import GameControllers.logic.SoundManager;
import GameModel.IDamageableObject;
import GameModel.IWorldObject;
import GameModel.LandmineModel;
import GameUtilities.Commands;
import GameView.Sounds.ESounds;
import GameView.gameEntity.LandmineEntity;
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
 * @author perthoresson
 */
public class LandmineControl extends AbstractControl implements PhysicsCollisionListener, PropertyChangeListener {
    
    private LandmineModel landmineModel;
    private LandmineEntity entity;
    private RigidBodyControl physicsControl;
    
    /**
     *
     * @param model
     * @param entity
     * @param physicsControl
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
     *
     * @param rm
     * @param vp
     */
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param spatial
     * @return
     */
    @Override
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
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
            if (objA == landmineModel && objB instanceof IDamageableObject) {
                physicsControl.setEnabled(false);
                entity.impact();
                landmineModel.impact();
                SoundManager.INSTANCE.play(ESounds.MISSILI_COLLISION_SOUND);
                landmineModel.doDamageOn((IDamageableObject)objB);
            } else if (objB == landmineModel && objA instanceof IDamageableObject) {
                physicsControl.setEnabled(false);
                entity.impact();
                landmineModel.impact();
                SoundManager.INSTANCE.play(ESounds.MISSILI_COLLISION_SOUND);
                landmineModel.doDamageOn((IDamageableObject)objA);
            } 
        }
    }

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
