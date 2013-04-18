/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel.gameEntity.Powerup;

import GameModel.Player.IPlayer;
import GameModel.gameEntity.Vehicle.IArmedVehicle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Per
 */
public class HastePowerup extends APowerup{
    private float maxSpeed;
    private float accForce;

    @Override
    public void usePowerup(IPlayer player) {
        IArmedVehicle vehicle = player.getVehicle();
        maxSpeed = vehicle.getMaxSpeed();
        accForce = vehicle.getAccelerationForce();
        
        vehicle.setMaxSpeed(maxSpeed * 5f);
        vehicle.setAccelerationForce(accForce * 5f);
        
        startPowerupActiveThreadForPlayer(player);
    }
    
    private void startPowerupActiveThreadForPlayer(final IPlayer player){
        new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(5000);
                    IArmedVehicle vehicle = player.getVehicle();
                    vehicle.setMaxSpeed(maxSpeed);
                    vehicle.setAccelerationForce(accForce);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HastePowerup.class.getName()).log(Level.SEVERE, "Powerup thread interrupted", ex);
                }
            }
        }).start();
    }
}
