
package GameLogicLayer.controls;

import GameLogicLayer.util.EPlayerInputs;
import GameModelLayer.Game.GameState;
import GameModelLayer.gameEntity.Projectile.IProjectile;
import GameModelLayer.gameEntity.Projectile.ProjectileModel;
import GameModelLayer.gameEntity.Vehicle.IArmedVehicle;
import GameModelLayer.gameEntity.Vehicle.TankModel;
import GameViewLayer.gameEntity.MainTank;
import GameViewLayer.gameEntity.Projectile.IProjectileSpatial;
import GameViewLayer.gameEntity.Projectile.TankProjectileSpatial;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.CameraControl;

/**
 *  A control of a tank vehicle.
 * 
 * @author Daniel
 */
public class TanksVehicleControl extends BaseControl implements ActionListener {
    private Vector3f driveDirection = new Vector3f();
    
    // The model for the vehicle (holds the data)
    private IArmedVehicle vehicleModel;
    private VehicleControl vehicle;
    
    // Projectile stuff
    // TODO should be entitys
    private IProjectile projectileModel;
    private IProjectileSpatial projectileSpatial;
    private PhysicsSpace physicsSpace;
    private Node rootNode;
    
    
    // Cam to be set up behind Vehicle
    private Camera cam;
    
    // Input related commands
    private EPlayerInputs inputs;
    private String TURN_LEFT;
    private String TURN_RIGHT;
    private String ACCELERATE_FORWARD;
    private String ACCELERATE_BACK;
    private String RESET;
    private String SHOOT;
    
    
    private InputManager inputManager;
    
    /*
     * Creates a control for a tank vehicle.
     */
    /**
     *
     */
    public TanksVehicleControl() {
        // Get needed managers     
        inputManager = app.getInputManager();
        
        // TODO change this
        physicsSpace = app.getBulletAppState().getPhysicsSpace();
        rootNode = app.getRootNode();
        projectileModel = new ProjectileModel(10, 0.001f);
        projectileSpatial = new TankProjectileSpatial(app.getAssetManager(), 0.4f);
        
        // Create a model for the vehicle
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
        if (vehicle == null || cam == null) {
            return;
        }
        driveDirection = vehicle.getForwardVector(null);
        
        float maxSpeed = (vehicleModel.getAccelerationValue() >= 0 ? 
                          vehicleModel.getForwardMaxSpeed() :
                          -vehicleModel.getBackMaxSpeed());
        float speedFactor = (maxSpeed-vehicle.getCurrentVehicleSpeedKmHour())/maxSpeed;
        vehicle.accelerate(vehicleModel.getAccelerationValue() * speedFactor);
    }

