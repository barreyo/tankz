
package GameLogicLayer.Vehicle;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;

/**
 * Abstract vehicle controller.
 *
 * @author Daniel, Per, Johan, Albin
 */
public abstract class AVehicleController implements ActionListener {
    
    private InputManager inputManager;
  
    AVehicleController(InputManager inputManager) {
        this.inputManager = inputManager;
        setupKeys();
    }
    
    /**
     * Gets the current health of the vehicle.
     *
     * @return The health of the vehicle
     */
    public abstract int getHealth();
    /**
     * Gets the direction of the vehicle in the 3d room.
     *
     * @return The direction of the vehicle
     */
    public abstract Vector3f getDirection();
    /**
     * Gets the position of the vehicle in the 3d room..
     * 
     * @return The position of the vehicle
     */
    public abstract Vector3f getPosition();
    /**
     * Gets the state of the vehicle.
     *
     * @return The state of the vehicle
     */
    public abstract VehicleState getVehicleState();
    /**
     * Gets the accerlationforce use to accelerate the vehicle.
     *
     * @return The accelerationforce of the vehicle
     */
    public abstract float getAccelerationForce();
    /**
     * Gets the brakeforce applied when braking.
     *
     * @return The brakeforce of the vehicle
     */
    public abstract float getBrakeForce();
    /**
     * Gets the steering value used to steer.
     *
     * @return The brake force of the vehicle
     */
    public abstract float getSteeringValue();
    /**
     * Gets the acceleration value used to accelerate.
     * 
     * @return The steeringvalue of the vehicle
     */
    public abstract float getAccelerationValue();
    
    /**
     * Sets the health of the vehicle.
     * 
     * @param health The health of the vehicle
     */
    public abstract void setHealth(int health);
    /**
     * 
     *
     * @param direction 
     */
    public abstract void setDirection(Vector3f direction);
    /**
     *
     * @param position
     */
    public abstract void setPosition(Vector3f position);
    /**
     * Sets the state of the vehicle.
     * 
     * @param state The state of the vehicle
     */
    public abstract void setVehicleState(VehicleState state);
    /**
     * Sets acceleration force of vehicle.
     *
     * @param force The acceleration force used to accelerate.
     */
    public abstract void setAccelerationForce(float force);
    /**
     * Sets brake force value of vehicle.
     *
     * @param force The brake force used to brake
     */
    public abstract void setBrakeForce(float force);
    /**
     * Sets steering value of vehicle.
     *
     * @param value The steeringvalue to be set
     */
    public abstract void setSteeringValue(float value);
    /**
     * Sets accelerationvalue of vehicle.
     *  
     * @param value The accelerationvalue to be set
     */
    public abstract void setAccelerationValue(float value);
    
    private void setupKeys() {
        inputManager.addMapping("Lefts", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("Rights", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("Ups", new KeyTrigger(KeyInput.KEY_U));
        inputManager.addMapping("Downs", new KeyTrigger(KeyInput.KEY_J));
        //inputManager.addMapping("Space", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("Reset", new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addListener(this, "Lefts");
        inputManager.addListener(this, "Rights");
        inputManager.addListener(this, "Ups");
        inputManager.addListener(this, "Downs");
        //inputManager.addListener(this, "Space");
        inputManager.addListener(this, "Reset");
    }
    
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
