
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
     *
     */
    public void accelerateForward();

    /**
     *
     */
    public void accelerateBack();

    /**
     *
     */
    public void steerLeft();

    /**
     *
     */
    public void steerRight();

    /**
     *
     * @param currentVehicleSpeedKmHour
     */
    public void updateCurrentVehicleSpeedKmHour(float currentVehicleSpeedKmHour);

    /**
     *
     * @param physicsLocation
     */
    public void updatePosition(Vector3f physicsLocation);

    /**
     *
     * @return
     */
    public Vector3f getFirePosition();

    /**
     *
     * @param forwardVector
     */
    public void updateDirection(Vector3f forwardVector);

    /**
     *
     * @return
     */
    public Vector3f getDirection();

    /**
     *
     * @return
     */
    public Quaternion getRotation();

    /**
     *
     * @param worldRotation
     */
    public void updateRotation(Quaternion worldRotation);

    /**
     *
     */
    public void applyFriction();
    
    /**
     *
     * @param maxSpeed
     */
    public void setMaxSpeed(float maxSpeed);
    
    /**
     *
     * @param accelerationForce
     */
    public void setAccelerationForce(float accelerationForce);
    
    /**
     *
     * @return
     */
    public float getDefaultMaxSpeed();

    /**
     *
     * @param projectile
     */
    public void gotHitBy(IExplodingProjectile projectile);

    /**
     *
     * @param position
     */
    public void setPosition(Vector3f position);
    
    /**
     *
     * @return
     */
    public Vector3f getSmokePosition();

    /**
     *
     */
    public void shootMissile();

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
     * @param hp 
     */
    public void decrementHealth(int hp);
    
    /**
     * Shoots a projectile from the vehicle
     */
    public void shoot();
    
    /**
     *
     */
    void resetSpeedValues();
   
}
