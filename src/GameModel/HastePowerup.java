
package GameModel;

/**
 *
 * @author Per
 */
public class HastePowerup extends APowerup{
    private float maxSpeed;
    private float accForce;
    
    private boolean isActive;
    
    private float activateTimer;
    private static final float END_TIME = 5f;
    
    private IPlayer player;

    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        IArmedVehicle vehicle = player.getVehicle();
        maxSpeed = vehicle.getMaxSpeed();
        accForce = vehicle.getAccelerationForce();
        
        vehicle.setMaxSpeed(maxSpeed * 3f);
        vehicle.setAccelerationForce(accForce * 10f);
        activateTimer = 0;
        isActive = true;
    }
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (isActive) {
            activateTimer += tpf;
            if (activateTimer >= END_TIME) {
                isActive = false;
                IArmedVehicle vehicle = player.getVehicle();
                vehicle.setMaxSpeed(maxSpeed);
                vehicle.setAccelerationForce(accForce);
            }
        }
    } 
}
