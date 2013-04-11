package GameModel.gameEntity.Vehicle;

import GameModel.IObservable;
import GameModel.gameEntity.Powerup.EPowerup;
import GameModel.gameEntity.Powerup.PowerupSlot;
import GameModel.gameEntity.Projectile.IProjectile;
import GameModel.gameEntity.Weapon.IWeapon;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
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
    
    private float accelerationForce;
    private float brakeForce;
    private float steeringValue;
    private float accelerationValue;
    private float maxForwardSpeed;
    private float maxBackSpeed;
    // MAX_BACKWARDS and MAX_FORWARDS should be final, and gotten through constructor
    // Powerups should be handled by player instead of the tank
    private PowerupSlot powerupSlot;
    private IWeapon weaponModel;

    // This is physics-related information about the Tank
    public static final float TANK_STIFFNESS = 80.0f;//200=f1 car
    public static final float TANK_COMP_VALUE = 0.2f; //(should be lower than damp)
    public static final float TANK_DAMP_VALUE = 0.5f;

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
        return accelerationForce;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getBrakeForce() {
        return brakeForce;
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
    public void setAccelerationForce(float force) {
        this.accelerationForce = force;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setBrakeForce(float force) {
        this.brakeForce = force;
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
    public void setWeaponModel(IWeapon model) {
        weaponModel = model;
    }

    /**
     * @inheritdoc
     */
    @Override
    public IWeapon getWeaponModel() {
        return weaponModel;
    }

    /**
     * @inheritdoc
     */
    @Override
    public EPowerup getPowerup() {
        return powerupSlot.getPowerup();
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setPowerup(EPowerup powerup) {
        powerupSlot.setPowerup(powerup);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setForwardMaxSpeed(float max) {
        this.maxForwardSpeed = max;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getForwardMaxSpeed() {
        return maxForwardSpeed;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getBackMaxSpeed() {
        return maxBackSpeed;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setBackMaxSpeed(float max) {
        this.maxBackSpeed = max;
    }

    public void decrementHealth(int hp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void shoot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<IProjectile> getFiredProjetiles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
}
