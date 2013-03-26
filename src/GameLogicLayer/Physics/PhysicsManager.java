/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Physics;

import GameLogicLayer.util.Manager;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.bullet.control.RigidBodyControl;
import java.util.EnumMap;

/**
 *
 * @author Per
 */
public class PhysicsManager implements Manager {
    
    private EnumMap<ETanksCollisionShape, CollisionShape> collisionShapeMap = new EnumMap<ETanksCollisionShape, CollisionShape>(ETanksCollisionShape.class);

    public void load(int level) {
        if (level == 1) {
            loadCollisionShapes(new ETanksCollisionShape[]{ETanksCollisionShape.VEHICLE});
        }
    }

    private void loadCollisionShapes(ETanksCollisionShape[] tanksCollisionShapes) {
        for (ETanksCollisionShape tanksCollisionShape : tanksCollisionShapes) {
            collisionShapeMap.put(tanksCollisionShape, tanksCollisionShape.createCollisionShape());
        }
    }

    public PhysicsControl getPhysicsControl(ETanksCollisionShape tanksCollisionShape) {
        RigidBodyControl rigidBodyControl = new RigidBodyControl(collisionShapeMap.get(tanksCollisionShape), 1);
        rigidBodyControl.setKinematic(true);
        return rigidBodyControl;
    }

    public CollisionShape getCollisionShape(ETanksCollisionShape tanksCollisionShape) {
        return collisionShapeMap.get(tanksCollisionShape);
    }

    public void cleanup() {
        collisionShapeMap.clear();
    }
}
