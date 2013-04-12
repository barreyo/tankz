
package GameControllers.entitycontrols;

import GameView.gameEntity.GameEntityFactory;
import GameControllers.player.EPlayerInputs;
import GameView.viewPort.VehicleCamera;
import GameModel.gameEntity.Projectile.IProjectile;
import GameModel.gameEntity.Projectile.ProjectileModel;
import GameModel.gameEntity.Vehicle.IArmedVehicle;
import GameModel.gameEntity.Vehicle.TankModel;
import GameUtilities.TankAppAdapter;
import GameView.GUI.FloatingNameControl;
import GameView.gameEntity.Tank;
import GameView.gameEntity.MissileProjectileEntity;
import GameView.gameEntity.EGameEntities;
import GameView.viewPort.VehicleCameraFactory;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Spatial;

/**
 *  A control of a tank vehicle.
 * 
 * @author Daniel
 */
public class TanksVehicleControl extends BaseControl implements ActionListener {
    
    // The model for the vehicle (holds the data)
    private IArmedVehicle vehicleModel;
    private Tank tank;
    
    private VehicleControl vehicle;
    
    // Variables needed to fire projectiles
    private IProjectile projectileModel;
 
    // Cam to be set up behind Vehicle
    private VehicleCamera chaseCam;
    
    // Input related commands
    private EPlayerInputs inputs;
    private String turnLeft;
    private String turnRight;
    private String accelerateForward;
    private String accelerateBack;
    private String reset;
    private String shoot;
 
    /**
     * Creates a control for a tank vehicle.
     */
    public TanksVehicleControl() {    
        projectileModel = new ProjectileModel(10, 0.001f);
        
        // Create a model for the vehicle
        // TODO should have same model as player
        vehicleModel = new TankModel();
        vehicleModel.setAccelerationForce(2000.0f);
        vehicleModel.setForwardMaxSpeed(80f);
        vehicleModel.setBackMaxSpeed(30f);
        vehicleModel.setBrakeForce(10000.0f);
        // Register input mappings
        addInputMappings();
    }

    /*
     * @inheritdoc
     */
    @Override
    void controlUpdate(float tpf) {
        if (enabled) {
            if (vehicle == null || chaseCam == null) {
                return;
            }

            // Keep vehicle within max speeds
            float maxSpeed = (vehicleModel.getAccelerationValue() >= 0
                    ? vehicleModel.getForwardMaxSpeed()
                    : -vehicleModel.getBackMaxSpeed());
            float speedFactor = (maxSpeed - vehicle.getCurrentVehicleSpeedKmHour()) / maxSpeed;
            vehicle.accelerate(vehicleModel.getAccelerationValue() * speedFactor);

            chaseCam.setHorizonalLookAt(vehicle.getForwardVector(null).multLocal(new Vector3f(1, 0, 1)));
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);

        if (spatial != null) {
            // Get the visual representation of the tank and the applied vehicle control
            tank = spatial.getUserData("entity");
            
            initControl();
        }  
    }
    
    private void initControl() {
        if (vehicle == null) {
            addVehicleControl();
        }
        // ?
        spatial.addControl(new FloatingNameControl(spatial,
                TankAppAdapter.INSTANCE.getAssetManager()));
    }

