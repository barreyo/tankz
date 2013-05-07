package GameView.gameEntity;

import GameUtilities.IObservable;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Daniel
 */
public interface IGameEntity extends PropertyChangeListener, IObservable {
    
    /**
     * Returns a collisionshape of this game entity.
     *
     * @return a collisionshape of this game entity
     */
    CollisionShape getCollisionShape();

    /**
     * Adds an material to the spatial.
     * @param mat 
     */
    void setMaterial(Material mat);

    /**
     * Adds an appropriate control to the spatial.
     * @param control 
     */
    void addControl(Control control);

    /**
     * Releases all occupied resources of this instance.
     */
    void cleanup();

    /**
     * TODO the goal is to remove this method, we dont want to share the spatial
     * Returns the spatial of this game entity.
     * 
     * @return The spatial of this game entity.
     */
    Spatial getSpatial();
    
    /**
     * Help metohd used to get the boundingbox of the spatial.
     * @return 
     */
    Vector3f getExtents();

    /**
     * 
     * @param control 
     */
    void removeControl(Control control);
}
