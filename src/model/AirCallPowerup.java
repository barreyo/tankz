
package model;

/**
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class AirCallPowerup extends APowerup {
    private long activateTimerStart;
    private boolean isActive;
    
    private static final long END_TIME = 8000;
    private static final float INTERVAL = 0.1f;
    
    private float counter;
    
    private IPlayer player;

    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        player.getVehicle().hideAirCallRing();
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
            counter += tpf;
            
            if (counter >= INTERVAL) {
                player.getVehicle().dropBomb(player);
                counter = 0;
            }
            if (System.currentTimeMillis() - activateTimerStart >= END_TIME) {
                isActive = false;
                isHeldByPlayer = false;
            }
        }

    }
}
