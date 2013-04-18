/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel.gameEntity.Powerup;

import GameModel.Player.IPlayer;
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
        maxSpeed = player.getVehicle().getMaxSpeed();
        accForce = player.getVehicle().getAccelerationForce();
        
        player.getVehicle().setMaxSpeed(player.getVehicle().getMaxSpeed()*1.5f);
        player.getVehicle().setAccelerationForce(player.getVehicle().
                getAccelerationForce()*1.5f);
    }
    
    private void createDelayThread(final IPlayer player){
        new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(5000);
                    player.getVehicle().setMaxSpeed(maxSpeed);
                    player.getVehicle().setAccelerationForce(accForce);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HastePowerup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
}
