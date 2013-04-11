
package GameView.gameEntity;

import GameControllers.entitycontrols.ControlFactory;
import GameControllers.entitycontrols.EControls;
import GameControllers.entitycontrols.TanksVehicleControl;
import GameModel.gameEntity.Vehicle.TankModel;
import GameUtilities.TankAppAdapter;
import GameView.GUI.FloatingNameControl;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.export.Savable;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import java.io.IOException;

/**
 * The visual game entity class of a tank.
 * 
 * @author Daniel
 */
public class Tank extends AGameEntity implements Savable {

    // Controls attached to this tank.
    private VehicleControl vehicle;
    private TanksVehicleControl tanksVehicleControl;
    
    private Vector3f direction;

    /**
     * Creates a tank game entity.
     */
    public Tank() {
        super(EGraphics.TANK);
        // Save this as user data for the spatial -> ie if you can access the spatial
        // you can access this.
        spatial.setUserData("entity", this);
        spatial.addControl(new FloatingNameControl(spatial,
                TankAppAdapter.INSTANCE.getAssetManager()));
    }

    /**
     *  @inheritdoc
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }

    /**
     *  @inheritdoc
     */
    @Override
    void addPhysicsControl() {
        
        CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        compoundShape.addChildShape(getCollisionShape(), new Vector3f(0, 1, 0));

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
        bulletAppState.getPhysicsSpace().add(vehicle);
    }
    

    /**
     * Returns the vehicle control of this tank.
     * 
     * @return the vehicle control of this tank
     */
    public VehicleControl getVehicleControl() {
        return vehicle;
    }
    
    /**
     * Returns the tank control of this tank.
     * 
     * @return The tank control of this tank
     */
    public TanksVehicleControl getTanksVehicleControl() {
        return tanksVehicleControl;
    }
    
    /**
     *  @inheritdoc
     */
    @Override
    public void addMaterial() {
    }

    /**
     *  @inheritdoc
     */
    @Override
    void addControl() {
        tanksVehicleControl = (TanksVehicleControl)ControlFactory.getControl(EControls.VEHICLE_CONTROL);
        spatial.addControl(tanksVehicleControl);
    }

    /**
     *  @inheritdoc
     */
    @Override
    public void cleanup() {
        spatial.getControl(EControls.VEHICLE_CONTROL.getControl()).cleanup();
        bulletAppState.getPhysicsSpace().remove(vehicle);
        spatial.removeControl(vehicle);
        spatial.setUserData("entity", null);
        vehicle = null;
    }

    /**
     *  @inheritdoc
     */
    @Override
    public void finalise() {
        addPhysicsControl();
        addControl();
    }

    /**
     *
     * @param e
     * @throws IOException
     */
    @Override
    public void write(JmeExporter e) throws IOException {
        OutputCapsule capsule = e.getCapsule(this);
        capsule.write(this.vehicle, "vehicle", null);
        capsule.write(this.tanksVehicleControl, "tanksVehicle", null);
    }

    /**
     *
     * @param e
     * @throws IOException
     */
    @Override
    public void read(JmeImporter e) throws IOException {
        InputCapsule capsule = e.getCapsule(this);
        vehicle = (VehicleControl) capsule.readSavable("vehicle", null);
        this.tanksVehicleControl = (TanksVehicleControl) capsule.readSavable("tanksVehicle", null);
    }
}
