
package GameModel;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import java.io.IOException;

/**
 *
 * @author Garpetun
 */
public class MissilePowerup extends APowerup {

    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);

        
        player.getVehicle().shoot();
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
