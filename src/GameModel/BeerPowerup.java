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
    
    float tpfCounter;
    boolean goFast;
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
            tpfCounter = tpfCounter + tpf;
            if (tpfCounter >= 0.6f) {
                goFast = !goFast;
                tpfCounter = 0;
                if (goFast) {
                    vehicle.setMaxSpeed(maxSpeed * 5f);
                } else {
                    vehicle.setMaxSpeed(maxSpeed / 5f);
                }
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
    
}
