
package GameModelLayer.Game;

import GameModelLayer.Player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Game {
    private List<Player> players = new ArrayList<Player>();
    
    public void addPlayer(Player player) {
        players.add(player);
    }
    
    public void removePlayer(Player player) {
        players.remove(player);
    }
    
    public List<Player> getPlayers() {
        return new ArrayList<Player>(players);
    }
    
    public int getNumberOfPlayers() {
        return players.size();
    }

    
    
}
