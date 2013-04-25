
package GameModel;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;


/**
 *
 * @author Daniel
 */
public interface IArmedVehicle extends IWorldObject {
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
    float getDefaultAccelerationForce();
    
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

    public void accelerateForward();

    public void accelerateBack();

    public void steerLeft();

    public void steerRight();

    public float getFrictionForce();

    public void updateCurrentVehicleSpeedKmHour(float currentVehicleSpeedKmHour);
    
    public float getMass();

    public void updatePosition(Vector3f physicsLocation);

    public Vector3f getFirePosition();

    public void updateDirection(Vector3f forwardVector);

    public Vector3f getDirection();

    public Quaternion getRotation();

    public void updateRotation(Quaternion worldRotation);

    public void applyFriction();
    
    public void setMaxSpeed(float maxSpeed);
    
    public void setAccelerationForce(float accelerationForce);
    
    public float getDefaultMaxSpeed();

    public void gotHitBy(IExplodingProjectile projectile);

    public void setPosition(Vector3f position);

    public Vector3f getPosition();
    
    public Vector3f getSmokePosition();

    /**
     * Enum representing different states of the vehicle.
     */
    public enum VehicleState {
        /**
         * Vehicle is in living state.
         */
        ALIVE,
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
    
    void resetSpeedValues();
   
}
