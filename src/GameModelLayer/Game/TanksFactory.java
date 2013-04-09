package GameModelLayer.Game;

import GameModelLayer.Game.TanksGameModel;
import GameModelLayer.Player.Player;
import GameModelLayer.gameEntity.Vehicle.IArmedVehicle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public final class TanksFactory {
    private static TanksFactory instance;
    
    private TanksFactory() {}
    
    public static synchronized TanksFactory getInstance() {
        if (instance == null) {
            instance = new TanksFactory();
        }
        return instance;
    }
    
    public static TanksGameModel getTanks() {
        List<Player> players = UserSettings.INSTANCE.getPlayers();
        return new TanksGameModel(players);
    }
}