    private void addVehicleControl() {
        CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        compoundShape.addChildShape(tank.getCollisionShape(), new Vector3f(0, 1, 0));

        // Create the actual vehicle control
        vehicle = new VehicleControl(compoundShape, 600);
        spatial.addControl(vehicle);
        spatial.setShadowMode(RenderQueue.ShadowMode.Cast);

        vehicle.setSuspensionCompression(TankModel.TANK_COMP_VALUE * 2.0f
                                * FastMath.sqrt(TankModel.TANK_STIFFNESS));
        vehicle.setSuspensionDamping(TankModel.TANK_DAMP_VALUE * 2.0f 
                                * FastMath.sqrt(TankModel.TANK_STIFFNESS));
        vehicle.setSuspensionStiffness(TankModel.TANK_STIFFNESS);
        vehicle.setMaxSuspensionForce(999000.0f);

        vehicle.addWheel(null, new Vector3f(-TankModel.TANK_WHEEL_X_OFF,
                TankModel.TANK_WHEEL_Y_OFF, TankModel.TANK_WHEEL_Z_OFF),
                TankModel.TANK_WHEEL_DIRECTION, TankModel.TANK_WHEEL_AXIS,
                TankModel.TANK_WHEEL_REST_LENGTH, TankModel.TANK_WHEEL_RADIUS,
                true);

        vehicle.addWheel(null, new Vector3f(TankModel.TANK_WHEEL_X_OFF,
                TankModel.TANK_WHEEL_Y_OFF, TankModel.TANK_WHEEL_Z_OFF),
                TankModel.TANK_WHEEL_DIRECTION, TankModel.TANK_WHEEL_AXIS,
                TankModel.TANK_WHEEL_REST_LENGTH, TankModel.TANK_WHEEL_RADIUS,
                true);

        vehicle.addWheel(null, new Vector3f(-TankModel.TANK_WHEEL_X_OFF,
                TankModel.TANK_WHEEL_Y_OFF, -TankModel.TANK_WHEEL_Z_OFF),
                TankModel.TANK_WHEEL_DIRECTION, TankModel.TANK_WHEEL_AXIS,
                TankModel.TANK_WHEEL_REST_LENGTH, TankModel.TANK_WHEEL_RADIUS,
                false);

        vehicle.addWheel(null, new Vector3f(TankModel.TANK_WHEEL_X_OFF,
                TankModel.TANK_WHEEL_Y_OFF, -TankModel.TANK_WHEEL_Z_OFF),
                TankModel.TANK_WHEEL_DIRECTION, TankModel.TANK_WHEEL_AXIS,
                TankModel.TANK_WHEEL_REST_LENGTH, TankModel.TANK_WHEEL_RADIUS,
                false);
        
        // Specify the groups to coolide with
        //vehicle.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_03);
        //vehicle.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_01 | PhysicsCollisionObject.COLLISION_GROUP_02 | PhysicsCollisionObject.COLLISION_GROUP_04);
        // Add vehicle to physics space
        TankAppAdapter.INSTANCE.addToPhysicsSpace(vehicle);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void cleanup() {
        // Remove this as a control and remove inputs
        super.cleanup();
        removeInputMappings();

        //GameState.setMoving(false);
    }

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
            vehicle.steer(vehicleModel.getSteeringValue());
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
            vehicle.steer(vehicleModel.getSteeringValue());
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
                vehicle.setPhysicsLocation(Vector3f.ZERO);
                vehicle.setPhysicsRotation(new Matrix3f());
                vehicle.setLinearVelocity(Vector3f.ZERO);
                vehicle.setAngularVelocity(Vector3f.ZERO);
                vehicle.resetSuspension();
            }
        } else if (name.equals(shoot)) {
            if (!isPressed) {
                
                MissileProjectileEntity projectileEntity = (MissileProjectileEntity) GameEntityFactory.create(EGameEntities.MISSILE_PROJECTILE);
                projectileEntity.setDirection(vehicle.getForwardVector(null));
                Spatial projectile = projectileEntity.getSpatial();
                projectile.setLocalTranslation(spatial.getWorldTranslation().addLocal(0, 1, 0).addLocal(vehicle.getForwardVector(null).multLocal(3f)));
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
    
    /**
     * Sets the camera that will be used to follow the tank.
     * 
     * @param cam the camera that will follow the tank
     */
    public void setCamera(Camera cam) {
        setUpCam(cam);
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

    /**
     * Initiates the third person camera that follows the vehicle.
     *
     * @param spatial The spatial to be followed by the camera.
     */
    private void setUpCam(Camera cam) {
        chaseCam = VehicleCameraFactory.getVehicleChaseCamera(cam, spatial);
    }
}
