/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.gameEntity;

import GameViewLayer.graphics.EGraphics;
import GameLogicLayer.Physics.ETanksCollisionShape;
import GameLogicLayer.controls.TanksControl;
import GameLogicLayer.controls.TanksVehicleControl;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.export.Savable;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import java.io.IOException;

/**
 * 
 * 
 * @author Daniel
 */
public class MainTank extends GameEntity implements Savable {

    private VehicleControl vehicle;
    private TanksVehicleControl tanksVehicleControl;

    /**
     *
     */
    public MainTank() {
        super(EGraphics.TANK);
        // Save this as user data for the spatial -> ie if you can access the spatial
        // you can access this.
        spatial.setUserData("entity", this);
    }

    /**
     *
     * @return
     */
    @Override
    public CollisionShape getCollisionShape() {
        return ETanksCollisionShape.VEHICLE.createCollisionShape();
    }

    /**
     *
     */
    @Override
    public void addPhysicsControl() {
        
        CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        compoundShape.addChildShape(getCollisionShape(), new Vector3f(0, 1, 0));

        // Create the actual vehicle control
        vehicle = new VehicleControl(compoundShape, 400);
        spatial.addControl(vehicle);
        spatial.setShadowMode(RenderQueue.ShadowMode.Cast);

        float stiffness = 60.0f;//200=f1 car
        float compValue = 0.2f; //(should be lower than damp)
        float dampValue = 0.201f;
        vehicle.setSuspensionCompression(compValue * 2.0f * FastMath.sqrt(stiffness));
        vehicle.setSuspensionDamping(dampValue * 2.0f * FastMath.sqrt(stiffness));
        vehicle.setSuspensionStiffness(stiffness);
        vehicle.setMaxSuspensionForce(999000.0f);

        //Create four wheels and add them at their locations
        Vector3f wheelDirection = new Vector3f(0, -1, 0); // was 0, -1, 0
        Vector3f wheelAxle = new Vector3f(-1, 0, 0); // was -1, 0, 0
        float radius = 0.1f;
        float restLength = 0.2f;
        float yOff = 0.3f;
        float xOff = 0.5f;
        float zOff = 1.5f;

        vehicle.addWheel(null, new Vector3f(-xOff, yOff, zOff),
                wheelDirection, wheelAxle, restLength, radius, true);

        vehicle.addWheel(null, new Vector3f(xOff, yOff, zOff),
                wheelDirection, wheelAxle, restLength, radius, true);

        vehicle.addWheel(null, new Vector3f(-xOff, yOff, -zOff),
                wheelDirection, wheelAxle, restLength, radius, false);

        vehicle.addWheel(null, new Vector3f(xOff, yOff, -zOff),
                wheelDirection, wheelAxle, restLength, radius, false);
        
     
        //vehicle.setPhysicsLocation(new Vector3f(0, 10, 0));
        vehicle.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_03);
        vehicle.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_01 | PhysicsCollisionObject.COLLISION_GROUP_02 | PhysicsCollisionObject.COLLISION_GROUP_04);
        bulletAppState.getPhysicsSpace().add(vehicle);
    }
    

    /**
     *
     * @return
     */
    public VehicleControl getVehicleControl() {
        return vehicle;
    }
    
    public TanksVehicleControl getTanksVehicleControl() {
        return tanksVehicleControl;
    }

    /**
     *
     */
    @Override
    public void addMaterial() {
    }

    /**
     *
     */
    @Override
    public void addControl() {
        tanksVehicleControl = (TanksVehicleControl)controlManager.getControl(TanksControl.VEHICLE_CONTROL);
        spatial.addControl(tanksVehicleControl);
    }

    /**
     *
     */
    @Override
    public void cleanup() {
        spatial.getControl(TanksControl.VEHICLE_CONTROL.getControl()).cleanup();
        bulletAppState.getPhysicsSpace().remove(vehicle);
        spatial.removeControl(vehicle);
        spatial.setUserData("entity", null);
        vehicle = null;
    }

    /**
     *
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
    }
}
