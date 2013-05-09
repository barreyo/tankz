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
    
    float speedChangeCounter;
    float turningChangeCounter;
    boolean changeSpeed;
    boolean changeTurning;
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
            
            turningChangeCounter = turningChangeCounter + tpf;
            if (turningChangeCounter >= 1.8f) {
                changeTurning = !changeTurning;
                turningChangeCounter = 0;
                if (changeTurning) {
                    vehicle.steerLeft();
                } else {
                    vehicle.steerRight();
                }
            }
            
            if (System.currentTimeMillis() - activateTimerStart >= END_TIME) {
                vehicle.steerRight();
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
    
}
