
package GameModel.gameEntity.Vehicle;

import GameModel.gameEntity.Projectile.IProjectile;
import java.util.List;


/**
 *
 * @author Daniel
 */
public interface IArmedVehicle {
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
    VehicleState getVehicleState();
    
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
     * Sets the health of the vehicle.
     * 
     * @param health The health of the vehicle
     */
    void setHealth(int health);
    
    /**
     * Sets the state of the vehicle.
     * 
     * @param state The state of the vehicle
     */
    void setVehicleState(VehicleState state);
    
    /**
     * Sets steering value of vehicle.
     *
     * @param value The steeringvalue to be set
     */
    void setSteeringValue(float value);
    
    /**
     * Sets accelerationvalue of vehicle.
     *  
     * @param value The accelerationvalue to be set
     */
    void setAccelerationValue(float value);
    
    /**
     * Increments the acceleration value by specified force.
     * 
     * @param force that increments the acceleration value
     */
    void incrementAccelerationValue(float force);
    /**
     * Decrements the acceleration value by specified force.
     * 
     * @param force that decrements the acceleration value
     */
    void decrementAccelerationValue(float force);
    /**
     * Increments the steering value by specified value.
     *
     * @param value that increments the steering value
     */
    void incrementSteeringValue(float value);
    /**
     * Decrements the steering value by specified value.
     * 
     * @param value that decrements the steering value
     */
    void decrementSteeringValue(float value);

    /**
     * Returns the forward max speed in km/h of the vehicle.
     * 
     * @return the forward max speed in km/h of the vehicle
     */
    float getForwardMaxSpeed();

    /**
     * Returns the backwards max speed in km/h of the vehicle.
     * 
     * @return the backwards max speed in km/h of the vehicle
     */
    float getBackMaxSpeed();

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
    
    /**
     * Returns the projectiles this vehicle has fired, that still exists
     */
    public List<IProjectile> getFiredProjetiles();
    
}
