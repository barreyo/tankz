
package GameModel;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author backman
 */
public class AirCallPowerup extends APowerup {
    private long activateTimerStart;
    private boolean isActive;
    
    private static final long END_TIME = 15000;
    private static final float INTERVAL = 0.1f;
    
    private float counter;
    
    private IPlayer player;

    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        
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
            counter += tpf;
            
            if (counter >= INTERVAL) {
                player.getVehicle().dropBomb(player);
                player.getVehicle().dropBomb(player);
                counter = 0;
            }
            if (System.currentTimeMillis() - activateTimerStart >= END_TIME) {
                isActive = false;
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
