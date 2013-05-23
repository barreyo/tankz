
package model;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import java.io.IOException;

/**
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class MissilePowerup extends APowerup {
    /**
     * {@inheritDoc}
     */
    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        
        player.getVehicle().shootMissile(player);
        isHeldByPlayer = false;
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
