package GameView.physics;

import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.math.Vector3f;

/**
 * Enum of the collision shapes used in Tanks.
 * 
 * @author Daniel
 */
public enum ECollisionShapes {
    /**
     *  A vehicle collision shape.
     */
    VEHICLE,
    /**
     *  A missile projectile collision shape.
     */
    MISSILE_PROJECTILE;

    /**
     * Returns a new collision shape based on enum type.
     * 
     * @return A new collision shape based on enum type
     */
    public CollisionShape createCollisionShape() {
        CollisionShape collisionShape;
        switch(this) {
            case VEHICLE:
                CompoundCollisionShape compound = new CompoundCollisionShape();
                BoxCollisionShape boxCollisionShape = new BoxCollisionShape(new Vector3f(1.2f, 0.9f, 1.4f));
                compound.addChildShape(boxCollisionShape, new Vector3f(0f, 1.4f, 0f));
                collisionShape = compound;
                break;
            case MISSILE_PROJECTILE:
                collisionShape = new BoxCollisionShape(new Vector3f(0.5f, 0.5f, 0.5f));
                break;
            default:
                collisionShape = new BoxCollisionShape(new Vector3f(1, 1, 1));
                break;
        }
        return collisionShape;
    }
}
