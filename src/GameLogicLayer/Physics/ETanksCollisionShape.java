/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Physics;

import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.math.Vector3f;

/**
 *
 * @author Daniel
 */
public enum ETanksCollisionShape {
    /**
     *
     */
    VEHICLE,
    
    SHARK_PROJECTILE;

    /**
     *
     * @return
     */
    public CollisionShape createCollisionShape() {
        CollisionShape collisionShape;
        switch(this) {
            case VEHICLE:
                collisionShape = new BoxCollisionShape(new Vector3f(0.6f, 0.5f, 1.5f));
                break;
            case SHARK_PROJECTILE:
                collisionShape = new BoxCollisionShape(new Vector3f(0.5f, 0.5f, 0.5f));
                break;
            default:
                collisionShape = new BoxCollisionShape(new Vector3f(1, 1, 1));
                break;
        }
        return collisionShape;
    }
}
