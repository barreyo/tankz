
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
public class TanksGameModel implements ITanks {
    private final List<Player> players;
    private List<IPowerUp> powerups;
    private List<ISpawningPoints> spawningPoints;
    private float timer;
    
    public TanksGameModel(List<Player> players) {
        this.players = players;
    }
    
    public TanksGameModel(List <Player> players, List <IPowerUp> powerups,
            List <ISpawningPoints> spawningPoints){
        this.players = players;
        this.powerups = powerups;
        this.spawningPoints = spawningPoints;
    }
    
    @SuppressWarnings("unchecked")
    public Collection<Player> getPlayers() { 
        List<Player> pls = Collections.unmodifiableList(players);
        // Cast OK Player implements IPlayer
        return (Collection<Player>) pls; 
    }

    public void startGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void endGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void pauseGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removePowerup(IPowerUp powerUp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addRandomPowerup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<IPowerUp> getPowerups() {
        List<IPowerUp> pUps = Collections.unmodifiableList(powerups);
        // Cast OK Powerup implements IPowerup
        return (Collection<IPowerUp>) pUps; 
    }

    public Collection<ISpawningPoints> getSpawningPoints() {
        List <ISpawningPoints> sp = Collections.unmodifiableList(spawningPoints);
        // Cast OK PlayerSpawningPoint and PowerupSpawningPoint implements ISpawningPoint
        return (Collection<ISpawningPoints>) sp;
    }
}
