package GameModel;

import GameModel.IExplodingProjectile;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

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
    
    private boolean isInWorld;
    
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
    
    private List<CanonBallModel> canonBalls;
    private List<MissileModel> missiles;
    
    public TankModel(List<CanonBallModel> canonBalls, List<MissileModel> missiles) {
        this.canonBalls = canonBalls;
        this.missiles = missiles;
        health = 100;
        maxSpeed = 80.0f;
        accelerationForce = 2000.0f;
        position = Vector3f.ZERO;
        direction = Vector3f.ZERO;
        rotation = Quaternion.ZERO;
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
        for (CanonBallModel canonBall : canonBalls) {
            
        }
        pcs.firePropertyChange(SHOOT, null, null);
    }
  
    public void shootMissile() {
        pcs.firePropertyChange(MISSILE, health, health);
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
        pcs.firePropertyChange(SMOKE, null, null);
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
        return new Vector3f(position).addLocal(0, 0.9f, 0).addLocal(direction.multLocal(1.3f));
    }
    
    public Vector3f getSmokePosition() {
        return new Vector3f(position).addLocal(0, 2.05f, 0).subtractLocal(direction.multLocal(1.5f));
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

    @Override
    public void gotHitBy(IExplodingProjectile projectile) {
        this.decrementHealth(projectile.getDamageOnImpact());
        if (health<=0){
            this.vehicleState = VehicleState.DESTROYED;
            pcs.firePropertyChange(HIDE, null, null);
        }
    }

    @Override
    public void cleanup() {
        pcs.firePropertyChange(CLEANUP, null, null);
    }

    @Override
    public void setPosition(Vector3f position) {
        this.position = position.clone();
    }

    @Override
    public void showInWorld() {
        health = 100;
        pcs.firePropertyChange(HEALTH, null, null);
        vehicleState = VehicleState.ALIVE;
        isInWorld = true;
        pcs.firePropertyChange(SHOW, null, null);
    }

    @Override
    public void hideFromWorld() {
        isInWorld = false;
        pcs.firePropertyChange(HIDE, null, null);
    }

    @Override
    public Vector3f getPosition() {
        return position.clone();
    }

    @Override
    public boolean isInWorld() {
        return this.isInWorld;
    }
}
