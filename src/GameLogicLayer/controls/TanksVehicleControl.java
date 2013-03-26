
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
 *
 * @author Daniel
 */
public class TanksVehicleControl extends BaseControl implements ActionListener {
    private Vector3f walkDirection = new Vector3f();
    private boolean left = false, right = false, up = false, down = false;
    private VehicleControl vehicle;
    private Camera cam = app.getCamera();
    private InputManager inputManager = app.getInputManager();
    private final String LEFT_MOVE = "LeftMove";
    private final String RIGHT_MOVE = "RightMove";
    private final String FORWARD_MOVE = "UpMove";
    private final String BACKWARD_MOVE = "BackMove";
    private final String RESET = "Reset";
    
    private IArmedVehicle vehicleModel;

    public TanksVehicleControl() {
        vehicleModel = new TankModel();
        vehicleModel.setAccelerationForce(4000.0f);
        vehicleModel.setBrakeForce(100.0f);
        addDesktopInputs();
    }

    @Override
    protected void controlUpdate(float tpf) {
        if (vehicle == null) {
            return;
        }
    }

    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);

        if (spatial != null) {
            MainTank tank = (MainTank) spatial.getUserData("entity");
            vehicle = tank.getVehicleControl();
            setUpCam(tank.getVehicleNode());
        }
    }

    public void cleanUp() {
        spatial.removeControl(this);
        removeDesktopInput();

        //GameState.setMoving(false);
    }

    public void onAction(String binding, boolean isPressed, float tpf) {

        if (binding.equals(LEFT_MOVE)) {
            left = isPressed;
            if (isPressed) {
                vehicleModel.incrementSteeringValue(1f);
            } else {
                vehicleModel.decrementSteeringValue(1f);
            }
            vehicle.steer(vehicleModel.getSteeringValue());
        } else if (binding.equals(RIGHT_MOVE)) {
            right = isPressed;
            if (isPressed) {
                vehicleModel.decrementSteeringValue(1f);
            } else {
                vehicleModel.incrementSteeringValue(1f);
            }
            vehicle.steer(vehicleModel.getSteeringValue());
        } else if (binding.equals(FORWARD_MOVE)) {
            up = isPressed;
            if (isPressed) {
                vehicleModel.incrementAccelerationValue(vehicleModel.getAccelerationForce());
            } else {
                vehicleModel.decrementAccelerationValue(vehicleModel.getAccelerationForce());
            }
            vehicle.accelerate(vehicleModel.getAccelerationValue());
            System.out.println(vehicleModel.getAccelerationValue());
        } else if (binding.equals(BACKWARD_MOVE)) {
            down = isPressed;
            if (isPressed) {
                vehicle.brake(vehicleModel.getBrakeForce());
                vehicleModel.decrementAccelerationValue(vehicleModel.getAccelerationForce());
            } else {
                vehicle.brake(0f);
                vehicleModel.incrementAccelerationValue(vehicleModel.getAccelerationForce());
            }
            vehicle.accelerate(vehicleModel.getAccelerationValue());
        } else if (binding.equals(RESET)) {
            if (isPressed) {
                System.out.println("Reset");
                vehicle.setPhysicsLocation(Vector3f.ZERO);
                vehicle.setPhysicsRotation(new Matrix3f());
                vehicle.setLinearVelocity(Vector3f.ZERO);
                vehicle.setAngularVelocity(Vector3f.ZERO);
                vehicle.resetSuspension();
            }
        }

        boolean isMoving = left || right || up || down;
        //GameState.setMoving(isMoving);
    }

    private void addDesktopInputs() {
        inputManager.addMapping(LEFT_MOVE, new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping(RIGHT_MOVE, new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping(FORWARD_MOVE, new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping(BACKWARD_MOVE, new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping(RESET, new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addListener(this, LEFT_MOVE, RIGHT_MOVE, FORWARD_MOVE, BACKWARD_MOVE, RESET);
    }

    private void removeDesktopInput() {
        inputManager.deleteMapping(LEFT_MOVE);
        inputManager.deleteMapping(RIGHT_MOVE);
        inputManager.deleteMapping(FORWARD_MOVE);
        inputManager.deleteMapping(BACKWARD_MOVE);
        inputManager.deleteMapping(RESET);
        inputManager.removeListener(this);
    }

    private void setUpCam(Node vehicleNode) {
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
