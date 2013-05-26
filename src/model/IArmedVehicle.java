
package model;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;


/**
 * The definition of an armed vehicle, an armed vehicle is a damagable object.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public interface IArmedVehicle extends IDamageableObject {
    
    /**
     * Gets the state of the vehicle.
     *
     * @return The state of the vehicle
     */
    IArmedVehicle.VehicleState getVehicleState();
    
    /**
     * Move vehicle forward.
     */
    public void accelerateForward();

    /**
     * Move vehicle backwards.
     */
    public void accelerateBack();

    /**
     * Steer the vehicle to the left.
     */
    public void steerLeft();

    /**
     * Steer the vehicle to the right.
     */
    public void steerRight();

    /**
     * Update how fast the vehicle is currently moving.
     * 
     * @param currentVehicleSpeedKmHour speed value.
     */
    public void updateCurrentVehicleSpeedKmHour(float currentVehicleSpeedKmHour);

    /**
     * Get the position where shots should be fired.
     * 
     * @return the vector of the firing position.
     */
    public Vector3f getFirePosition();

    /**
     * Update the vehicle facing direction.
     * 
     * @param forwardVector direction of vehicle.
     */
    public void updateDirection(Vector3f forwardVector);

    /**
     * Get the vehicle facing direction.
     * 
     * @return direction of vehicle.
     */
    public Vector3f getDirection();

    /**
     * {@inheritDoc}
     */
    @Override
    public Quaternion getRotation();

    /**
     * Update the rotation of the vehicle.
     * 
     * @param worldRotation rotation of the vehicle.
     */
    public void updateRotation(Quaternion worldRotation);

    /**
     * Apply a breaking force to the vehicle.
     */
    public void applyFriction();
    
    /**
     * Set the highest possible speed for the vehicle.
     * 
     * @param maxSpeed speed.
     */
    public void setMaxSpeed(float maxSpeed);
    
    /**
     * Returns the current max speed of the vehicle.
     * Currently only used for testing
     * 
     * @return The max speed
     */
    public float getMaxSpeed();
    
    /**
     * Get the default speed of the vehicle.
     * 
     * @return speed.
     */
    public float getDefaultMaxSpeed();
    
    /**
     * Returns the position where smoke and other exhaust relate effects should 
     * come out from.
     * 
     * @return vector of exhaust pipe.
     */
    public Vector3f getExhaustPosition();
    
    /**
     * Enable/disable haste flame.
     */
    public void toggleFlame(boolean state);
    
    /**
     * Enable/disable engine smoke.
     */
    public void toggleSmoke(boolean state);
    
    /**
     * Shoots a projectile from the vehicle.
     */
    public void shoot(IPlayer player);
    
    /**
     * Hide the air call indicator ring.
     */
    public void hideAirCallRing();
    
    /**
     * Reset all values concering moving.
     */
    public void resetSpeedValues();

    /**
     * Shoots a missile from the vehicle.
     */
    public void shootMissile(IPlayer player);

    /**
     * Drop a landmine at the current postion.
     * 
     * @param player the player who should get a kill if this mine kills someone.
     */
    public void dropLandmine(IPlayer player);

    /**
     * Heal this vehicle.
     * 
     * @param heal how much the vehicle should be healed.
     */
    public void heal(int heal);

    /**
     * Drop a atomic bomb.
     * 
     * @param player the player who should get a kill if this mine kills someone.
     */
    public void dropBomb(IPlayer player);

    /**
     * Sets enabling of the vehicle.
     * 
     * @param bool a boolean enabling.
     */
    public void setEnabled(boolean bool);

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
