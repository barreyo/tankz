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
 *
 * @author Daniel
 */
public class TanksGameModel implements ITanks {

    private final List<IPlayer> players;
    private List<IPowerup> powerups;
    private List<ISpawningPoint> playerSpawningPoints;
    private List<ISpawningPoint> powerupSpawningPoints;
    private GameSettings settings;
    // Time until game ends
    private final long gameEndTime;
    private long gameTimerStart;
    private long gameTimeLeft;
    
    private long powerupSpawningTimerStart;
    private final long powerupSpawningIntervall;
    
    private long secondTimerStart;
    
    private long pauseTimeStart;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Instantiates the TanksGameModel.
     *
     * @param players list of all the IPlayers in the game
     * @param powerups list of all th IPowerups in the game
     * @param powerupSpawningPoints 
     * @param playerSpawningPoints 
     * @param settings  
     */
    public TanksGameModel(List<IPlayer> players, List<IPowerup> powerups,
            List<ISpawningPoint> powerupSpawningPoints, List<ISpawningPoint> playerSpawningPoints,
            GameSettings settings) {
        if (playerSpawningPoints.size() < players.size()) {
            throw new IllegalArgumentException("Not allowed to have fever playerspawningpoints than players");
        }
        this.players = players;
        this.powerups = powerups;
        this.powerupSpawningPoints = powerupSpawningPoints;
        this.playerSpawningPoints = playerSpawningPoints;
        this.settings = settings;
        gameEndTime = settings.getGameEndTimeMS();
        powerupSpawningIntervall = settings.getPowerupSpawningIntervallMS();  //testing
    }

    /**
     * {@inheritdoc}
     * @return 
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
        gameTimerStart = System.currentTimeMillis();
        secondTimerStart = gameTimerStart;
        powerupSpawningTimerStart = gameTimerStart;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void endGame() {
        // show stats
        for (IPlayer player : players) {
            player.showScoreboard();
        }
        this.cleanup();
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void pauseGame() {
        pauseTimeStart = System.currentTimeMillis();
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void resumeGame() {
        long pausedTime = System.currentTimeMillis() - pauseTimeStart;
        // adjust all timers with the paused time.
        gameTimerStart += pausedTime;
        secondTimerStart += pausedTime;
        powerupSpawningTimerStart += pausedTime;
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

    /**
     * {@inheritdoc}
     * @param tpf 
     */
    @Override
    public void update(float tpf) {
        if (EApplicationState.getGameState() == EApplicationState.RUNNING) {
            long currTime = System.currentTimeMillis();
            if (currTime - gameTimerStart >= gameEndTime) {
                endGame();
                return;
            }
            if (currTime - secondTimerStart >= 1000) {
                gameTimeLeft = (gameEndTime - (System.currentTimeMillis() - gameTimerStart))/1000;
                pcs.firePropertyChange(Commands.TIMER, null, gameTimeLeft);
                secondTimerStart = currTime;
            }
            for (IPlayer player : players) {
                if (player.getKills() >= settings.getKillsToWin()) {
                    endGame();
                    return;
                }
                player.update(tpf);
                if (player.shouldRespawn()) {
                    respawnPlayer(player);
                    player.setRespawn(false);
                }
            }
            if (currTime - powerupSpawningTimerStart >= powerupSpawningIntervall) {
                spawnPowerups();
                powerupSpawningTimerStart = currTime;
            }
        }
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

    /**
     * {@inheritdoc}
     */
    @Override
    public void powerupPickedUp(IPowerup powerup) {
        for (ISpawningPoint spawn : powerupSpawningPoints) {
            if (spawn.isOccupied() && spawn.getOccupier() == powerup) {
                spawn.setOccupied(false);
                spawn.setOccupier(null);
            }
        }
    }

    private void respawnPlayer(IPlayer player) {
        Collections.shuffle(playerSpawningPoints);
        IArmedVehicle vehicle = player.getVehicle();
        ISpawningPoint spawn = playerSpawningPoints.get(0);
        vehicle.setPosition(spawn.getPosition());
        vehicle.showInWorld();
    }
}
