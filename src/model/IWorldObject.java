package model;

import com.jme3.export.Savable;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import utilities.IObservable;

/**
 * A interface for a world object that can be hidden and shown.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public interface IWorldObject extends IObservable, Savable{
    /**
     * The world object enters a visible state.
     */
    public void showInWorld();
    
    /**
     * The world object enters a hidden state.
     */
    public void hideFromWorld();
    
    /**
     * Checks if the world object is visible.
     * 
     * @return a boolean indicating if the world object is visible
     */
    public boolean isShownInWorld();
    
    /**
     * Allows the world object to update its state.
     * 
     * @param tpf time per frame.
     */
    public void update(float tpf);
    
    /**
     *  Indicates to the world object that it should release resources.
     */
    public void cleanup();
    
    /**
     * Returns the mass of the world object.
     * 
     * @return the mass of the world object in KG
     */
    public float getMass();

    /**
     * Returns the position of the world object.
     * 
     * @return the position of the world object
     */
    public Vector3f getPosition();
    
    /**
     * Sets the position of the world object.
     * 
     * @param pos the position to be set
     */
    public void setPosition(Vector3f pos);
    
    /**
     * Returns the rotation.
     * 
     * @return rotation
     */
    public Quaternion getRotation();
}
