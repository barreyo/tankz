package GameLogicLayer.Vehicle;

import GameModelLayer.Vehicle.IVehicleModel;
import GameViewLayer.Vehicle.IVehicleSpatial;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.InputManager;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;

/**
 *
 *
 * @author Daniel
 */
public class TankController extends AVehicleController {
    private IVehicleModel vehicleModel;
    private VehicleControl vehicle;
    private PhysicsSpace physicsSpace;

    /**
     * Creates a tank controller, connected to specified vehicle.
     *
     * @param inputManager Used to manage input
     * @param tank The vehicle spatial representing the tank
     * @param physicsSpace The physicsSpace of the 3d room
     */
    public TankController(IVehicleModel vehicleModel, IVehicleSpatial tank, InputManager inputManager, PhysicsSpace physicsSpace) {
        super(inputManager);

        this.vehicleModel = vehicleModel;
        this.physicsSpace = physicsSpace;

        //create a compound shape and attach the BoxCollisionShape for the car body at 0,1,0
        //this shifts the effective center of mass of the BoxCollisionShape to 0,-1,0
        CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        BoxCollisionShape box = new BoxCollisionShape(new Vector3f(1.2f, 0.5f, 2.4f));
        compoundShape.addChildShape(box, new Vector3f(0, 1, 0));

        // Create the actual vehicle control
        Node vehicleNode = tank.getVehicleNode();
        vehicle = new VehicleControl(compoundShape, 400);
        vehicleNode.addControl(vehicle);
        vehicleNode.setShadowMode(RenderQueue.ShadowMode.Cast);

        float stiffness = 60.0f;//200=f1 car
        float compValue = .3f; //(should be lower than damp)
        float dampValue = .4f;
        vehicle.setSuspensionCompression(compValue * 2.0f * FastMath.sqrt(stiffness));
        vehicle.setSuspensionDamping(dampValue * 2.0f * FastMath.sqrt(stiffness));
        vehicle.setSuspensionStiffness(stiffness);
        vehicle.setMaxSuspensionForce(10000.0f);

        //Create four wheels and add them at their locations
        Vector3f wheelDirection = new Vector3f(0, -1, 0); // was 0, -1, 0
        Vector3f wheelAxle = new Vector3f(-1, 0, 0); // was -1, 0, 0
        float radius = 0.3f;
        float restLength = 0.3f;
        float yOff = 0.5f;
        float xOff = 1f;
        float zOff = 2f;

        vehicle.addWheel(null, new Vector3f(-xOff, yOff, zOff),
                wheelDirection, wheelAxle, restLength, radius, true);

        vehicle.addWheel(null, new Vector3f(xOff, yOff, zOff),
                wheelDirection, wheelAxle, restLength, radius, true);

        vehicle.addWheel(null, new Vector3f(-xOff, yOff, -zOff),
                wheelDirection, wheelAxle, restLength, radius, false);

        vehicle.addWheel(null, new Vector3f(xOff, yOff, -zOff),
                wheelDirection, wheelAxle, restLength, radius, false);


        getPhysicsSpace().add(vehicle);
    }

    /**
     * Returns physicsSpace.
     *
     * @return physicsSpace.
     */
    private PhysicsSpace getPhysicsSpace() {
        return physicsSpace;
    }

    /**
     * Defines what should happen when a button is pressed. Allows for
     * multiple-input.
     *
     * @param name The name of the action.
     * @param isPressed Boolean representing if the button is pressed already.
     * @param tpf Times per frame, how "fast" the program is running.
     */
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("Lefts")) {
            if (isPressed) {
                vehicleModel.incrementSteeringValue(1f);
            } else {
                vehicleModel.decrementSteeringValue(1f);
            }
            vehicle.steer(vehicleModel.getSteeringValue());
        } else if (name.equals("Rights")) {
            if (isPressed) {
                vehicleModel.decrementSteeringValue(1f);
            } else {
                vehicleModel.incrementSteeringValue(1f);
            }
            vehicle.steer(vehicleModel.getSteeringValue());
        } else if (name.equals("Ups")) {
            if (isPressed) {
                vehicleModel.incrementAccelerationValue(vehicleModel.getAccelerationForce());
            } else {
                vehicleModel.decrementAccelerationValue(vehicleModel.getAccelerationForce());
            }
            vehicle.accelerate(vehicleModel.getAccelerationValue());
        } else if (name.equals("Downs")) {
            if (isPressed) {
                vehicle.brake(vehicleModel.getBrakeForce());
                vehicleModel.decrementAccelerationValue(vehicleModel.getAccelerationForce());
            } else {
                vehicle.brake(0f);
                vehicleModel.incrementAccelerationValue(vehicleModel.getAccelerationForce());
            }
            vehicle.accelerate(vehicleModel.getAccelerationValue());
        } /*else if (name.equals("Space")) {
         if (isPressed) {
         vehicle.applyImpulse(jumpForce, Vector3f.ZERO);
         }
         }*/ else if (name.equals("Reset")) {
            if (isPressed) {
                System.out.println("Reset");
                vehicle.setPhysicsLocation(Vector3f.ZERO);
                vehicle.setPhysicsRotation(new Matrix3f());
                vehicle.setLinearVelocity(Vector3f.ZERO);
                vehicle.setAngularVelocity(Vector3f.ZERO);
                vehicle.resetSuspension();
            }
        }
    }
}