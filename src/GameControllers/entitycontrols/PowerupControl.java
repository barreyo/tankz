/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameControllers.entitycontrols;

import GameModel.gameEntity.Powerup.IPowerup;
import GameModel.gameEntity.Powerup.EPowerup;
import GameModel.gameEntity.Projectile.IExplodingProjectile;
import GameView.gameEntity.MissileProjectileEntity;
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
        
        isListening = true;
        
        // We observe view
        entity.addObserver(this);
    }
    
    public void collision(PhysicsCollisionEvent event) {
        System.out.println("COLLISION HAPPENED");
        if (space == null) {
            return;
        }
        if (event.getObjectA() instanceof TanksVehicleControl || event.getObjectB() instanceof TanksVehicleControl) {
            System.out.println("OMG TANKEN NUDDAR NÅGONTING!!!!");
        }
        if (event.getObjectA() == this || event.getObjectB() == this) {
            System.out.println("SOMETHING IS COLLIDING WITH BOX");
            powerupModel.removePowerup();
            
            // We dont have to listen for collisions any more
            isListening = false;
        }
    }

    public void propertyChange(PropertyChangeEvent pce) {
        System.out.println("POWERUPCONTROL: PROPERTY CHANGED");
        powerupEntity.cleanup();
    }
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (enabled) {
            if (!isListening && space != null) {
                space.removeCollisionListener(this);
            }
 //           powerupModel.update(tpf);
        } 
    }
}
