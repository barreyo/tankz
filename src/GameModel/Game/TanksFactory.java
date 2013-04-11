package GameModel.Game;

import GameModel.Game.TanksGameModel;
import GameModel.Player.Player;
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
