
package GameModel;

import com.jme3.math.Vector3f;

/**
 * An IPlayers spawning point.
 * 
 * @author perthoresson
 */
public class PlayerSpawningPoint implements ISpawningPoints {
    
    private boolean inUse;

    private final Vector3f position;
    
    /**
     * Instantiates the PlayerSpawningPoint.
     * 
     * @param inUse if the spawning point is in use or not.
     */
    public PlayerSpawningPoint (Vector3f pos){
        this.position = pos.clone();
        this.inUse = false;
    }
    
    /**
     * {@inheritdoc} 
     */
    @Override
    public boolean isInUse() {
        return inUse;
    }
    
    /**
     * {@inheritdoc} 
     */
    @Override
    public void setInUse(boolean inUse){
        this.inUse = inUse;
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public Vector3f getPosition() {
        return position.clone();
    }

    /**
     * Returns a string representation of the object, based on inUse and position.
     * @return inUse and position in the format: PlayerSpawningPoint{inUse=xxxx,
     *  position=xxxf}
     */
    @Override
    public String toString() {
        return "PlayerSpawningPoint{" + "inUse=" + inUse + ", "
                + "position=" + position + '}';
    }

    /**
     * Returns hash code based on the position.
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
        final PlayerSpawningPoint other = (PlayerSpawningPoint) obj;
        if (this.position != other.position && (this.position == null || !this.position.equals(other.position))) {
            return false;
        }
        return true;
    }    
}
