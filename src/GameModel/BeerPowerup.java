/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import java.io.IOException;

/**
 *
 * @author Garpetun
 */
public class BeerPowerup extends APowerup {
    
    private float speedChangeCounter;
    private float turningLeftCounter;
    private float turningRightCounter;
    private float shootingCounter;
    private boolean changeSpeed;
    private boolean turnLeft;
    private boolean turnRight;
    private float maxSpeed;
    private long activateTimerStart;
    private boolean isActive;
    
    private static final long END_TIME = 10000;
    
    private IPlayer player;
    private IArmedVehicle vehicle;

    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        vehicle = player.getVehicle();
        maxSpeed = vehicle.getDefaultMaxSpeed();
        vehicle.setMaxSpeed(maxSpeed / 5f);
        activateTimerStart = System.currentTimeMillis();
        isActive = true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (isActive) {
            
            speedChangeCounter = speedChangeCounter + tpf;
            if (speedChangeCounter >= 0.6f) {
                changeSpeed = !changeSpeed;
                speedChangeCounter = 0;
                if (changeSpeed) {
                    vehicle.setMaxSpeed(maxSpeed * 5f);
                } else {
                    vehicle.setMaxSpeed(maxSpeed / 5f);
                }
            }
            
            turningLeftCounter = turningLeftCounter + tpf;
            if (turningLeftCounter >= 1.8f) {
                turnLeft = !turnLeft;
                turningLeftCounter = 0;
                if (turnLeft) {
                    vehicle.steerLeft();
                } else {
                    vehicle.steerRight();
                }
            }
           
            turningRightCounter = turningRightCounter + tpf;
            if (turningRightCounter >= 0.9f) {
                turnRight = !turnRight;
                turningRightCounter = 0;
                if (turnRight) {
                    vehicle.steerRight();
                } else {
                    vehicle.steerLeft();
                }
            }
            
            shootingCounter = shootingCounter + tpf;
            if (shootingCounter >= 2.5) {
                shootingCounter = 0;
                vehicle.shoot();
            }
            
            if (System.currentTimeMillis() - activateTimerStart >= END_TIME) {
                isActive = false;
                vehicle.resetSpeedValues();
                player = null;
            }
        }
    }
    
    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean isActive() {
        return isActive;
    }
    
}
