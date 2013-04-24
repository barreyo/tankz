package GameControllers.entitycontrols;

import App.TanksAppAdapter;
import GameModel.IPowerup;
import GameView.gameEntity.PowerupEntity;
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
public class PowerupControl extends AbstractControl implements PropertyChangeListener {
    private PowerupEntity powerupEntity;
    private IPowerup powerupModel;
    
    private RigidBodyControl physicsControl;
    
    public PowerupControl(PowerupEntity entity, IPowerup model, RigidBodyControl physicsControl) {
        powerupEntity = entity;
        powerupModel = model;
        
        this.physicsControl = physicsControl;
        
        // We observe view
        entity.addObserver(this);
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(IPowerup.SHOW)) {
            TanksAppAdapter.INSTANCE.addToPhysicsSpace(physicsControl);
        } else if (pce.getPropertyName().equals(IPowerup.HIDE)) {
            TanksAppAdapter.INSTANCE.removeFromPhysicsSpace(physicsControl);
        }
    }

    public IPowerup getPowerup() {
        return powerupModel;
    }

    @Override
    protected void controlUpdate(float tpf) {
        if (enabled) {
            powerupModel.update(tpf);
            spatial.rotate(tpf * 0.8f, tpf * 0.7f, tpf * 0.5f);
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
       // Should only be used by advance users
    }

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported");
    }
}
