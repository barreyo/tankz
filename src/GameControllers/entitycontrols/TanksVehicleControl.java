
package GameControllers.entitycontrols;

import GameView.gameEntity.GameEntityFactory;
import GameControllers.player.EPlayerInputs;
import GameModel.Game.EGameState;
import GameModel.Player.IPlayer;
import GameView.viewPort.VehicleCamera;
import GameModel.gameEntity.Projectile.IProjectile;
import GameModel.gameEntity.Projectile.ProjectileModel;
import GameModel.gameEntity.Vehicle.IArmedVehicle;
import GameUtilities.TankAppAdapter;
import GameView.GUI.FloatingNameControl;
import GameView.gameEntity.Tank;
import GameView.gameEntity.MissileProjectileEntity;
import GameView.gameEntity.EGameEntities;
import GameView.gameEntity.IGameEntity;
import GameView.viewPort.VehicleCameraFactory;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;

/**
 *  A control of a tank vehicle.
 * 
 * Extends JMonkey predefined VehicleControl class.
 * 
 * @author Daniel
 */
public class TanksVehicleControl extends VehicleControl implements ActionListener {
    
    // The model for the vehicle
    private IArmedVehicle vehicleModel;
    // View of the vehicle
    private IGameEntity entity;
    // The player controlling
    private IPlayer player;
   
    // Variables needed to fire projectiles
    private IProjectile projectileModel;
    
    // Cam to be set up behind Vehicle
    private VehicleCamera chaseCam;
 
    /**
     * Creates a control for a tank vehicle.
     */
    public TanksVehicleControl(CollisionShape shape, float mass, IGameEntity entity, IPlayer player) {  
        super(shape, mass);
        
        // TODO remove (use factory)
        projectileModel = new ProjectileModel(10, 0.001f);
        
        // Save references to model, view and player
        this.entity = entity;
        this.vehicleModel = player.getVehicle();
        this.player = player;
        
        // Register input mappings
        addInputMappings();
    }

    /*
     * @inheritdoc
     */
    @Override
    public void update(float tpf) {
        if (!enabled || EGameState.getGameState() != EGameState.RUNNING) {
            return;
        }
        super.update(tpf);
        if (chaseCam == null) {
            return;
        }
        
        // Keep vehicle within max speeds
        float maxSpeed = (vehicleModel.getAccelerationValue() >= 0
                ? vehicleModel.getForwardMaxSpeed()
                : -vehicleModel.getBackMaxSpeed());
        float speedFactor = (maxSpeed - this.getCurrentVehicleSpeedKmHour()) / maxSpeed;
        this.accelerate(vehicleModel.getAccelerationValue() * speedFactor);

        chaseCam.setHorizonalLookAt(this.getForwardVector(null).multLocal(new Vector3f(1, 0, 1)));

    }

