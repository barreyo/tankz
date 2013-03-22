package GameLogicLayer.Vehicle;

import GameLogicLayer.Game.GameController;
import GameLogicLayer.Projectile.BulletControl;
import GameLogicLayer.Weapon.AWeaponController;
import GameLogicLayer.Weapon.GunController;
import GameModelLayer.Projectile.BulletModel;
import GameModelLayer.Projectile.IProjectileModel;
import GameModelLayer.Vehicle.IVehicleModel;
import GameModelLayer.Weapon.GunModel;
import GameModelLayer.Weapon.IProjectileWeaponModel;
import GameModelLayer.Weapon.IWeaponModel;
import GameViewLayer.Projectile.BulletSpatial;
import GameViewLayer.Projectile.IProjectileSpatial;
import GameViewLayer.Vehicle.IVehicleSpatial;
import GameViewLayer.Weapon.GunSpatial;
import GameViewLayer.Weapon.IWeaponSpatial;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;

/**
 *
 *
 * @author Daniel
 */
public class TankController extends AVehicleController {
    private IVehicleModel vehicleModel;
    private IVehicleSpatial vehicleSpatial;
    private VehicleControl vehicle;
    
    private GameController app;
    
    private Node vehicleNode;
    
    

    /**
     * Creates a tank controller, connected to specified vehicle.
     *
     * @param inputManager Used to manage input
     * @param tank The vehicle spatial representing the tank
     * @param physicsSpace The physicsSpace of the 3d room
     */
    public TankController(IVehicleModel vehicleModel, IVehicleSpatial tank, 
                          GameController app) {
        super(app.getInputManager());
        
        this.app = app;

        this.vehicleModel = vehicleModel;
        this.vehicleSpatial = tank;

        //create a compound shape and attach the BoxCollisionShape for the car body at 0,1,0
        //this shifts the effective center of mass of the BoxCollisionShape to 0,-1,0
        CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        BoxCollisionShape box = new BoxCollisionShape(new Vector3f(1.2f, 0.5f, 2.4f));
        compoundShape.addChildShape(box, new Vector3f(0, 1, 0));

        // Create the actual vehicle control
        vehicleNode = tank.getVehicleNode();
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
        
        // Add vehicle to phsyicsspace
        PhysicsSpace.getPhysicsSpace().add(vehicle);
        
        createWeapon();
    }
    
    private void createWeapon() {
        // 
        IProjectileModel bulletModel = new BulletModel(10);
        IProjectileSpatial bulletSpatial = new BulletSpatial(app.getAssetManager());
        
        IProjectileWeaponModel weaponModel = new GunModel(bulletModel);
        
        Spatial spat = new Geometry("Box", new Box(0,0,0));
        spat.setMaterial(new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md"));
        IWeaponSpatial weaponSpatial = new GunSpatial(spat, 1f);
        
        vehicleModel.setWeaponModel(weaponModel);
        vehicleNode.attachChild(weaponSpatial.getWeaponSpatial());
        AWeaponController weaponController = new GunController(weaponSpatial, weaponModel,
            bulletSpatial, bulletModel, app);
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
        } else if (name.equals("Reset")) {
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

    @Override
    public void simpleUpdate(float tpf) {
        vehicleSpatial.setPosition(vehicle.getPhysicsLocation());
        vehicleSpatial.setDirection(vehicle.getForwardVector(Vector3f.ZERO));
    }
}