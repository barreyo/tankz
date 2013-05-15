package GameControllers.Managers;

import GameView.physics.ECollisionShapes;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.bullet.control.RigidBodyControl;
import java.util.EnumMap;

/**
 *
 * @author Per
 */
public enum PhysicsManager {
    /**
     *
     */
    INSTANCE;
    
    private EnumMap<ECollisionShapes, CollisionShape> collisionShapeMap = new EnumMap<ECollisionShapes, CollisionShape>(ECollisionShapes.class);
    
    /**
     *
     * @param level
     */
    public void load() {
            loadCollisionShapes(new ECollisionShapes[]{ECollisionShapes.VEHICLE,
                ECollisionShapes.MISSILE_PROJECTILE, ECollisionShapes.NUKE_PROJECTILE});
    }

    private void loadCollisionShapes(ECollisionShapes[] tanksCollisionShapes) {
        for (ECollisionShapes tanksCollisionShape : tanksCollisionShapes) {
            collisionShapeMap.put(tanksCollisionShape, tanksCollisionShape.createCollisionShape());
        }
    }

    /**
     *
     * @param tanksCollisionShape
     * @return
     */
    public PhysicsControl getPhysicsControl(ECollisionShapes tanksCollisionShape) {
        RigidBodyControl rigidBodyControl = new RigidBodyControl(collisionShapeMap.get(tanksCollisionShape), 1);
        rigidBodyControl.setKinematic(true);
        return rigidBodyControl;
    }

    /**
     *
     * @param tanksCollisionShape
     * @return
     */
    public CollisionShape getCollisionShape(ECollisionShapes tanksCollisionShape) {
        return collisionShapeMap.get(tanksCollisionShape);
    }

    /**
     *
     */
    public void cleanup() {
        collisionShapeMap.clear();
    }
}
