/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameControllers.entitycontrols;

import GameModel.gameEntity.Powerup.APowerup;
import GameView.gameEntity.PowerupEntity;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.RigidBodyControl;

/**
 *
 * @author Garpetun
 */
public class PowerupControl extends RigidBodyControl implements PhysicsCollisionListener {
    private PowerupEntity powerupEntity;
    private APowerup powerupModel;

    public void collision(PhysicsCollisionEvent event) {
        if (space == null) {
            return;
        }
        if (event.getObjectA() == this && event.getObjectB() instanceof TanksVehicleControl) {
            powerupModel.usePowerup(((TanksVehicleControl) event.getObjectB()).getPlayer());
        }
        if (event.getObjectB() == this && event.getObjectA() instanceof TanksVehicleControl) {
            powerupModel.usePowerup(((TanksVehicleControl) event.getObjectA()).getPlayer());
        }
            // Impact made, alert model
            // Highly temporary, this means the powerup gets used as soon as you hit it
            // Also this doesn't know which tank hit it
            // I believe that the tank should have a method, getPowerup()
//            powerupModel.usePowerup();
            
            // We dont have to listen for collisions any more
            space.removeCollisionListener(this);
     
            // Remove ourself as a rigid body control from physics space
            space.remove(this);
    }
}
