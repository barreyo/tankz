/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameControllers.entitycontrols;

import App.TanksAppAdapter;
import GameModel.IPowerup;
import GameView.gameEntity.PowerupEntity;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.RigidBodyControl;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Garpetun
 */
public class PowerupControl extends RigidBodyControl implements PhysicsCollisionListener, PropertyChangeListener {
    private PowerupEntity powerupEntity;
    private IPowerup powerupModel;
    private boolean isListening;

    
   public PowerupControl(PowerupEntity entity, IPowerup model) {
        super(entity.getCollisionShape(), model.getMASS());
        powerupEntity = entity;
        powerupModel = model;

        // We observe view
        entity.addObserver(this);
    }

    @Override
    public void collision(PhysicsCollisionEvent event) {
        if (space == null) {
            return;
        }
        if (event.getObjectA() instanceof TanksVehicleControl && event.getObjectB() == this
         || event.getObjectB() instanceof TanksVehicleControl && event.getObjectA() == this) {
            powerupModel.playerPickedUpPowerup();
        }
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(IPowerup.CLEANUP)) {
            powerupEntity.cleanup();
        } else if (pce.getPropertyName().equals(IPowerup.SHOW)) {
            TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(this);
            TanksAppAdapter.INSTANCE.addToPhysicsSpace(this);
            isListening = true;
            this.setEnabled(true);
        } else if (pce.getPropertyName().equals(IPowerup.HIDE)) {
            TanksAppAdapter.INSTANCE.removeFromPhysicsSpace(this);
            isListening = false;
        }
                
    }
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (enabled) {
            if (!isListening) {
                TanksAppAdapter.INSTANCE.removePhysiscsCollisionListener(this);
                this.setEnabled(false);
            }
            spatial.rotate(tpf * 0.8f, tpf * 0.7f, tpf * 0.5f);
        } 
    }

    public IPowerup getPowerup() {
        return powerupModel;
    }
}
