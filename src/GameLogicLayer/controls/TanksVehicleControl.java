
package GameLogicLayer.controls;

import GameLogicLayer.entity.GameEntityManager;
import GameLogicLayer.player.EPlayerInputs;
import GameLogicLayer.viewPort.VehicleCamera;
import GameModelLayer.gameEntity.Projectile.IProjectile;
import GameModelLayer.gameEntity.Projectile.ProjectileModel;
import GameModelLayer.gameEntity.Vehicle.IArmedVehicle;
import GameModelLayer.gameEntity.Vehicle.TankModel;
import GameViewLayer.gameEntity.Tank;
import GameViewLayer.gameEntity.MissileProjectile;
import GameViewLayer.gameEntity.EGameEntities;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *  A control of a tank vehicle.
 * 
 * @author Daniel
 */
public class TanksVehicleControl extends BaseControl implements ActionListener {
    
    // The model for the vehicle (holds the data)
    private IArmedVehicle vehicleModel;
    private VehicleControl vehicle;
    
    // Variables needed to fire projectiles
    private IProjectile projectileModel;
    private PhysicsSpace physicsSpace;
    private Node rootNode;
 
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
    private float counter;
    private static final float SHOOTING_DELAY = 1.0f;
    
    // Needed to manage inputs
    private InputManager inputManager;
 
    /**
     * Creates a control for a tank vehicle.
     */
    public TanksVehicleControl() {
        // Get needed managers     
        inputManager = app.getInputManager();        
        
        physicsSpace = app.getBulletAppState().getPhysicsSpace();
        rootNode = app.getRootNode();
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
            
            counter = counter + tpf;
            
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
            Tank tank = spatial.getUserData("entity");
            vehicle = tank.getVehicleControl();
        }  
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
                if (counter>SHOOTING_DELAY) {
                    counter = 0;
                    MissileProjectile projectileEntity = (MissileProjectile) app.getEntityManager().create(EGameEntities.MISSILE_PROJECTILE);
                    projectileEntity.setDirection(vehicle.getForwardVector(null));
                    Spatial projectile = projectileEntity.getSpatial();
                    projectile.setLocalTranslation(spatial.getWorldTranslation().addLocal(0, 1, 0).addLocal(vehicle.getForwardVector(null).multLocal(3f)));
                    projectile.setLocalRotation(spatial.getWorldRotation());
                    projectileEntity.finalise();
                    // Attach to world and phsysicsSpace
                    rootNode.attachChild(projectile);

                    /*
                    // Get a projectilespatial and translate it to weapon
                    Spatial projectile = projectileSpatial.getProjectileSpatial();
                    //projectile.setLocalTranslation(weaponSpatial.getWeaponSpatial().getWorldTranslation());
                    projectile.setLocalTranslation(spatial.getWorldTranslation().addLocal(0, 1, 0));

                    // Create a RigidBodyControl over the projectile collision shape
                    RigidBodyControl projectileControl = new RigidBodyControl(
                    projectileSpatial.getProjectileCollisionShape(), projectileModel.getMass());
                    projectileControl.setCcdMotionThreshold(0.1f);

                    // TODO Solve direction of velocity, should be same as weapon direction
    //                projectileControl.setLinearVelocity(weaponSpatial.getAttackDirection().mult(200));
                    projectileControl.setLinearVelocity(driveDirection.multLocal(200));
                    projectile.addControl(projectileControl);


                    physicsSpace.add(projectileControl);
                    // Attach to world and phsysicsSpace
                    rootNode.attachChild(projectile);

                    // Create a controller of the projectile
                    //TankProjectileManager projectileManager = new TankProjectileManager(projectileModel,
                                                                //projectileSpatial, physicsSpace);*/

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
        inputManager.addMapping(turnLeft, new KeyTrigger(left));
        inputManager.addMapping(turnRight, new KeyTrigger(right));
        inputManager.addMapping(accelerateForward, new KeyTrigger(up));
        inputManager.addMapping(accelerateBack, new KeyTrigger(down));
        inputManager.addMapping(reset, new KeyTrigger(resetI));
        inputManager.addMapping(shoot, new KeyTrigger(shootI));
        // Registers this as an listener for the specified mappingnames
        inputManager.addListener(this, turnLeft, turnRight, accelerateForward, accelerateBack, reset, shoot);
        
        // These mappings are now in use and cant be used by other players
        inputs.setInUse(true);
    }

    private void removeInputMappings() {
        if (inputs == null) {
            return;
        }
        inputManager.deleteMapping(turnLeft);
        inputManager.deleteMapping(turnRight);
        inputManager.deleteMapping(accelerateForward);
        inputManager.deleteMapping(accelerateBack);
        inputManager.deleteMapping(reset);
        inputManager.deleteMapping(shoot);
        inputManager.removeListener(this);

        inputs.setInUse(false);
    }

    /**
     * Initiates the third person camera that follows the vehicle.
     *
     * @param spatial The spatial to be followed by the camera.
     */
    private void setUpCam(Camera cam) {
        // Chasecam properties
        chaseCam = new VehicleCamera(cam, spatial, inputManager);
        chaseCam.setMaxDistance(20);
        chaseCam.setMinDistance(10);
        chaseCam.setDefaultDistance(15);
        chaseCam.setChasingSensitivity(5f);
        chaseCam.setSmoothMotion(true); //automatic following
        chaseCam.setUpVector(Vector3f.UNIT_Y);
        chaseCam.setTrailingEnabled(true);
        chaseCam.setDefaultVerticalRotation(0.3f);
    }
}
