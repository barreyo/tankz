package GameModel.gameEntity.Vehicle;

import GameModel.gameEntity.Projectile.IExplodingProjectile;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * Model for a tank vehicle.
 * 
 * @author Daniel
 */
public class TankModel implements IArmedVehicle {
    
    private int health = 100;
    private IArmedVehicle.VehicleState vehicleState;
    
    private float steeringValue;
    private float accelerationValue;
    private float acceleration;
    private float currentVehicleSpeedKmHour;
    
    // This is physics-related information about the Tank
    public static final float TANK_MASS = 600.0f;
    public static final float TANK_STIFFNESS = 80.0f;//200=f1 car
    public static final float TANK_COMP_VALUE = 0.2f; //(should be lower than damp)
    public static final float TANK_DAMP_VALUE = 0.5f;
    public static final float TANK_MAX_FORWARD_SPEED = 80.0f; 
    public static final float TANK_MAX_BACK_SPEED = 30.0f;
    public static final float TANK_ACCELERATION_FORCE = 2000.0f;
    public static final float TANK_BRAKE_FORCE = 10000.0f;
    public static final float TANK_FRICTION_FORCE = 10.0f;
    public static final float TANK_MAX_SUSPENSION_FORCE = 999000.0f;
    public static final float TANK_STEERING_CHANGE_VALUE = 0.4f;

    //Create four wheels and add them at their locations
    public static final Vector3f TANK_WHEEL_DIRECTION = new Vector3f(0, -1, 0); // was 0, -1, 0
    public static final Vector3f TANK_WHEEL_AXIS = new Vector3f(-1, 0, 0); // was -1, 0, 0
    public static final float TANK_WHEEL_RADIUS = 0.1f;
    public static final float TANK_WHEEL_REST_LENGTH = 0.2f;
    public static final float TANK_WHEEL_Y_OFF = 0.3f;
    public static final float TANK_WHEEL_X_OFF = 0.5f;
    public static final float TANK_WHEEL_Z_OFF = 1.5f;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * @inheritdoc
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * @inheritdoc
     */
    @Override
    public IArmedVehicle.VehicleState getVehicleState() {
        return vehicleState;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getAccelerationForce() {
        return TANK_ACCELERATION_FORCE;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getBrakeForce() {
        return TANK_BRAKE_FORCE;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getSteeringValue() {
        return steeringValue;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getAccelerationValue() {
        return accelerationValue;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setVehicleState(IArmedVehicle.VehicleState state) {
        this.vehicleState = state;
    }

    private void incrementAcceleration(float force) {
        this.acceleration += force;
    }

    private void decrementAcceleration(float force) {
        this.acceleration -= force;
    }

    private void incrementSteeringValue(float value) {
        this.steeringValue += value;
    }

    private void decrementSteeringValue(float value) {
        this.steeringValue -= value;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getForwardMaxSpeed() {
        return TANK_MAX_FORWARD_SPEED;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getBackMaxSpeed() {
        return TANK_MAX_BACK_SPEED;
    }

    @Override
    public void decrementHealth(int hp) {
        if (health - hp < 0) {
            health = 0;
        } else {
            health -= hp;
        }
        pcs.firePropertyChange(null,null,null);
    }

    @Override
    public void shoot() {
        System.out.println("PEWPEWPEW");
    }
  
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    
    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    @Override
    public void update(float tpf) {
        // Keep vehicle within max speeds
        float maxSpeed = (acceleration >= 0
                ? TANK_MAX_FORWARD_SPEED
                : -TANK_MAX_BACK_SPEED);
        float speedFactor = (maxSpeed - currentVehicleSpeedKmHour) / maxSpeed;
        accelerationValue = acceleration * speedFactor;
    }

    @Override
    public void accelerateForward() {
        incrementAcceleration(TANK_ACCELERATION_FORCE);
    }

    @Override
    public void accelerateBack() {
        decrementAcceleration(TANK_ACCELERATION_FORCE);
    }

    @Override
    public void steerLeft() {
        incrementSteeringValue(TANK_STEERING_CHANGE_VALUE);
    }

    @Override
    public void steerRight() {
        decrementSteeringValue(TANK_STEERING_CHANGE_VALUE);
    }

    @Override
    public float getFrictionForce() {
        return TANK_FRICTION_FORCE;
    }

    @Override
    public void updateCurrentVehicleSpeedKmHour(float currentVehicleSpeedKmHour) {
        this.currentVehicleSpeedKmHour = currentVehicleSpeedKmHour;
    }
}
