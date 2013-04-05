package GameLogicLayer.player;

import GameLogicLayer.viewPort.EViewPorts;
import GameModelLayer.Player.Player;
import java.util.EnumMap;

/**
 *
 * @author Daniel
 */
public class PlayerInputManager {
    
    private EnumMap<EPlayerInputs, Player> views = new EnumMap<EPlayerInputs, Player>(EPlayerInputs.class);
    
}
