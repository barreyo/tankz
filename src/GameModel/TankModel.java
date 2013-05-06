package GameModel;

import GameModel.IExplodingProjectile;
import GameUtilities.Commands;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Model for a tank vehicle.
 * 
 * @author Daniel
 */
public final class TankModel implements IArmedVehicle {
    
    private int health;
    private IArmedVehicle.VehicleState vehicleState;
    private Vector3f position;
    private Vector3f direction;
    private Quaternion rotation;
    
    private boolean isInWorld;
   
    private float steeringValue;
    private float accelerationValue;
    private float steeringChangeValue;
    private float acceleration;
    private float currentVehicleSpeedKmHour;
   
    private final float mass;
    private float currentMaxSpeed;
    private float currentAccelerationForce;
    private final float defaultMaxSpeed;
    private final float defaultAccelerationForce;
    private final float brakeForce;
    private final float frictionForce;
    private final float backMaxSpeed;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    private List<CanonBallModel> canonBalls;
    private List<MissileModel> missiles;
    
    /**
     *
     * @param canonBalls
     * @param missiles
     */
    public TankModel(List<CanonBallModel> canonBalls, List<MissileModel> missiles) {
        this.canonBalls = canonBalls;
        this.missiles = missiles;
        mass = 600.0f;
        health = 100;
        defaultMaxSpeed = 80.0f;
        currentMaxSpeed = defaultMaxSpeed;
        backMaxSpeed = 30.0f;
        defaultAccelerationForce = 2000.0f;
        currentAccelerationForce = defaultAccelerationForce;
        brakeForce = 10000.0f;
        frictionForce = 10.0f;
        steeringChangeValue = 0.4f;
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
    public float getDefaultAccelerationForce() {
        return defaultAccelerationForce;
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
        pcs.firePropertyChange(Commands.STEER, null, null);
    }

    public void decrementHealth(int hp) {
        if (hp != 0) {
            if (health - hp < 0) {
                health = 0;
            } else {
                health -= hp;
            }
            pcs.firePropertyChange(Commands.HEALTH, null, null);
        }
    }

    @Override
    public synchronized void shoot() {
        for (CanonBallModel canonBall : canonBalls) {
            if (!canonBall.isInWorld()) {
                canonBall.launchProjectile(getFirePosition(),
                        direction.multLocal(100), rotation);
                pcs.firePropertyChange(Commands.SHOOT, null, null);
                return;
            }
        }
    }
    
    /**
     *
     */
    @Override
    public synchronized void shootMissile() {
        for (MissileModel missile : missiles) {
            if (!missile.isInWorld()) {
                missile.launchProjectile(new Vector3f(position).addLocal(0, 4, 0),
                        new Vector3f(0, 10, 0), rotation);
                pcs.firePropertyChange(Commands.SHOOT, null, null);
                return;
            }
        }
    }
    
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    
    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /**
     *
     * @param tpf
     */
    @Override
    public void update(float tpf) {
        // Keep vehicle within max speeds
        float oldAcceleration = accelerationValue;
        float maxSpeed = (acceleration >= 0 ? this.currentMaxSpeed : -backMaxSpeed);
        float speedFactor = (maxSpeed - currentVehicleSpeedKmHour) / maxSpeed;
        accelerationValue = acceleration * speedFactor;
        if (oldAcceleration != accelerationValue) {
            pcs.firePropertyChange(Commands.ACCELERATE, oldAcceleration, accelerationValue);
        }
        pcs.firePropertyChange(Commands.SMOKE, null, null);
    }

    /**
     *
     */
    @Override
    public void accelerateForward() {
        incrementAcceleration(currentAccelerationForce);
    }

    /**
     *
     */
    @Override
    public void accelerateBack() {
        decrementAcceleration(currentAccelerationForce);
    }

    /**
     *
     */
    @Override
    public void steerLeft() {
        incrementSteeringValue(steeringChangeValue);
    }

    /**
     *
     */
    @Override
    public void steerRight() {
        decrementSteeringValue(steeringChangeValue);
    }

    /**
     *
     * @return
     */
    @Override
    public float getFrictionForce() {
        return frictionForce;
    }

    /**
     *
     * @param currentVehicleSpeedKmHour
     */
    @Override
    public void updateCurrentVehicleSpeedKmHour(float currentVehicleSpeedKmHour) {
        this.currentVehicleSpeedKmHour = currentVehicleSpeedKmHour;
    }

    /**
     *
     * @return
     */
    @Override
    public float getMass() {
        return mass;
    }

    /**
     *
     * @param pos
     */
    @Override
    public void updatePosition(Vector3f pos) {
        this.position = new Vector3f(pos);
    }

    /**
     *
     * @return
     */
    @Override
    public synchronized Vector3f getFirePosition() {
        return new Vector3f(position).addLocal(0, 0.9f, 0).addLocal(direction.multLocal(1.5f));
    }
    
    /**
     *
     * @return
     */
    @Override
    public Vector3f getSmokePosition() {
        return new Vector3f(position).addLocal(0, 2.05f, 0).subtractLocal(direction.multLocal(1.5f));
    }

    /**
     *
     * @param forwardVector
     */
    @Override
    public void updateDirection(Vector3f forwardVector) {
        direction = forwardVector.clone();
    }

    /**
     *
     * @return
     */
    @Override
    public Vector3f getDirection() {
        return direction.clone();
    }

    /**
     *
     * @return
     */
    @Override
    public Quaternion getRotation() {
        return rotation.clone();
    }

    /**
     *
     * @param rotation
     */
    @Override
    public void updateRotation(Quaternion rotation) {
        this.rotation = rotation.clone();
    }

    /**
     *
     */
    @Override
    public void applyFriction() {
        pcs.firePropertyChange(Commands.FRICTION, null, null);
    }

    /**
     *
     * @param maxSpeed
     */
    @Override
    public synchronized void setMaxSpeed(float maxSpeed) {
        this.currentMaxSpeed = maxSpeed;
    }

    /**
     *
     * @param accelerationForce
     */
    @Override
    public synchronized void setAccelerationForce(float accelerationForce) {
        this.currentAccelerationForce = accelerationForce;
    }
    
    /**
     *
     * @return
     */
    @Override
    public float getDefaultMaxSpeed(){
        return defaultMaxSpeed;
    }

    /**
     *
     * @param projectile
     */
    @Override
    public void gotHitBy(IExplodingProjectile projectile) {
        this.decrementHealth(projectile.getDamageOnImpact());
        if (health<=0){
            this.vehicleState = VehicleState.DESTROYED;
            pcs.firePropertyChange(Commands.HIDE, null, null);
        }
    }

    /**
     *
     */
    @Override
    public void cleanup() {
        pcs.firePropertyChange(Commands.CLEANUP, null, null);
    }

    /**
     *
     * @param position
     */
    @Override
    public void setPosition(Vector3f position) {
        this.position = position.clone();
    }

    @Override
    public void showInWorld() {
        health = 100;
        pcs.firePropertyChange(Commands.HEALTH, null, null);
        vehicleState = VehicleState.ALIVE;
        isInWorld = true;
        pcs.firePropertyChange(Commands.SHOW, null, null);
    }

    @Override
    public void hideFromWorld() {
        isInWorld = false;
        vehicleState = VehicleState.DESTROYED;
        pcs.firePropertyChange(Commands.HIDE, null, null);
    }

    /**
     *
     * @return
     */
    @Override
    public Vector3f getPosition() {
        return new Vector3f(position);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isInWorld() {
        return this.isInWorld;
    }

    /**
     *
     */
    @Override
    public void resetSpeedValues() {
        currentMaxSpeed = defaultMaxSpeed;
        currentAccelerationForce = defaultAccelerationForce;
    }

    /**
     *
     * @param ex
     * @throws IOException
     */
    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param im
     * @throws IOException
     */
    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
