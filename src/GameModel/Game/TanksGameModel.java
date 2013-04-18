
package GameModel.Game;

import GameModel.Player.IPlayer;
import GameModel.gameEntity.Powerup.IPowerup;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The model for the game state.
 * @author Daniel
 */
public class TanksGameModel implements ITanks {
    // Can be changed by UserSettings.
    private final List<IPlayer> players;
    private List<IPowerup> powerups;
    private List<ISpawningPoints> spawningPoints;
    private GameSettings settings;
    
    // Time until game ends
    private float timer;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Instantiates the TanksGameModel.
     * 
     * @param players list of all the players in the game
     */
    public TanksGameModel(List<IPlayer> players) {
        this.players = players;
    }
    
    /**
     * Instantiates the TanksGameModel.
     * 
     * @param players list of all the IPlayers in the game
     * @param powerups list of all th IPowerups in the game
     * @param spawningPoints list of all the ISpawningPoints in the game.
     */
    public TanksGameModel(List<IPlayer> players, List <IPowerup> powerups,
            List <ISpawningPoints> spawningPoints, GameSettings settings){
        this.players = players;
        this.powerups = powerups;
        this.spawningPoints = spawningPoints;
        this.settings = settings;
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public Collection<IPlayer> getPlayers() { 
        List<IPlayer> pls = Collections.unmodifiableList(players);
        // Cast OK Player implements IPlayer
        return (Collection<IPlayer>) pls; 
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void startGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void endGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void pauseGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void removePowerup(IPowerup powerup) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void addRandomPowerup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public Collection<IPowerup> getPowerups() {
        List<IPowerup> pUps = Collections.unmodifiableList(powerups);
        // Cast OK Powerup implements IPowerup
        return (Collection<IPowerup>) pUps; 
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public Collection<ISpawningPoints> getSpawningPoints() {
        List <ISpawningPoints> sp = Collections.unmodifiableList(spawningPoints);
        // Cast OK PlayerSpawningPoint and PowerupSpawningPoint implements ISpawningPoint
        return (Collection<ISpawningPoints>) sp;
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    
    /**
     * {@inheritdoc} 
     */
    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    @Override
    public void update(float tpf) {
        timer += tpf;
        // Check if someone has won
    }
}
