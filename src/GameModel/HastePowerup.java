
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
    private float activateTimer;
    private boolean isActive;
    
    private static final float END_TIME = 5f;
    
    private IPlayer player;

    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        IArmedVehicle vehicle = player.getVehicle();
        maxSpeed = vehicle.getDefaultMaxSpeed();
        accForce = vehicle.getDefaultAccelerationForce();
        vehicle.setMaxSpeed(maxSpeed * 3f);
        vehicle.setAccelerationForce(accForce * 5f);
        activateTimer = 0;
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
            System.out.println("vajs");
            activateTimer += tpf;
            if (activateTimer >= END_TIME) {
                isActive = false;
                IArmedVehicle vehicle = player.getVehicle();
                vehicle.resetSpeedValues();
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
