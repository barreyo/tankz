
package model;

import com.jme3.math.Vector3f;

/**
 * An IPowerups spawning point.
 * 
 * @author perthoresson
 */
public class SpawningPoint implements ISpawningPoint {
    private boolean isOccupied;
    private IWorldObject occupier;
    private Vector3f position;

    /**
     * Instantiates the object.
     * 
     * @param pos 
     */
    public SpawningPoint (Vector3f pos){
        this.position = pos.clone();
        this.isOccupied = false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }
    
    /**
     * {@inheritdoc} 
     */
    @Override
    public void setOccupied(boolean inUse){
        this.isOccupied = inUse;
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public Vector3f getPosition() {
        return position.clone();
    }
    
    /**
     * Returns a string representation of the object, based on position and inUse.
     * 
     * @return inUse and position in the format: PlayerSpawningPoint{inUse=xxxx,
     *  position=xxxf}
     */
    @Override
    public String toString() {
        return "PlayerSpawningPoint{" + "inUse=" + isOccupied + ", "
                + "position=" + position + '}';
    }
    
    /**
     * Returns hash code based on the position.
     * 
     * @return hash code based on the position.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.position != null ? this.position.hashCode() : 0);
        return hash;
    }
    
    /**
     * Equals method that compares the position of the PlayerSpawningPoint.
     *
     * @param obj the reference object with which to compare.
     * @return true if this PlayerSpawningPoint is the same as the obj argument
     * ; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SpawningPoint other = (SpawningPoint) obj;
        if (this.position != other.position && (this.position == null || !this.position.equals(other.position))) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOccupier(IWorldObject occupier) {
        this.occupier = occupier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IWorldObject getOccupier() {
        return occupier;
    }
}
