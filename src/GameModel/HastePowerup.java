
package GameModel;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import java.io.IOException;

/**
 *
 * @author Per
 */
public class HastePowerup extends APowerup{
    private float maxSpeed;
    private float accForce;
    
    private boolean isActive;
    
    private float activateTimer;
    private static final float END_TIME = 5f;
    
    private IPlayer player;

    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        IArmedVehicle vehicle = player.getVehicle();
        maxSpeed = vehicle.getMaxSpeed();
        accForce = vehicle.getAccelerationForce();
        vehicle.setMaxSpeed(maxSpeed * 3f);
        vehicle.setAccelerationForce(accForce * 5f);
        activateTimer = 0;
        isActive = true;
    }
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (isActive) {
            activateTimer += tpf;
            if (activateTimer >= END_TIME) {
                isActive = false;
                IArmedVehicle vehicle = player.getVehicle();
                vehicle.resetSpeedValues();
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
