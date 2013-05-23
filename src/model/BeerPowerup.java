
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class BeerPowerup extends APowerup {
    
    private float speedChangeCounter;
/*    private float turningLeftCounter;
    private float turningRightCounter;
    private float shootingCounter;
*/    private boolean changeSpeed;
/*    private boolean turnLeft;
    private boolean turnRight;
*/    private float maxSpeed;
    private long activateTimerStart;
    private boolean isActive;
    
    private static final long END_TIME = 10000;
    
    private IPlayer player;
//    private IArmedVehicle vehicle;
    private List<IPlayer> players;
    private List<IPlayer> targets;

    /**
     * Constructor for BeerPowerup
     * @param players 
     */
    public BeerPowerup(List<IPlayer> players) {
        super();
        this.players = players;
    }

    /**
     * 
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
                temp.setMaxSpeed(temp.getDefaultMaxSpeed() / 2f);
            }
        } else {
            maxSpeed = vehicle.getDefaultMaxSpeed();
            vehicle.setMaxSpeed(maxSpeed / 2f);
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
            } else {
                speedChangeCounter = speedChangeCounter + tpf;
                if (speedChangeCounter >= 0.6f) {
                    changeSpeed = !changeSpeed;
                    speedChangeCounter = 0;
                    if (changeSpeed) {
                        vehicle.setMaxSpeed(maxSpeed * 2f);
                    } else {
                        vehicle.setMaxSpeed(maxSpeed / 2f);
                    }
                }
/*
                turningLeftCounter = turningLeftCounter + tpf;
                if (turningLeftCounter >= 1.8f) {
                    turnLeft = !turnLeft;
                    turningLeftCounter = 0;
                    if (turnLeft) {
                        vehicle.steerLeft();
                    } else {
                        vehicle.steerRight();
                    }
                }

                turningRightCounter = turningRightCounter + tpf;
                if (turningRightCounter >= 0.9f) {
                    turnRight = !turnRight;
                    turningRightCounter = 0;
                    if (turnRight) {
                        vehicle.steerRight();
                    } else {
                        vehicle.steerLeft();
                    }
                }

                shootingCounter = shootingCounter + tpf;
                if (shootingCounter >= 2.5) {
                    shootingCounter = 0;
                    vehicle.shoot(player);
                }
*/
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
