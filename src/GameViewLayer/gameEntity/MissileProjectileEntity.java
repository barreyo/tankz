
package GameViewLayer.gameEntity;

import GameLogicLayer.entitycontrols.ControlFactory;
import GameLogicLayer.entitycontrols.TankProjectileControl;
import GameLogicLayer.entitycontrols.EControls;
import GameViewLayer.graphics.EGraphics;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;
import com.jme3.math.Vector3f;
import java.io.IOException;


/**
 * A missile projectile.
 *
 * @author Daniel
 */
public class MissileProjectileEntity extends AGameEntity implements Savable {
    
    private TankProjectileControl projectileControl;
    private RigidBodyControl physicsControl;
    
    private Vector3f direction;

    public MissileProjectileEntity() {
        super(EGraphics.SHARK);
        
        // Add this instance as user data to the spatial
        spatial.setUserData("entity", this);
    }
    
    /**
     * @inheritdoc
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }
    
    @Override
    void addPhysicsControl() {
        //TODO change mass to model mass
        physicsControl = new RigidBodyControl(getCollisionShape(), 
                0.001f);
        physicsControl.setCcdMotionThreshold(0.1f);
        physicsControl.setGravity(new Vector3f(0f, 0.01f, 0f));
        physicsControl.setKinematic(true);
        
        //physicsControl.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_02);
        //physicsControl.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_01);

        spatial.addControl(physicsControl);
        bulletAppState.getPhysicsSpace().add(physicsControl);
    }
    
    /**
     * Returns the vehicle control of this tank.
     * 
     * @return the vehicle control of this tank
     */
    public RigidBodyControl getPhysicsControl() {
        return physicsControl;
    }

    /**
     * @inheritdoc
     */
    @Override
    void addMaterial() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @inheritdoc
     */
    @Override
    void addControl() {
        this.projectileControl = (TankProjectileControl)ControlFactory.getControl(EControls.PROJECTILE_CONTROL);
        spatial.addControl(this.projectileControl);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void cleanup() {
        projectileControl.cleanup();
        //bulletAppState.getPhysicsSpace().remove(vehicle);
        spatial.removeControl(projectileControl);
        spatial.setName(null);
        projectileControl = null;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void finalise() {
        addControl();
        addPhysicsControl();
    }

    /**
     * Returns the direction this missile is facing.
     * 
     * @return The direction this missile is facing
     */
    public Vector3f getDirection() {
        return direction;
    }
    
    /**
     * Sets the direction of the missile.
     * 
     * @param direction sets the direcetion this missile is facing
     */
    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }

    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
