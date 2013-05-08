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
    private List<LandmineModel> landmines;
    
    /**
     *
     * @param canonBalls
     * @param missiles
     */
    public TankModel(List<CanonBallModel> canonBalls, 
            List<MissileModel> missiles, List<LandmineModel> landmines) {
        this.canonBalls = canonBalls;
        this.missiles = missiles;
        this.landmines = landmines;
        mass = 600.0f;
        health = 100;
        defaultMaxSpeed = 80.0f;
        currentMaxSpeed = defaultMaxSpeed;
        backMaxSpeed = 30.0f;
        defaultAccelerationForce = 3000.0f;
        currentAccelerationForce = defaultAccelerationForce;
        brakeForce = 10000.0f;
        frictionForce = 10.0f;
        steeringChangeValue = 0.4f;
        position = Vector3f.ZERO;
        direction = Vector3f.ZERO;
        rotation = Quaternion.ZERO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IArmedVehicle.VehicleState getVehicleState() {
        return vehicleState;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void applyDamage(int hp) {
        if (hp != 0) {
            int oldHP = health;
            if (health - hp <= 0) {
                health = 0;
                hideFromWorld();
            } else {
                health -= hp;
            }
            pcs.firePropertyChange(Commands.HEALTH, oldHP, health);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void shoot() {
        for (CanonBallModel canonBall : canonBalls) {
            if (!canonBall.isShownInWorld()) {
                canonBall.launchProjectile(getFirePosition(),
                        direction.multLocal(100), rotation);
                pcs.firePropertyChange(Commands.SHOOT, null, null);
                return;
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void shootMissile() {
        for (MissileModel missile : missiles) {
            if (!missile.isShownInWorld()) {
                missile.launchProjectile(new Vector3f(position).addLocal(0, 4, 0),
                        new Vector3f(0, 10, 0), rotation);
                pcs.firePropertyChange(Commands.SHOOT, null, null);
                return;
            }
        }
    }
    
    @Override
    public synchronized void dropLandmine() {
        for (LandmineModel landmine : landmines) {
            if (!landmine.isShownInWorld()) {
                landmine.dropMine(new Vector3f(position).add(direction.mult(2f).negate()));
                return;
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        float oldAcceleration = accelerationValue;
        float maxSpeed = (acceleration >= 0 ? this.currentMaxSpeed : -backMaxSpeed);
        float speedFactor = (maxSpeed - currentVehicleSpeedKmHour) / maxSpeed;
        accelerationValue = acceleration * speedFactor;
        pcs.firePropertyChange(Commands.ACCELERATE, oldAcceleration, accelerationValue);

        pcs.firePropertyChange(Commands.SMOKE, null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accelerateForward() {
        this.acceleration += currentAccelerationForce;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accelerateBack() {
       this.acceleration -= currentAccelerationForce;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void steerLeft() {
        float oldSteeringValue = steeringValue;
        this.steeringValue += steeringChangeValue;
        pcs.firePropertyChange(Commands.STEER, oldSteeringValue, steeringValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void steerRight() {
        float oldSteeringValue = steeringValue;
        this.steeringValue -= steeringChangeValue;
        pcs.firePropertyChange(Commands.STEER, oldSteeringValue, steeringValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCurrentVehicleSpeedKmHour(float currentVehicleSpeedKmHour) {
        this.currentVehicleSpeedKmHour = currentVehicleSpeedKmHour;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getMass() {
        return mass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Vector3f getFirePosition() {
        return new Vector3f(position).addLocal(0, 0.9f, 0).addLocal(direction.multLocal(1.5f));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3f getExhaustPosition() {
        return new Vector3f(position).addLocal(0, 2.05f, 0).subtractLocal(direction.multLocal(1.5f));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDirection(Vector3f forwardVector) {
        direction = forwardVector.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3f getDirection() {
        return direction.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quaternion getRotation() {
        return rotation.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRotation(Quaternion rotation) {
        this.rotation = rotation.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyFriction() {
        pcs.firePropertyChange(Commands.FRICTION, null, frictionForce);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxSpeed(float maxSpeed) {
        this.currentMaxSpeed = maxSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getDefaultMaxSpeed(){
        return defaultMaxSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanup() {
        pcs.firePropertyChange(Commands.CLEANUP, null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Vector3f position) {
        this.position = new Vector3f(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showInWorld() {
        health = 100;
        pcs.firePropertyChange(Commands.HEALTH, null, health);
        vehicleState = VehicleState.ALIVE;
        boolean wasInWorld = isInWorld;
        isInWorld = true;
        pcs.firePropertyChange(Commands.SHOW, wasInWorld, isInWorld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideFromWorld() {
        boolean wasInWorld = isInWorld;
        isInWorld = false;
        vehicleState = VehicleState.DESTROYED;
        pcs.firePropertyChange(Commands.HIDE, wasInWorld, isInWorld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3f getPosition() {
        return new Vector3f(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShownInWorld() {
        return this.isInWorld;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetSpeedValues() {
        currentMaxSpeed = defaultMaxSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
