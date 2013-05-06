
package GameModel;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import java.io.IOException;

/**
 * Is broken
 * @author Per
 */
public class HastePowerup extends APowerup{
    private float maxSpeed;
    private float accForce;
    private long activateTimerStart;
    private boolean isActive;
    
    private static final long END_TIME = 5000;
    
    private IPlayer player;

    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        IArmedVehicle vehicle = player.getVehicle();
        maxSpeed = vehicle.getDefaultMaxSpeed();
        accForce = vehicle.getDefaultAccelerationForce();
        vehicle.setMaxSpeed(maxSpeed * 2f);
        vehicle.setAccelerationForce(accForce * 2f);
        activateTimerStart = System.currentTimeMillis();
        isActive = true;
    }
    
    /**
     *
     * @param tpf
     */
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (isActive) {
            if (System.currentTimeMillis() - activateTimerStart >= END_TIME) {
                isActive = false;
                IArmedVehicle vehicle = player.getVehicle();
                vehicle.resetSpeedValues();
                player = null;
            }
        }
    }

    /**
     *
     * @param ex
     * @throws IOException
     */
    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param im
     * @throws IOException
     */
    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
