package GameView.gameEntity;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;

/**
 *
 * @author Daniel
 */
public interface IGameEntity {
    
    /**
     * Returns a collisionshape of this game entity.
     *
     * @return a collisionshape of this game entity
     */
    CollisionShape getCollisionShape();

    /**
     * Adds an material to the spatial.
     */
    void setMaterial(Material mat);

    /**
     * Adds an appropriate control to the spatial.
     */
    void addControl(Control control);

    /**
     * Releases all occupied resources of this instance.
     */
    void cleanup();

    /**
     * Returns the spatial of this game entity.
     * 
     * @return The spatial of this game entity.
     */
    Spatial getSpatial();
    
    /**
     * Help metohd used to get the boundingbox of the spatial.
     */
    Vector3f getExtents();

    /**
     * 
     * @param control 
     */
    void removeControl(Control control);
}
