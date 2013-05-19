package model;

import utilities.Commands;
import utilities.Constants;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Model for a tank vehicle.
 * 
 * @author Daniel
 */
public final class TankModel implements IArmedVehicle {
    
    private boolean flameIsShowing;
    private boolean firstFlameDisabling;
    private boolean smokeIsShowing;
    private boolean firstSmokeDisabling;
    private boolean paused;
    
    private int health;
    private final int maxHealth;
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
    private final float frictionForce;
    private final float backMaxSpeed;
    
    private float shootDelay;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    private final List<CanonBallModel> canonBalls;
    private final List<MissileModel> missiles;
    private final List<LandmineModel> landmines;
    private final List<AtomicBombModel> bombs;
    
    /**
     *
     * @param canonBalls
     * @param missiles
     */
    public TankModel(List<CanonBallModel> canonBalls, 
            List<MissileModel> missiles, List<LandmineModel> landmines, 
            List<AtomicBombModel> bombs) {
        paused = false;
        firstFlameDisabling = false;
        firstSmokeDisabling = true;
        this.smokeIsShowing = true;
        this.canonBalls = canonBalls;
        this.missiles = missiles;
        this.landmines = landmines;
        this.bombs = bombs;
        mass = 600.0f;
        maxHealth = 100;
        health = maxHealth;
        defaultMaxSpeed = 80.0f;
        currentMaxSpeed = defaultMaxSpeed;
        backMaxSpeed = 30.0f;
        defaultAccelerationForce = 3000.0f;
        currentAccelerationForce = defaultAccelerationForce;
        frictionForce = 10.0f;
        steeringChangeValue = 0.4f;
        position = Vector3f.ZERO;
        direction = Vector3f.ZERO;
        rotation = Quaternion.ZERO;
        shootDelay = 0.5f;
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
    public boolean applyDamageToKill(int hp) {
        int oldHP = health;
        if (health == 0) {
            return false;
        } else if (health - hp <= 0) {
            health = 0;
            hideFromWorld();
            pcs.firePropertyChange(Commands.HEALTH, oldHP, health);
            return true;
        } else {
            health -= hp;
            pcs.firePropertyChange(Commands.HEALTH, oldHP, health);
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void shoot(IPlayer player) {
        if(shootDelay<=0){
            //Can only shoot once every half second
            shootDelay = 0.5f;
            for (CanonBallModel canonBall : canonBalls) {
                if (!canonBall.isShownInWorld()) {
                    canonBall.launchProjectile(getFirePosition(),
                            direction.mult(100), rotation, player);
                    pcs.firePropertyChange(Commands.SHOOT, null, null);
                    return;
                }
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void shootMissile(IPlayer player) {
        for (MissileModel missile : missiles) {
            if (!missile.isShownInWorld()) {
                missile.launchProjectile(position.add(0, 4, 0),
                        new Vector3f(0, 10, 0), rotation, player);
                pcs.firePropertyChange(Commands.SHOOT, null, null);
                return;
            }
        }
    }
    
    @Override
    public synchronized void dropLandmine(IPlayer player) {
        for (LandmineModel landmine : landmines) {
            if (!landmine.isShownInWorld()) {
                landmine.dropMine(position.add(direction.mult(2f).negate()), player);
                return;
            }
        }
    }
    
    @Override
    public void dropBomb(IPlayer player) {
        Vector3f launchOrigin = position.add(direction.normalize().mult(50));
        launchOrigin.setY(Constants.NUKE_DROP_HEIGHT);
        Random rand = new Random();
        
        float zRandom = (rand.nextFloat() * 30) - 15;
        float xRandom = (rand.nextFloat() * 30) - 15;
        
        launchOrigin.addLocal(xRandom, 0, zRandom);
        for (int i = 0; i < bombs.size(); i++) {
            if (!bombs.get(i).isShownInWorld()) {
                
                bombs.get(i).launchProjectile(launchOrigin, new Vector3f(0, -80, 0), Quaternion.ZERO, player);
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
        if (isInWorld) {
            float oldAcceleration = accelerationValue;
            float maxSpeed = (acceleration >= 0 ? this.currentMaxSpeed : -backMaxSpeed);
            float speedFactor = (maxSpeed - currentVehicleSpeedKmHour) / maxSpeed;
            shootDelay -= tpf;
            accelerationValue = acceleration * speedFactor;
            pcs.firePropertyChange(Commands.ACCELERATE, oldAcceleration, accelerationValue);

            if (smokeIsShowing) {
                pcs.firePropertyChange(Commands.SHOW_SMOKE, null, null);
            } else if(!smokeIsShowing && firstSmokeDisabling) {
                firstSmokeDisabling = false;
                pcs.firePropertyChange(Commands.HIDE_SMOKE, null, null);
            }

            if (flameIsShowing) {
                pcs.firePropertyChange(Commands.SHOW_FLAME, null, null);
            } else if (!flameIsShowing && firstFlameDisabling) {
                firstFlameDisabling = false;
                pcs.firePropertyChange(Commands.HIDE_FLAME, null, null);
            }
        }
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
        return position.add(0, 1.7f, 0).add(direction.mult(1.1f));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3f getExhaustPosition() {
        return position.add(0, 2.05f, 0).subtract(direction.mult(1.5f));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDirection(Vector3f forwardVector) {
        direction = new Vector3f(forwardVector);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3f getDirection() {
        return new Vector3f(direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quaternion getRotation() {
        return new Quaternion(rotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRotation(Quaternion rotation) {
        this.rotation = new Quaternion(rotation);
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
        pcs.firePropertyChange(Commands.AIRCALL, null, null); // NEEDS TO TBE CHANGED
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showInWorld() {
        this.toggleSmoke(true);
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
        this.toggleSmoke(false);
        this.toggleFlame(false);
        boolean wasInWorld = isInWorld;
        isInWorld = false;
        steeringValue = 0f;
        accelerationValue = 0f;
        acceleration = 0f;
        currentVehicleSpeedKmHour = 0f;
        rotation = Quaternion.ZERO;
        direction = Vector3f.ZERO;
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

    @Override
    public void toggleFlame(boolean state) {
        if (state) {
            firstFlameDisabling = true;
        }
        flameIsShowing = state;
    }

    @Override
    public void toggleSmoke(boolean state) {
        if (state) {
            firstSmokeDisabling = true;
        }
        smokeIsShowing = state;
    }

    @Override
    public void heal(int heal) {
        int oldHP = health;
        if (health == 0) {
            return;
        } else if (health + heal >= maxHealth) {
            health = maxHealth;
            pcs.firePropertyChange(Commands.HEALTH, oldHP, health);
        } else {
            health += heal;
            pcs.firePropertyChange(Commands.HEALTH, oldHP, health);
        }
    }
}
