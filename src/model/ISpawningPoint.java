
package model;

import com.jme3.math.Vector3f;

/**
 * Interface for modelling all the spawning points in the game.
 * 
 * @author perthoresson
 */
public interface ISpawningPoint {
    
    /**
     * Returns a boolean indicating if the spawning point is occupied.
     * 
     * @return a boolean indicating if the spawning point is occupied.
     */
    public boolean isOccupied();
    
    /**
     * Sets if spawning point is occupied.
     * 
     * @param isOccupied a boolean indicating if the powerup now is occupied.
     */
    public void setOccupied(boolean isOccupied);
    
    /**
     * Returns the position of the spawningpoint.
     * 
     * @return the position of the spawningpoint
     */
    public Vector3f getPosition();
    
    /**
     * Sets the world object occupier of the spawningpoint.
     * 
     * @param occupier the occupant of this spawningpoint
     */
    public void setOccupier(IWorldObject occupier);
    
    /**
     * Returns the world object occupier of the spawningpoint.
     * 
     * @return the occupier of this spawningpoint
     */
    public IWorldObject getOccupier();
}
