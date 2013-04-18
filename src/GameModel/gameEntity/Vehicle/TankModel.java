package GameModel.gameEntity.Vehicle;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Model for a tank vehicle.
 * 
 * @author Daniel
 */
public final class TankModel implements IArmedVehicle {
    
    private int health;
    private float maxSpeed;
    private float accelerationForce;
    private IArmedVehicle.VehicleState vehicleState;
    private Vector3f position;
    private Vector3f direction;
    private Quaternion rotation;
    
    private float steeringValue;
    private float accelerationValue;
    private float acceleration;
    private float currentVehicleSpeedKmHour;
    
    // This is physics-related information about the Tank
    public static final float TANK_MASS = 600.0f;
    public static final float TANK_STIFFNESS = 80.0f;//200=f1 car
    public static final float TANK_COMP_VALUE = 0.2f; //(should be lower than damp)
    public static final float TANK_DAMP_VALUE = 0.5f; 
    public static final float TANK_MAX_BACK_SPEED = 30.0f;
    public static final float TANK_BRAKE_FORCE = 10000.0f;
    public static final float TANK_FRICTION_FORCE = 10.0f;
    public static final float TANK_MAX_SUSPENSION_FORCE = 999000.0f;
    public static final float TANK_STEERING_CHANGE_VALUE = 0.4f;

    //Create four wheels and add them at their locations
    public static final Vector3f TANK_WHEEL_DIRECTION = new Vector3f(0, -1, 0); // was 0, -1, 0
    public static final Vector3f TANK_WHEEL_AXIS = new Vector3f(-1, 0, 0); // was -1, 0, 0
    public static final float TANK_WHEEL_REST_LENGTH = 0.2f;
    public static final float TANK_WHEEL_Y_OFF = 0.7f;
    public static final float TANK_WHEEL_X_OFF = 0.9f;
    public static final float TANK_WHEEL_Z_OFF = 0.7f;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public TankModel() {
        health = 100;
        maxSpeed = 80.0f;
        accelerationForce = 2000.0f;
    }
    
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
        return accelerationForce;
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
        if (value != 0) {
            this.steeringValue += value;
            steeringValueChanged();
        }
    }

    private void decrementSteeringValue(float value) {
        if (value != 0) {
            this.steeringValue -= value;
            steeringValueChanged();
        }
    }
    
    private void steeringValueChanged() {
        pcs.firePropertyChange(STEER, null, null);
    }

    @Override
    public void decrementHealth(int hp) {
        if (hp != 0) {
            if (health - hp < 0) {
                health = 0;
            } else {
                health -= hp;
            }
            pcs.firePropertyChange(HEALTH, null, null);
        }
    }

    @Override
    public void shoot() {
        pcs.firePropertyChange(SHOOT, null, null);
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
        float oldAcceleration = accelerationValue;
        float maxSpeed = (acceleration >= 0 ? this.maxSpeed : -TANK_MAX_BACK_SPEED);
        float speedFactor = (maxSpeed - currentVehicleSpeedKmHour) / maxSpeed;
        accelerationValue = acceleration * speedFactor;
        if (oldAcceleration != accelerationValue) {
            pcs.firePropertyChange(ACCELERATE, oldAcceleration, accelerationValue);
        }
    }

    @Override
    public void accelerateForward() {
        incrementAcceleration(accelerationForce);
    }

    @Override
    public void accelerateBack() {
        decrementAcceleration(accelerationForce);
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

    @Override
    public float getMass() {
        return TANK_MASS;
    }

    @Override
    public void updatePosition(Vector3f pos) {
        this.position = pos.clone();
    }

    @Override
    public Vector3f getFirePosition() {
        return position.addLocal(0, 1.1f, 0).addLocal(direction.multLocal(2f));
    }

    @Override
    public void updateDirection(Vector3f forwardVector) {
        direction = forwardVector.clone();
    }

    @Override
    public Vector3f getDirection() {
        return direction.clone();
    }

    @Override
    public Quaternion getRotation() {
        return rotation.clone();
    }

    @Override
    public void updateRotation(Quaternion rotation) {
        this.rotation = rotation.clone();
    }

    @Override
    public void applyFriction() {
        pcs.firePropertyChange(FRICTION, null, null);
    }

    @Override
    public synchronized void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public synchronized void setAccelerationForce(float accelerationForce) {
        this.accelerationForce = accelerationForce;
    }
    
    @Override
    public float getMaxSpeed(){
        return maxSpeed;
    }
}
