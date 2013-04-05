package GameLogicLayer.viewPort;

import GameLogicLayer.util.IManager;
import GameModelLayer.Player.Player;
import java.util.EnumMap;

/**
 *
 * @author Daniel
 */
public class ViewPortManager implements IManager {
    
    private EnumMap<EViewPorts, Player> views = new EnumMap<EViewPorts, Player>(EViewPorts.class);

    public void load(int map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void cleanup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
