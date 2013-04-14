
package GameModel.Game;

import GameModel.IObservable;
import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import GameModel.gameEntity.Powerup.APowerup;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class TanksGameModel implements ITanks, IObservable {
    private final List<IPlayer> players;
    private List<APowerup> powerups;
    private List<ISpawningPoints> spawningPoints;
    private float timer;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public TanksGameModel(List<IPlayer> players) {
        this.players = players;
    }
    
    public TanksGameModel(List<IPlayer> players, List <APowerup> powerups,
            List <ISpawningPoints> spawningPoints){
        this.players = players;
        this.powerups = powerups;
        this.spawningPoints = spawningPoints;
    }

    public Collection<IPlayer> getPlayers() { 
        List<IPlayer> pls = Collections.unmodifiableList(players);
        // Cast OK Player implements IPlayer
        return (Collection<IPlayer>) pls; 
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

    public void removePowerup(APowerup powerup) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addRandomPowerup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<APowerup> getPowerups() {
        List<APowerup> pUps = Collections.unmodifiableList(powerups);
        // Cast OK Powerup implements IPowerup
        return (Collection<APowerup>) pUps; 
    }

    public Collection<ISpawningPoints> getSpawningPoints() {
        List <ISpawningPoints> sp = Collections.unmodifiableList(spawningPoints);
        // Cast OK PlayerSpawningPoint and PowerupSpawningPoint implements ISpawningPoint
        return (Collection<ISpawningPoints>) sp;
    }


    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
}
