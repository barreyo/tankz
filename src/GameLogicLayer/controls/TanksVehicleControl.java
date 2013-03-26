
package GameLogicLayer.controls;

import GameModelLayer.Game.GameState;
import GameModelLayer.gameEntity.Vehicle.IArmedVehicle;
import GameModelLayer.gameEntity.Vehicle.TankModel;
import GameViewLayer.gameEntity.MainTank;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
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
    
    // Cam to be set up behind Vehicle
    private Camera cam;
    
    // Input related commands
    private final String TURN_LEFT = "TurnLeft";
    private final String TURN_RIGHT = "TurnRight";
    private final String ACCELERATE_FORWARD = "AccelerateForward";
    private final String ACCELERATE_BACK = "AccelerateBack";
    private final String RESET = "Reset";
    private InputManager inputManager;
    
    /*
     * Creates a control for a tank vehicle.
     */
    /**
     *
     */
    public TanksVehicleControl() {
        // Get needed managers
        cam = app.getCamera();
        inputManager = app.getInputManager();
        
        // Create a model for the vehicle
        vehicleModel = new TankModel();
        vehicleModel.setAccelerationForce(4000.0f);
        vehicleModel.setBrakeForce(100.0f);
        
        // Register input mappings
        addInputMappings();
    }

    /*
     * @inheritdoc
     */
    @Override
    void controlUpdate(float tpf) {
        if (vehicle == null) {
            return;
        }
        // TODO riktning
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
    
            // Set up the cam behind vehicle
            setUpCam(tank.getSpatial());
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
                vehicleModel.incrementSteeringValue(1f);
            } else {
                vehicleModel.decrementSteeringValue(1f);
            }
            vehicle.steer(vehicleModel.getSteeringValue());
        } else if (name.equals(TURN_RIGHT)) {
            if (isPressed) {
                vehicleModel.decrementSteeringValue(1f);
            } else {
                vehicleModel.incrementSteeringValue(1f);
            }
            vehicle.steer(vehicleModel.getSteeringValue());
        } else if (name.equals(ACCELERATE_FORWARD)) {
            if (isPressed) {
                vehicleModel.incrementAccelerationValue(vehicleModel.getAccelerationForce());
            } else {
                vehicleModel.decrementAccelerationValue(vehicleModel.getAccelerationForce());
            }
            vehicle.accelerate(vehicleModel.getAccelerationValue());
        } else if (name.equals(ACCELERATE_BACK)) {
            if (isPressed) {
                vehicle.brake(vehicleModel.getBrakeForce());
                vehicleModel.decrementAccelerationValue(vehicleModel.getAccelerationForce());
            } else {
                vehicle.brake(0f);
                vehicleModel.incrementAccelerationValue(vehicleModel.getAccelerationForce());
            }
            vehicle.accelerate(vehicleModel.getAccelerationValue());
        } else if (name.equals(RESET)) {
            if (isPressed) {
                System.out.println("Reset");
                vehicle.setPhysicsLocation(Vector3f.ZERO);
                vehicle.setPhysicsRotation(new Matrix3f());
                vehicle.setLinearVelocity(Vector3f.ZERO);
                vehicle.setAngularVelocity(Vector3f.ZERO);
                vehicle.resetSuspension();
            }
        }

        //boolean isMoving = left || right || up || down;
        //GameState.setMoving(isMoving);
    }

    private void addInputMappings() {
        inputManager.addMapping(TURN_LEFT, new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping(TURN_RIGHT, new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping(ACCELERATE_FORWARD, new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping(ACCELERATE_BACK, new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping(RESET, new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addListener(this, TURN_LEFT, TURN_RIGHT, ACCELERATE_FORWARD, ACCELERATE_BACK, RESET);
    }

    private void removeInputMappings() {
        inputManager.deleteMapping(TURN_LEFT);
        inputManager.deleteMapping(TURN_RIGHT);
        inputManager.deleteMapping(ACCELERATE_FORWARD);
        inputManager.deleteMapping(ACCELERATE_BACK);
        inputManager.deleteMapping(RESET);
        inputManager.removeListener(this);
    }

    /**
     * Initiates the third person camera that follows the vehicle.
     *
     * @param spatial The spatial to be followed by the camera.
     */
    private void setUpCam(Spatial spatial) {
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
        camNode.setLocalTranslation(new Vector3f(0, 2, -5));
        //Rotate the camNode to look at the target:
        camNode.lookAt(vehicleNode.getLocalTranslation(), Vector3f.UNIT_Y);
    }
}
