
package GameModelLayer.Game;

/**
 *
 * 
 * @author perthoresson
 */
public class PlayerSpawningPoint implements ISpawningPoints {
    
    private boolean inUse;
    
    public PlayerSpawningPoint (boolean inUse){
        this.inUse = inUse;
    }
    
    public boolean isInUse() {
        return inUse;
    }
    
    public void setInUse(boolean inUse){
        this.inUse = inUse;
    }
    
}
