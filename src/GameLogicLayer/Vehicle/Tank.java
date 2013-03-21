/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Vehicle;

import GameLogicLayer.Vehicle.AVehicle;
import GameViewLayer.Vehicle.IVehicleSpatial;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.InputManager;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.shape.Cylinder;

/**
 *
 * @author Daniel
 */
public class Tank extends AVehicle {
    private int health;
    private Vector3f direction;
    private Vector3f position;
    
    private VehicleState vehicleState;
    
    private float accelerationForce;
    private float brakeForce;
    private float steeringValue;
    private float accelerationValue;
    
    private VehicleControl vehicle;
    private BulletAppState bulletAppState;
    private PhysicsSpace physicsSpace;
    
    
    
    public Tank(InputManager inputManager, IVehicleSpatial tank, PhysicsSpace physicsSpace) {
        super(inputManager);
        
        this.physicsSpace = physicsSpace;
 
        //create a compound shape and attach the BoxCollisionShape for the car body at 0,1,0
        //this shifts the effective center of mass of the BoxCollisionShape to 0,-1,0
        CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        BoxCollisionShape box = new BoxCollisionShape(new Vector3f(1.2f, 0.5f, 2.4f));
        compoundShape.addChildShape(box, new Vector3f(0, 1, 0));
        
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


    
    private PhysicsSpace getPhysicsSpace(){
        return physicsSpace;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public Vector3f getDirection() {
        return direction;
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public VehicleState getVehicleState() {
        return vehicleState;
    }

    @Override
    public float getAccelerationForce() {
        return accelerationForce;
    }

    @Override
    public float getBrakeForce() {
        return brakeForce;
    }

    @Override
    public float getSteeringValue() {
        return steeringValue;
    }

    @Override
    public float getAccelerationValue() {
        return accelerationValue;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }

    @Override
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    @Override
    public void setVehicleState(VehicleState state) {
        this.vehicleState = state;
    }

    @Override
    public void setAccelerationForce(float force) {
        this.accelerationForce = force;
    }

    @Override
    public void setBrakeForce(float force) {
        this.brakeForce = force;
    }

    @Override
    public void setSteeringValue(float value) {
        this.steeringValue = value;
    }

    @Override
    public void setAccelerationValue(float value) {
        this.accelerationValue = value;
    }

    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("Lefts")) {
            if (isPressed) {
                steeringValue += 1f;
            } else {
                steeringValue += -1f;
            }
            vehicle.steer(steeringValue);
        } else if (name.equals("Rights")) {
            if (isPressed) {
                steeringValue += -1f;
            } else {
                steeringValue += 1f;
            }
            vehicle.steer(steeringValue);
        } else if (name.equals("Ups")) {
            if (isPressed) {
                accelerationValue += accelerationForce;
            } else {
                accelerationValue -= accelerationForce;
            }
            vehicle.accelerate(accelerationValue);
        } else if (name.equals("Downs")) {
            if (isPressed) {
                vehicle.brake(brakeForce);
                accelerationValue -= accelerationForce;
            } else {
                vehicle.brake(0f);
                accelerationValue += accelerationForce;
            }
            vehicle.accelerate(accelerationValue);
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