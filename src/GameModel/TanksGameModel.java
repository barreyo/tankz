
package GameModel;

import GameModel.IArmedVehicle.VehicleState;
import GameUtilities.Commands;
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
    private List<ISpawningPoint> playerSpawningPoints;
    private List<ISpawningPoint> powerupSpawningPoints;
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
            List <ISpawningPoint> powerupSpawningPoints, List <ISpawningPoint> playerSpawningPoints,
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
        pcs.firePropertyChange(Commands.END_GAME, null, null);
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
    public Collection<ISpawningPoint> getSpawningPoints() {
        List <ISpawningPoint> sp = Collections.unmodifiableList(powerupSpawningPoints);
        // Cast OK PlayerSpawningPoint and PowerupSpawningPoint implements ISpawningPoint
        return (Collection<ISpawningPoint>) sp;
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

    private float secondTimer = 0;
    
    /**
     * {@inheritdoc} 
     */
    @Override
    public void update(float tpf) {
        gameTimer -= tpf;
        secondTimer += tpf;
        if (secondTimer >= 1.0f) {
            pcs.firePropertyChange("Timer", null, null);
            secondTimer = 0;
        }
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
            respawnDestroyedVehicles();
            spawnPowerups();
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).update(tpf);
        }
        respawnDestroyedVehicles();
    }

    private void spawnPowerups() {
        Collections.shuffle(powerups);
        for (ISpawningPoint spawn : powerupSpawningPoints) {
            if (!spawn.isOccupied()) {
                for (IPowerup powerup : powerups) {
                    if (!powerup.isHeldByPlayer() && !powerup.isInWorld()) {
                        powerup.setPosition(spawn.getPosition());
                        powerup.showInWorld();
                        spawn.setOccupied(true);
                        spawn.setOccupier(powerup);
                        break;
                    }
                }
            }
        }
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void cleanup() {
        for (IPlayer player : players) {
            player.cleanup();
        }
        for (IPowerup powerup : powerups) {
            powerup.cleanup();
        }
    }

    private void spawnAllPlayers() {
        Collections.shuffle(playerSpawningPoints);
        int i = 0;
        for (IPlayer player : players) {
            ISpawningPoint spawn = playerSpawningPoints.get(i);
            IArmedVehicle vehicle = player.getVehicle();
            vehicle.setPosition(spawn.getPosition());
            vehicle.showInWorld();
            i++;
        }
    }

    @Override
    public void powerupPickedUp(IPowerup powerup) {
        for (ISpawningPoint spawn : powerupSpawningPoints) {
            if (spawn.isOccupied() && spawn.getOccupier() == powerup) {
                spawn.setOccupied(false);
                spawn.setOccupier(null);
            }
        }
    }
    
    

    private void respawnDestroyedVehicles() {
        Collections.shuffle(playerSpawningPoints);
        int i = 0;
        for (IPlayer player : players) {
            IArmedVehicle vehicle = player.getVehicle();
            if (vehicle.getVehicleState() == VehicleState.DESTROYED) {
                ISpawningPoint spawn = playerSpawningPoints.get(i);
                vehicle.setPosition(spawn.getPosition());
                vehicle.showInWorld();
                i++;
            }
        }
    }

    /**
     * {@inheritdoc} 
     */
    public float getGameTime() {
        return gameTimer;
    }
}
