package GameModelLayer.Game;

import GameModelLayer.Game.TanksGameModel;
import GameModelLayer.Player.Player;
import java.util.List;

/**
 *
 * @author Daniel
 */
public final class TanksFactory {
    
    private TanksFactory() {}
    
    public static TanksGameModel getTanks() {
        List<Player> players = UserSettings.INSTANCE.getPlayers();
        return new TanksGameModel(players);
    }
}
