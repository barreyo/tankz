
package GameModel.gameEntity.Vehicle;

import GameUtilities.IObservable;


/**
 *
 * @author Daniel
 */
public interface IArmedVehicle extends IObservable{
    /**
     * Gets the current health of the vehicle.
     *
     * @return The health of the vehicle
     */
    int getHealth();
    
    /**
     * Gets the state of the vehicle.
     *
     * @return The state of the vehicle
     */
    IArmedVehicle.VehicleState getVehicleState();
    
    /**
     * Gets the accerlationforce use to accelerate the vehicle.
     *
     * @return The accelerationforce of the vehicle
     */
    float getAccelerationForce();
    
    /**
     * Gets the brakeforce applied when braking.
     *
     * @return The brakeforce of the vehicle
     */
    float getBrakeForce();
    
    /**
     * Gets the steering value used to steer.
     *
     * @return The brake force of the vehicle
     */
    float getSteeringValue();
    
    /**
     * Gets the acceleration value used to accelerate.
     * 
     * @return The steeringvalue of the vehicle
     */
    float getAccelerationValue();
    
    /**
     * Sets the state of the vehicle.
     * 
     * @param state The state of the vehicle
     */
    void setVehicleState(IArmedVehicle.VehicleState state);

    public void accelerateForward();

    public void accelerateBack();

    public void steerLeft();

    public void steerRight();

    public float getFrictionForce();

    public void updateCurrentVehicleSpeedKmHour(float currentVehicleSpeedKmHour);
    
    public float getMass();

    /**
     * Enum representing different states of the vehicle.
     */
    public enum VehicleState {
        /**
         * Vehicle is in moving state.
         */
        MOVING,
        /**
         * Vehicle is in idle state.
         */
        IDLE,
        /**
         * Vehicle is in destroyed state.
         */
        DESTROYED;
    }
    
    /**
     * Decrements the hp of the vehicle by the given amount
     */
    public void decrementHealth(int hp);
    
    /**
     * Shoots a projectile from the vehicle
     */
    public void shoot();
    
    public void update(float tpf);
}
