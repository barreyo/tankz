package GameModel.Game;

import GameModel.Player.IPlayer;
import java.util.List;

/**
 * A factory for creating the TanksGameModel.
 * 
 * @author Daniel
 */
public final class TanksFactory {
    
    private TanksFactory() {}
    
    /**
     * Returns a new TanksGameModel.
     * @return a new TanksGameModel
     */
    public static TanksGameModel getTanks() {
        List<IPlayer> players = UserSettings.INSTANCE.getPlayers();
        return new TanksGameModel(players);
    }
}
