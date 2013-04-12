package GameModel.Game;

import GameModel.Game.TanksGameModel;
import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import java.util.List;

/**
 *
 * @author Daniel
 */
public final class TanksFactory {
    
    private TanksFactory() {}
    
    public static TanksGameModel getTanks() {
        List<IPlayer> players = UserSettings.INSTANCE.getPlayers();
        return new TanksGameModel(players);
    }
}
