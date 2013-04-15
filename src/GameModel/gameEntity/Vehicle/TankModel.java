package GameModel.gameEntity.Vehicle;

import GameModel.IObservable;
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
public class TankModel implements IArmedVehicle, IObservable {
    
    private int health;
    private VehicleState vehicleState;
    
    private float steeringValue;
    private float accelerationValue;
    
    // This is physics-related information about the Tank
    public static final float TANK_MASS = 600.0f;
    public static final float TANK_STIFFNESS = 80.0f;//200=f1 car
    public static final float TANK_COMP_VALUE = 0.2f; //(should be lower than damp)
    public static final float TANK_DAMP_VALUE = 0.5f;
    public static final float TANK_MAX_FORWARD_SPEED = 80.0f; 
    public static final float TANK_MAX_BACK_SPEED = 30.0f;
    public static final float TANK_ACCELERATION_FORCE = 2000.0f;
    public static final float TANK_BRAKE_FORCE = 10000.0f;
    public static final float TANK_MAX_SUSPENSION_FORCE = 999000.0f;

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
    public VehicleState getVehicleState() {
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
    public void setVehicleState(VehicleState state) {
        this.vehicleState = state;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setSteeringValue(float value) {
        this.steeringValue = value;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setAccelerationValue(float value) {
        this.accelerationValue = value;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void incrementAccelerationValue(float force) {
        this.accelerationValue += force;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void decrementAccelerationValue(float force) {
        this.accelerationValue -= force;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void incrementSteeringValue(float value) {
        this.steeringValue += value;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void decrementSteeringValue(float value) {
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

    public void decrementHealth(int hp) {
        if (health - hp < 0) {
            health = 0;
        } else {
            health -= hp;
        }
    }

    public void shoot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<IExplodingProjectile> getFiredProjetiles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /**
     * 
     * @return health, vehicleState, steeringValue and accelerationValue in the
     * format: "Tankmodel{health=xxx, vehicleState=xxxxx, steeringValue=xxxf,
     * accelerationValue=xxxf
     */
    @Override
    public String toString() {
        return "TankModel{" + "health=" + health + ", vehicleState="
                + vehicleState + ", steeringValue=" + steeringValue 
                + ", accelerationValue=" + accelerationValue + '}';
    }
    
    /**
     * 
     * @return haschCode based on health, vehicleState, steeringValue 
     * and accelerationValue.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.health;
        hash = 59 * hash + (this.vehicleState != null ? this.vehicleState.hashCode() : 0);
        hash = 59 * hash + Float.floatToIntBits(this.steeringValue);
        hash = 59 * hash + Float.floatToIntBits(this.accelerationValue);
        return hash;
    }
    
    /**
     * Equals method that compares health, vehicleState, steeringValue and
     * accelerationValue
     *
     * @param obj the reference object with which to compare.
     * @return true if this TankModel is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TankModel other = (TankModel) obj;
        if (this.health != other.health) {
            return false;
        }
        if (this.vehicleState != other.vehicleState) {
            return false;
        }
        if (Float.floatToIntBits(this.steeringValue) != Float.floatToIntBits(other.steeringValue)) {
            return false;
        }
        if (Float.floatToIntBits(this.accelerationValue) != Float.floatToIntBits(other.accelerationValue)) {
            return false;
        }
        return true;
    }
}
