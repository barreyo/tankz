
package GameModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The model for the game state.
 * @author Daniel
 */
public class TanksGameModel implements ITanks {
    private final List<IPlayer> players;
    private List<IPowerup> powerups;
    private List<ISpawningPoints> playerSpawningPoints;
    private List<ISpawningPoints> powerupSpawningPoints;
    private GameSettings settings;
    
    // Time until game ends
    private float gameTimer;
    private float spawningTimer;
    
    private final float spawningIntervall;
    
    private final Random randomGenerator;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Instantiates the TanksGameModel.
     * 
     * @param players list of all the IPlayers in the game
     * @param powerups list of all th IPowerups in the game
     * @param spawningPoints list of all the ISpawningPoints in the game.
     */
    public TanksGameModel(List<IPlayer> players, List <IPowerup> powerups,
            List <ISpawningPoints> powerupSpawningPoints, List <ISpawningPoints> playerSpawningPoints,
            GameSettings settings){
        if (playerSpawningPoints.size() < players.size()) {
            throw new IllegalArgumentException("Not allowed to have fever playerspawningpoints than players");
        }
        this.players = players;
        this.powerups = powerups;
        this.powerupSpawningPoints = powerupSpawningPoints;
        this.playerSpawningPoints = playerSpawningPoints;
        this.settings = settings;
        gameTimer = settings.getGameTime();
        spawningIntervall = 5f;  //testing
        randomGenerator = new Random();
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
        spawnAllPlayers();
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void endGame() {
        // show stats
        pcs.firePropertyChange(END_GAME, null, null);
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
        List <ISpawningPoints> sp = Collections.unmodifiableList(powerupSpawningPoints);
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
        gameTimer -= tpf;
        // Check if someone has won
        if (gameTimer <= 0) {
            endGame();
        }
        for (IPlayer player : players) {
            if (player.getKills() >= settings.getKillsToWin()) {
                endGame();
            }
        }
        
        spawningTimer += tpf;
        if (spawningTimer >= spawningIntervall) {
            spawningTimer = 0;
            spawnPowerups();
        }
    }

    private void spawnPowerups() {
        for (ISpawningPoints spawn : powerupSpawningPoints) {
            if (!spawn.isInUse()) {
                IPowerup powerup = getRandomItem(powerups);
                if (!powerup.isHeldByPlayer()) {
                    powerup.setPosition(spawn.getPosition());
                    powerup.showPowerupInWorld();
                    spawn.setInUse(true);
                }
            }
        }
    }

    @Override
    public void cleanup() {
        for (IPlayer player : players) {
            player.cleanup();
        }
        for (IPowerup powerup : powerups) {
            powerup.cleanup();
        }
    }
    
    private <E> E getRandomItem(List<? extends E> collection) {
        int index = randomGenerator.nextInt(collection.size());
        E item = collection.get(index);
        return item;
    }

    private void spawnAllPlayers() {
        Collections.shuffle(playerSpawningPoints);
        int i = 0;
        for (IPlayer player : players) {
            ISpawningPoints spawn = playerSpawningPoints.get(i);
            IArmedVehicle vehicle = player.getVehicle();
            vehicle.setPosition(spawn.getPosition());
            vehicle.showInWorld();
            i++;
        }
    }
}
