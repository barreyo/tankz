package GameModel;

import GameUtilities.IObservable;
import com.jme3.export.Savable;
import com.jme3.math.Vector3f;

/**
 * A interface for a world object that can be hidden and shown.
 * 
 * @author Daniel
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
     * @param tpf
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
}
