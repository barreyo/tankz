package GameLogicLayer.Vehicle;

import GameLogicLayer.Game.GameManager;
import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.Weapon.AWeaponManager;
import GameLogicLayer.Weapon.TankGunManager;
import GameModelLayer.gameEntity.Projectile.IProjectile;
import GameModelLayer.gameEntity.Projectile.ProjectileModel;
import GameModelLayer.gameEntity.Vehicle.IArmedVehicle;
import GameModelLayer.gameEntity.Weapon.IProjectileWeapon;
import GameModelLayer.gameEntity.Weapon.TankGunModel;
import GameViewLayer.gameEntity.Projectile.IProjectileSpatial;
import GameViewLayer.gameEntity.Projectile.TankProjectileSpatial;
import GameViewLayer.gameEntity.Vehicle.IVehicleSpatial;
import GameViewLayer.gameEntity.Weapon.GunSpatial;
import GameViewLayer.gameEntity.Weapon.IWeaponSpatial;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 *
 *
 * @author Daniel
 */
public class TankManager extends AVehicleManager {
    private IArmedVehicle vehicleModel;
    private IVehicleSpatial vehicleSpatial;
    private VehicleControl vehicle;
    private Node vehicleNode;
    
    private AWeaponManager weaponManager;
    
    private GameManager app;

    /**
     * Creates a tank controller, connected to specified vehicle.
     *
     * @param vehicleModel 
     * @param tank The vehicle spatial representing the tank
     * @param app 
     */
    public TankManager(IArmedVehicle vehicleModel, IVehicleSpatial tank, GameManager app) {
        super(app.getInputManager());
        
        this.app = app;

        this.vehicleModel = vehicleModel;
        this.vehicleSpatial = tank;

        // Maybe move to Tank spatial class?
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
        // Create projectile for weapon
        IProjectile bulletModel = new ProjectileModel(10, 0.001f);
        IProjectileSpatial bulletSpatial = new TankProjectileSpatial(app.getAssetManager(), 0.4f);
        
        // Uses a temporary box Spatial as weapon that can fire projectiles
        // Will likely be replaced
        IProjectileWeapon weaponModel = new TankGunModel(bulletModel);
        Spatial spat = new Geometry("Box", new Box(0,0,0));
        spat.setMaterial(new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md"));
        spat.setLocalTranslation(0, 1, 2);
        IWeaponSpatial weaponSpatial = new GunSpatial(spat, 1f);
        
        // Attaches weapon to vehicle
        vehicleModel.setWeaponModel(weaponModel);
        vehicleNode.attachChild(weaponSpatial.getWeaponSpatial());
        
        // Creates a controller for the weapon
        weaponManager = new TankGunManager(weaponSpatial, weaponModel,
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

    /**
     *
     * @param tpf
     */
    @Override
    public void simpleUpdate(float tpf) {
        Vector3f direction = vehicle.getForwardVector(Vector3f.ZERO);
        vehicleSpatial.setPosition(vehicle.getPhysicsLocation());
        vehicleSpatial.setDirection(direction);
        weaponManager.updateDirectionOfWeapon(direction);
    }
}