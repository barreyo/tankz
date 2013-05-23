
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A beer powerup slows the opponents down or if you are in single player mode,
 * yourself.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class BeerPowerup extends APowerup {
    
    private float speedChangeCounter;
    private boolean changeSpeed;
    private float maxSpeed;
    private long activateTimerStart;
    private boolean isActive;
    
    private static final long END_TIME = 10000;
    
    private IPlayer player;
    private List<IPlayer> players;
    private List<IPlayer> targets;

    /**
     * Constructor for BeerPowerup
     * 
     * @param players the players of the game.
     */
    public BeerPowerup(List<IPlayer> players) {
        super();
        this.players = players;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        IArmedVehicle vehicle = player.getVehicle();
        
        targets = new ArrayList<IPlayer>();
        // Setup targets for this powerup
        for (IPlayer p : players) {
            if (!player.equals(p)) {
                targets.add(p);
            }
        }
        if (!targets.isEmpty()) {
            for (IPlayer p : targets) {
                IArmedVehicle temp = p.getVehicle();
                temp.setMaxSpeed(temp.getDefaultMaxSpeed() / 3f);
            }
        } else {
            maxSpeed = vehicle.getDefaultMaxSpeed();
            vehicle.setMaxSpeed(maxSpeed / 3f);
        }
        activateTimerStart = System.currentTimeMillis();
        isActive = true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (isActive) {
            IArmedVehicle vehicle = player.getVehicle();
            if (!targets.isEmpty()) {
                if (System.currentTimeMillis() - activateTimerStart >= END_TIME) {
                    isActive = false;
                    for (IPlayer p : targets) {
                        p.getVehicle().resetSpeedValues();
                    }
                    targets = null;
                    players = null;
                    player = null;
                    isHeldByPlayer = false;
                }
                
            // Only gets here if it is single-player
            } else {
                speedChangeCounter = speedChangeCounter + tpf;
                if (speedChangeCounter >= 0.6f) {
                    changeSpeed = !changeSpeed;
                    speedChangeCounter = 0;
                    if (changeSpeed) {
                        vehicle.setMaxSpeed(maxSpeed * 3f);
                    } else {
                        vehicle.setMaxSpeed(maxSpeed / 3f);
                    }
                }
                if (System.currentTimeMillis() - activateTimerStart >= END_TIME) {
                    isActive = false;
                    vehicle.resetSpeedValues();
                    player = null;
                    isHeldByPlayer = false;
                }
            }
        }
    }
}
