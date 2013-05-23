
package model;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import java.io.IOException;

/**
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class HastePowerup extends APowerup {
    private float maxSpeed;
    private long activateTimerStart;
    private boolean isActive;
    
    private static final long END_TIME = 10000;
    
    private IPlayer player;

    /**
     * {@inheritDoc}
     * @param player 
     */
    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        IArmedVehicle vehicle = player.getVehicle();
        maxSpeed = vehicle.getDefaultMaxSpeed();
        vehicle.setMaxSpeed(maxSpeed * 3f);
        activateTimerStart = System.currentTimeMillis();
        isActive = true;
        vehicle.toggleFlame(true);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (isActive) {
            if (System.currentTimeMillis() - activateTimerStart >= END_TIME) {
                isActive = false;
                IArmedVehicle vehicle = player.getVehicle();
                vehicle.toggleFlame(false);
                vehicle.resetSpeedValues();
                player = null;
                isHeldByPlayer = false;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
