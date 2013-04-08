package GameLogicLayer.logic;

import GameModelLayer.Game.Game;
import GameModelLayer.Player.Player;
import GameModelLayer.gameEntity.Vehicle.IArmedVehicle;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class GameManager {
    private static GameManager instance;
    
    private Game gameModel;
    
    private GameManager() {
        this.gameModel = new Game();
    }
    
    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    
    public void createPlayer(String name, IArmedVehicle vehicle) {
        gameModel.addPlayer(new Player(name, vehicle));
    }
    
    public List<Player> getPlayers() {
        return gameModel.getPlayers();
    }
}
