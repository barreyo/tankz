/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameControllers.entitycontrols;

import App.TanksAppAdapter;
import GameControllers.logic.SoundManager;
import GameModel.IWorldObject;
import GameModel.LandmineModel;
import GameView.Sounds.ESounds;
import GameView.gameEntity.LandmineEntity;
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
 * @author perthoresson
 */
public class LandmineController extends AbstractControl
implements PhysicsCollisionListener, PropertyChangeListener {
    
    private LandmineModel landmineModel;
    private LandmineEntity entity;
    private RigidBodyControl physicsControl;
    
    public LandmineController (LandmineModel model, LandmineEntity entity,
            RigidBodyControl physicsControl ){
        
        this.landmineModel = model;
        this.entity = entity;
        this.physicsControl = physicsControl;
        
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(physicsControl);
        
        // We observe Model
        entity.addObserver(this);
    }
    
    
    @Override
    protected void controlUpdate(float tpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void collision(PhysicsCollisionEvent event) {
        IWorldObject objA = event.getNodeA().getUserData("Model");
        IWorldObject objB = event.getNodeB().getUserData("Model");
        if (objA == landmineModel || objB == landmineModel){
            TanksAppAdapter.INSTANCE.removeFromPhysicsSpace(physicsControl);
            SoundManager.INSTANCE.play(ESounds.MISSILI_COLLISION_SOUND);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
