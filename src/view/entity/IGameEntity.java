package view.entity;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.beans.PropertyChangeListener;
import utilities.IObservable;

/**
 * Interface containing all the methods a game entity has.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public interface IGameEntity extends PropertyChangeListener, IObservable {
    
    /**
     * Returns a collisionshape of this game entity.
     *
     * @return a collisionshape of this game entity
     */
    public CollisionShape getCollisionShape();

    /**
     * Adds an appropriate control to this view.
     * @param control 
     */
    public void addControl(Control control);

    /**
     * Releases all occupied resources of this instance.
     */
    public void cleanup();

    /**
     * @return The spatial of this game entity.
     */
    public Spatial getSpatial();

    /**
     * @Removes a control of this view.
     */
    public void removeControl(Control control);
    
    /**
     * Shows the view in the world.
     */
    public void showInWorld();
    
    /**
     * Hides the view from the world.
     */
    public void hideFromWorld();
}
