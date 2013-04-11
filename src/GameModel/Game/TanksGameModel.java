
package GameModel.Game;

import GameModel.Player.Player;
import GameModel.gameEntity.Powerup.IPowerUp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class TanksGameModel {
    private final List<Player> players;
    private List<IPowerUp> powerups;
    private List<ISpawningPoints> spawningPoints;
    private float timer;
    
    public TanksGameModel(List<Player> players) {
        this.players = players;
    }
    
    @SuppressWarnings("unchecked")
    public Collection<Player> getPlayers() { 
        List<Player> pls = Collections.unmodifiableList(players);
        // Cast OK Player implements IPlayer
        return (Collection<Player>) pls; 
    }
}
