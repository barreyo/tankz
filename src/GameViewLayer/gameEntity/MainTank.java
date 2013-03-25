/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.gameEntity;

import GameLogicLayer.Graphics.Graphics;
import GameLogicLayer.controls.TanksControl;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
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
public class MainTank extends GameEntity {

    private VehicleControl vehicle;

    public MainTank() {
        super(Graphics.TANK);
        spatial.setName("tank");
        // ?
        spatial = (Node)spatial;
        // For saving?
        //spatial.setUserData("entity", this);
    }

    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(new Vector3f(1.2f, 0.5f, 2.4f));
    }

    @Override
    public void addPhysicsControl() {
        
        CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        compoundShape.addChildShape(getCollisionShape(), new Vector3f(0, 1, 0));

        // Create the actual vehicle control
        vehicle = new VehicleControl(compoundShape, 400);
        spatial.setShadowMode(RenderQueue.ShadowMode.Cast);

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
        
        
        //vehicle.setPhysicsLocation(new Vector3f(0, 10, 0));
        vehicle.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_03);
        vehicle.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_01 | PhysicsCollisionObject.COLLISION_GROUP_02 | PhysicsCollisionObject.COLLISION_GROUP_04);
        bulletAppState.getPhysicsSpace().add(vehicle);

        spatial.addControl(vehicle);
    }

    public VehicleControl getCharacterControl() {
        return vehicle;
    }

    @Override
    public void addMaterial() {
    }

    @Override
    public void addControl() {
        spatial.addControl(controlManager.getControl(TanksControl.VEHICLE_CONTROL));
    }

    @Override
    public void cleanup() {
        spatial.getControl(TanksControl.VEHICLE_CONTROL.getControl()).cleanup();
        bulletAppState.getPhysicsSpace().remove(vehicle);
        spatial.removeControl(vehicle);
        spatial.setUserData("entity", null);
        vehicle = null;
    }

    @Override
    public void finalise() {
        addPhysicsControl();
        addControl();
    }
}
