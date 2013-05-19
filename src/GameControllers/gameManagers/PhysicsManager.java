package GameControllers.gameManagers;

import GameView.physics.ECollisionShapes;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.bullet.control.RigidBodyControl;
import java.util.EnumMap;

/**
 * Manager managing the physics in the game, loading and processing.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum PhysicsManager {
    /**
     * Instance to this singleton.
     */
    INSTANCE;
    
    private EnumMap<ECollisionShapes, CollisionShape> collisionShapeMap = new EnumMap<ECollisionShapes, CollisionShape>(ECollisionShapes.class);
    
    /**
     * Load all collision shapes.
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
     * Get a specific physics control.
     * 
     * @param tanksCollisionShape shape.
     * @return created physics control.
     */
    public PhysicsControl getPhysicsControl(ECollisionShapes tanksCollisionShape) {
        RigidBodyControl rigidBodyControl = new RigidBodyControl(collisionShapeMap.get(tanksCollisionShape), 1);
        rigidBodyControl.setKinematic(true);
        return rigidBodyControl;
    }

    /**
     * Get collision shape for an object.
     * 
     * @param tanksCollisionShape shape.
     * @return collision shape of object.
     */
    public CollisionShape getCollisionShape(ECollisionShapes tanksCollisionShape) {
        return collisionShapeMap.get(tanksCollisionShape);
    }

    /**
     * Remove all physics object loaded in the manager.
     */
    public void cleanup() {
        collisionShapeMap.clear();
    }
}
