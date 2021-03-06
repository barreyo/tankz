
package model;

/**
 * The player who uses a haste powerup gain a speed increase for a short
 * period of time.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class HastePowerup extends APowerup {
    private float maxSpeed;
    private long activateTimerStart;
    private boolean isActive;
    
    private static final long END_TIME = 10000;
    
    private IPlayer player;

    /**
     * {@inheritDoc}
     */
    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        IArmedVehicle vehicle = player.getVehicle();
        maxSpeed = vehicle.getDefaultMaxSpeed();
        vehicle.setMaxSpeed(maxSpeed * 3f);
        activateTimerStart = System.currentTimeMillis();
        isActive = true;
        vehicle.toggleFlame(true);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (isActive) {
            if (System.currentTimeMillis() - activateTimerStart >= END_TIME) {
                isActive = false;
                IArmedVehicle vehicle = player.getVehicle();
                vehicle.toggleFlame(false);
                vehicle.resetSpeedValues();
                player = null;
                isHeldByPlayer = false;
            }
        }
    }
}
