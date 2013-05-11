
package GameModel;

import GameUtilities.Commands;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;


/**
 *
 * @author Daniel
 */
public interface IArmedVehicle extends IDamageableObject {
    
    /**
     * Gets the state of the vehicle.
     *
     * @return The state of the vehicle
     */
    IArmedVehicle.VehicleState getVehicleState();
    
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
     * @return
     */
    public float getDefaultMaxSpeed();
    
    /**
     *
     * @return
     */
    public Vector3f getExhaustPosition();
    
    /**
     * 
     */
    public void toggleFlame();
    
    /**
     * Shoots a projectile from the vehicle.
     */
    public void shoot(IPlayer player);
    
    /**
     *
     */
    public void resetSpeedValues();

    /**
     * Shoots a missile from the vehicle.
     */
    public void shootMissile(IPlayer player);

    public void dropLandmine(IPlayer player);

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
}
