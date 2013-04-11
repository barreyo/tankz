
package GameModel.Game;

/**
 *
 * @author perthoresson
 */
public class PowerupSpawningPoint implements ISpawningPoints {
    
    private boolean inUse;

    public PowerupSpawningPoint (boolean isTaken){
        this.inUse = inUse;
    }
    
    public boolean isInUse() {
        return inUse;
    }
    
    public void setInUse(boolean inUse){
        this.inUse = inUse;
    }
}