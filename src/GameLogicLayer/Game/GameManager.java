package GameLogicLayer.Game;

import GameModelLayer.Game.Game;
import GameModelLayer.Player.Player;
import GameModelLayer.gameEntity.Vehicle.IArmedVehicle;

/**
 *
 * @author Daniel
 */
public class GameManager {
    private Game gameModel;
    
    public GameManager(Game gameModel) {
        this.gameModel = gameModel;
    }
    
    public void createPlayer(String name, IArmedVehicle vehicle) {
        gameModel.addPlayer(new Player(name, vehicle));
    }
}
