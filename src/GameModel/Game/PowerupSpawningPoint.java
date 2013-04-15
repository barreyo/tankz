
package GameModel.Game;

import com.jme3.math.Vector3f;

/**
 *
 * @author perthoresson
 */
public class PowerupSpawningPoint implements ISpawningPoints {
    
    private boolean inUse;
    
    private Vector3f position;

    public PowerupSpawningPoint (boolean isTaken){
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
}