    /*
     * @inheritdoc
     */
    /**
     *
     * @param spatial
     */
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);

        if (spatial != null) {
            // Get the visual representation of the tank and the applied vehicle control
            MainTank tank = spatial.getUserData("entity");
            vehicle = tank.getVehicleControl();
        }
    }

    /*
     * @inheritdoc
     */
    @Override
    public void cleanup() {
        // Remove this as a control and remove inputs
        super.cleanup();
        removeInputMappings();

        //GameState.setMoving(false);
    }

    public void onAction(String name, boolean isPressed, float tpf) {
        // Steering related
        if (name.equals(TURN_LEFT)) {
            if (isPressed) {
                vehicleModel.incrementSteeringValue(.4f);
            } else {
                vehicleModel.decrementSteeringValue(.4f);
            }
            vehicle.steer(vehicleModel.getSteeringValue());
        } else if (name.equals(TURN_RIGHT)) {
            if (isPressed) {
                vehicleModel.decrementSteeringValue(.4f);
            } else {
                vehicleModel.incrementSteeringValue(.4f);
            }
            vehicle.steer(vehicleModel.getSteeringValue());
        } else if (name.equals(ACCELERATE_FORWARD)) {
            if (isPressed) {
                vehicleModel.incrementAccelerationValue(vehicleModel.getAccelerationForce());       
            } else {
                vehicleModel.decrementAccelerationValue(vehicleModel.getAccelerationForce());
            }
        } else if (name.equals(ACCELERATE_BACK)) {
            if (isPressed) {
                // TODO ny input för bromsning?
                //vehicle.brake(vehicleModel.getBrakeForce());
                vehicleModel.decrementAccelerationValue(vehicleModel.getAccelerationForce());
            } else {
                //vehicle.brake(0f);
                vehicleModel.incrementAccelerationValue(vehicleModel.getAccelerationForce());
            }
        } else if (name.equals(RESET)) {
            if (isPressed) {
                System.out.println("Reset");
                vehicle.setPhysicsLocation(Vector3f.ZERO);
                vehicle.setPhysicsRotation(new Matrix3f());
                vehicle.setLinearVelocity(Vector3f.ZERO);
                vehicle.setAngularVelocity(Vector3f.ZERO);
                vehicle.resetSuspension();
            }
        } else if (name.equals(SHOOT)) {
            if (!isPressed) {
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
                                                            //projectileSpatial, physicsSpace);
            }
        }

        //boolean isMoving = left || right || up || down;
        //GameState.setMoving(isMoving);
    }
    
    public void setCamera(Camera cam) {
        this.cam = cam;
        setUpCam();
    }

    private void addInputMappings() {
        // Looks for unused input mappings
        for (EPlayerInputs player : EPlayerInputs.values()) {
            if (!player.isInUse()) {
                inputs = player;
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
        int reset = inputs.getResetKey();
        int shoot = inputs.getShootKey();
        
        // Specifies mappingnames for input
        TURN_LEFT = "" + left;
        TURN_RIGHT = "" + right;
        ACCELERATE_FORWARD = "" + up;
        ACCELERATE_BACK = "" + down;
        RESET = "" + reset;
        SHOOT = "" + shoot;
        
        // Adds the mappings to inputmanager
        inputManager.addMapping(TURN_LEFT, new KeyTrigger(left));
        inputManager.addMapping(TURN_RIGHT, new KeyTrigger(right));
        inputManager.addMapping(ACCELERATE_FORWARD, new KeyTrigger(up));
        inputManager.addMapping(ACCELERATE_BACK, new KeyTrigger(down));
        inputManager.addMapping(RESET, new KeyTrigger(reset));
        inputManager.addMapping(SHOOT, new KeyTrigger(shoot));
        // Registers this as an listener for the specified mappingnames
        inputManager.addListener(this, TURN_LEFT, TURN_RIGHT, ACCELERATE_FORWARD, ACCELERATE_BACK, RESET, SHOOT);
        
        // These mappings are now in use and cant be used by other players
        inputs.setInUse(true);
    }

    private void removeInputMappings() {
        inputManager.deleteMapping(TURN_LEFT);
        inputManager.deleteMapping(TURN_RIGHT);
        inputManager.deleteMapping(ACCELERATE_FORWARD);
        inputManager.deleteMapping(ACCELERATE_BACK);
        inputManager.deleteMapping(RESET);
        inputManager.deleteMapping(SHOOT);
        inputManager.removeListener(this);
        
        inputs.setInUse(false);
    }

    /**
     * Initiates the third person camera that follows the vehicle.
     *
     * @param spatial The spatial to be followed by the camera.
     */
    private void setUpCam() {
        Node vehicleNode = (Node)spatial;
        // Disable the default flyby cam
        //app.getFlyByCamera().setEnabled(false);
        //create the camera Node
        CameraNode camNode = new CameraNode("Camera Node", cam);
        //This mode means that camera copies the movements of the target:
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        //Attach the camNode to the target:
        vehicleNode.attachChild(camNode);
        //Move camNode, e.g. behind and above the target:
        camNode.setLocalTranslation(new Vector3f(0, 3, -15));
        //Rotate the camNode to look at the target:
        camNode.lookAt(vehicleNode.getLocalTranslation(), Vector3f.UNIT_Y);
    }
}