    /**
     * @inheritdoc
     */
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        if (spatial != null) {
            initControl();
        }  
    }
    
    private void initControl() {
        addVehicleControl();
        // ?
        spatial.addControl(new FloatingNameControl(spatial,
                TankAppAdapter.INSTANCE.getAssetManager()));
    }

    private void addVehicleControl() {
        // Add vehicle to physics space
        TankAppAdapter.INSTANCE.addToPhysicsSpace(this);
    }
    
    /**
     * Sets the camera that will be used to follow the tank.
     * 
     * @param cam the camera that will follow the tank
     */
    public void setCamera(Camera cam) {
        setUpCam(cam);
    }
    
    /**
     * Initiates the third person camera that follows the vehicle.
     *
     * @param spatial The spatial to be followed by the camera.
     */
    private void setUpCam(Camera cam) {
        chaseCam = VehicleCameraFactory.getVehicleChaseCamera(cam, spatial);
    }

    /**
     * @inheritdoc
     */
    public void cleanup() {
        // Remove this as a control and remove inputs
        spatial.removeControl(this);
        removeInputMappings();
    }
    
    // Input related commands
    private EPlayerInputs inputs;
    private String turnLeft;
    private String turnRight;
    private String accelerateForward;
    private String accelerateBack;
    private String reset;
    private String shoot;

    private boolean isFirstLeftKeyPressDone;
    private boolean isFirstRightKeyPressDone;
    private boolean isFirstUpKeyPressDone;
    private boolean isFirstDownKeyPressDone;
    /**
     * @inheritdoc
     */
    public void onAction(String name, boolean isPressed, float tpf) {
       
        // Steering related
        if (name.equals(turnLeft)) {
            if (isPressed) {
                if (!isFirstLeftKeyPressDone) {
                    isFirstLeftKeyPressDone = true;
                }
                vehicleModel.incrementSteeringValue(.4f);
            } else {
                if (!isFirstLeftKeyPressDone) {
                    return;
                }
                vehicleModel.decrementSteeringValue(.4f);
            }
            this.steer(vehicleModel.getSteeringValue());
        } else if (name.equals(turnRight)) {
            if (isPressed) {
                if (!isFirstRightKeyPressDone) {
                    isFirstRightKeyPressDone = true;
                }
                vehicleModel.decrementSteeringValue(.4f);
            } else {
                if (!isFirstRightKeyPressDone) {
                    return;
                }
                vehicleModel.incrementSteeringValue(.4f);
            }
            this.steer(vehicleModel.getSteeringValue());
        } else if (name.equals(accelerateForward)) {
            if (isPressed) {
                if (!isFirstUpKeyPressDone) {
                    isFirstUpKeyPressDone = true;
                }
                vehicleModel.incrementAccelerationValue(vehicleModel.getAccelerationForce());       
            } else {
                if (!isFirstUpKeyPressDone) {
                    return;
                }
                vehicle.brake(vehicleModel.getBrakeForce());
                vehicleModel.decrementAccelerationValue(vehicleModel.getAccelerationForce());
            }
        } else if (name.equals(accelerateBack)) {
            if (isPressed) {
                if (!isFirstDownKeyPressDone) {
                    isFirstDownKeyPressDone = true;
                }
                // TODO ny input för bromsning?
                //vehicle.brake(vehicleModel.getBrakeForce());
                vehicleModel.decrementAccelerationValue(vehicleModel.getAccelerationForce());
            } else {
                if (!isFirstDownKeyPressDone) {
                    return;
                }
                vehicleModel.incrementAccelerationValue(vehicleModel.getAccelerationForce()); 
                
                //vehicle.brake(0f);
            }
        } else if (name.equals(reset)) {
            if (isPressed) {
                System.out.println("Reset");
                this.setPhysicsLocation(Vector3f.ZERO);
                this.setPhysicsRotation(new Matrix3f());
                this.setLinearVelocity(Vector3f.ZERO);
                this.setAngularVelocity(Vector3f.ZERO);
                vehicle.resetSuspension();
            }
        } else if (name.equals(shoot)) {
            if (!isPressed) {
                
                MissileProjectileEntity projectileEntity = (MissileProjectileEntity) GameEntityFactory.create(EGameEntities.MISSILE_PROJECTILE);
                projectileEntity.setDirection(this.getForwardVector(null));
                Spatial projectile = projectileEntity.getSpatial();
                projectile.setLocalTranslation(spatial.getWorldTranslation().addLocal(0, 1, 0).addLocal(this.getForwardVector(null).multLocal(3f)));
                projectile.setLocalRotation(spatial.getWorldRotation());
                TankProjectileControl projectileControl = (TankProjectileControl)ControlFactory.getControl(EControls.PROJECTILE_CONTROL);
                projectileEntity.addControl(projectileControl);
                // Attach to world and phsysicsSpace
                TankAppAdapter.INSTANCE.attachChildToRootNode(projectile);
            }
        }
        //boolean isMoving = left || right || up || down;
        //GameState.setMoving(isMoving);
    }

    private void addInputMappings() {
        // Looks for unused input mappings
        for (EPlayerInputs playerInputs : EPlayerInputs.values()) {
            if (!playerInputs.isInUse()) {
                inputs = playerInputs;
                break;
            }
        }
        if (inputs == null) {
            return;
        }
        
        // Gets input keycodes
        int left = inputs.getLeftKey();
        int right = inputs.getRightKey();
        int up = inputs.getUpKey();
        int down = inputs.getDownKey();
        int resetI = inputs.getResetKey();
        int shootI = inputs.getShootKey();
        
        // Specifies mappingnames for input
        turnLeft = "" + left;
        turnRight = "" + right;
        accelerateForward = "" + up;
        accelerateBack = "" + down;
        reset = "" + resetI;
        shoot = "" + shootI;
        
        // Adds the mappings to inputmanager
        TankAppAdapter.INSTANCE.addInputMapping(turnLeft, new KeyTrigger(left));
        TankAppAdapter.INSTANCE.addInputMapping(turnRight, new KeyTrigger(right));
        TankAppAdapter.INSTANCE.addInputMapping(accelerateForward, new KeyTrigger(up));
        TankAppAdapter.INSTANCE.addInputMapping(accelerateBack, new KeyTrigger(down));
        TankAppAdapter.INSTANCE.addInputMapping(reset, new KeyTrigger(resetI));
        TankAppAdapter.INSTANCE.addInputMapping(shoot, new KeyTrigger(shootI));
        // Registers this as an listener for the specified mappingnames
        TankAppAdapter.INSTANCE.addInputListener(this, turnLeft, turnRight, accelerateForward, accelerateBack, reset, shoot);
        
        // These mappings are now in use and cant be used by other players
        inputs.setInUse(true);
    }

    private void removeInputMappings() {
        if (inputs == null) {
            return;
        }
        TankAppAdapter.INSTANCE.deleteInputMapping(turnLeft);
        TankAppAdapter.INSTANCE.deleteInputMapping(turnRight);
        TankAppAdapter.INSTANCE.deleteInputMapping(accelerateForward);
        TankAppAdapter.INSTANCE.deleteInputMapping(accelerateBack);
        TankAppAdapter.INSTANCE.deleteInputMapping(reset);
        TankAppAdapter.INSTANCE.deleteInputMapping(shoot);
        TankAppAdapter.INSTANCE.removeInputListener(this);

        inputs.setInUse(false);
    }
}
