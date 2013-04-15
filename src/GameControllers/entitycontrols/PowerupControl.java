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

    
   public PowerupControl(PowerupEntity entity, IPowerup model) {
        powerupEntity = entity;
        powerupModel = model;
        
        // We observe view
        entity.addObserver(this);
    }
    
    public void collision(PhysicsCollisionEvent event) {
        if (space == null) {
            return;
        }
        if (event.getObjectA() == this && event.getObjectB() instanceof TanksVehicleControl) {
            ((TanksVehicleControl) event.getObjectB()).getPlayer().setPowerup(EPowerup.HASTE);
            powerupModel.removePowerup();
        }
        if (event.getObjectB() == this && event.getObjectA() instanceof TanksVehicleControl) {
            ((TanksVehicleControl) event.getObjectA()).getPlayer().setPowerup(EPowerup.HASTE);
            powerupModel.removePowerup();
        }

        // We dont have to listen for collisions any more
        space.removeCollisionListener(this);
        
        // Remove ourself as a rigid body control from physics space
        space.remove(this);
    }

    public void propertyChange(PropertyChangeEvent pce) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
