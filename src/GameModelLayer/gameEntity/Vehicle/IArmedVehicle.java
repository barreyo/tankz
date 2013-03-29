/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModelLayer.gameEntity.Vehicle;

import GameModelLayer.gameEntity.Powerup.EPowerup;
import GameModelLayer.gameEntity.Weapon.IWeapon;


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
     * Sets acceleration force of vehicle.
     *
     * @param force The acceleration force used to accelerate.
     */
    void setAccelerationForce(float force);
    
    /**
     * Sets brake force value of vehicle.
     *
     * @param force The brake force used to brake
     */
    void setBrakeForce(float force);
    
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
     *
     * @param force
     */
    void incrementAccelerationValue(float force);
    /**
     *
     * @param force
     */
    void decrementAccelerationValue(float force);
    /**
     *
     * @param value
     */
    void incrementSteeringValue(float value);
    /**
     *
     * @param value
     */
    void decrementSteeringValue(float value);
    
    /**
     * Returns the powerup in powerupSlot.
     * 
     * @return The powerup contained in powerupSlot
     */
    public EPowerup getPowerup();

    /**
     * Sets the powerup in the powerupSlot.
     * 
     * @param powerup The powerup to add in powerupSlot
     */
    public  void setPowerup(EPowerup powerup);
    
    /**
     *
     * @param model
     */
    void setWeaponModel(IWeapon model);
    
    /**
     *
     * @return
     */
    IWeapon getWeaponModel();

    public void setForwardMaxSpeed(float f);

    public float getForwardMaxSpeed();

    public float getBackMaxSpeed();

    public void setBackMaxSpeed(float f);

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
}
