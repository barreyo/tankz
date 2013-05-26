package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import utilities.Commands;

/**
 * The model for the game state.
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
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
     * @param players list of all the IPlayers in the game.
     * @param powerups list of all th IPowerups in the game.
     * @param powerupSpawningPoints list of all the powerupspawningpoints in
     * game.
     * @param playerSpawningPoints list of all the playerspawningpoints in game
     * @param settings the game settings for the game.
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
     * {@inheritDoc}
     */
    @Override
    public Collection<IPlayer> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        spawnAllPlayers();
        gameTimerStart = System.currentTimeMillis();
        secondTimerStart = gameTimerStart;
        powerupSpawningTimerStart = gameTimerStart;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public void pauseGame() {
        pauseTimeStart = System.currentTimeMillis();
        for (IPlayer player : players) {
            player.getVehicle().setEnabled(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resumeGame() {
        long pausedTime = System.currentTimeMillis() - pauseTimeStart;
        // adjust all timers with the paused time.
        gameTimerStart += pausedTime;
        secondTimerStart += pausedTime;
        powerupSpawningTimerStart += pausedTime;
        for (IPlayer player : players) {
            player.getVehicle().setEnabled(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /**
     * {@inheritDoc}
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
                gameTimeLeft = (gameEndTime - (System.currentTimeMillis() - gameTimerStart)) / 1000;
                pcs.firePropertyChange(Commands.TIMER, null, gameTimeLeft);
                secondTimerStart = currTime;
            }
            for (IPlayer player : players) {
                if (player.getKills() >= settings.getKillsToWin() && settings.getKillsToWin() > 0) {
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

    /**
     * Spawn powerups at random powerup-spawningpoints that isnt occupied.
     */
    private void spawnPowerups() {
        Collections.shuffle(powerups);
        for (ISpawningPoint spawn : powerupSpawningPoints) {
            if (!spawn.isOccupied()) {
                for (IPowerup powerup : powerups) {
                    if (!powerup.isHeldByPlayer() && !powerup.isShownInWorld()) {
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
     * {@inheritDoc}
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

    /**
     * Spawns all the players at different random locations.
     */
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
     * {@inheritDoc}
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

    /**
     * Respawns the player at a random spawningpoint.
     *
     * @param player
     */
    private void respawnPlayer(IPlayer player) {
        Collections.shuffle(playerSpawningPoints);
        IArmedVehicle vehicle = player.getVehicle();
        ISpawningPoint spawn = playerSpawningPoints.get(0);
        vehicle.setPosition(spawn.getPosition());
        vehicle.showInWorld();
    }
}
