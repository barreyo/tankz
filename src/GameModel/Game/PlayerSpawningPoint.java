
package GameModel.Game;

import com.jme3.math.Vector3f;

/**
 *
 * 
 * @author perthoresson
 */
public class PlayerSpawningPoint implements ISpawningPoints {
    
    private boolean inUse;
    
    private Vector3f position;
    
    public PlayerSpawningPoint (boolean inUse){
        this.inUse = inUse;
    }
    
    public boolean isInUse() {
        return inUse;
    }
    
    public void setInUse(boolean inUse){
        this.inUse = inUse;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * 
     * @return inUse and position in the format: PlayerSpawningPoint{inUse=xxxx,
     *  position=xxxf}
     */
    @Override
    public String toString() {
        return "PlayerSpawningPoint{" + "inUse=" + inUse + ", "
                + "position=" + position + '}';
    }

    /**
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
        final PlayerSpawningPoint other = (PlayerSpawningPoint) obj;
        if (this.position != other.position && (this.position == null || !this.position.equals(other.position))) {
            return false;
        }
        return true;
    }
    
    
    
    
}
