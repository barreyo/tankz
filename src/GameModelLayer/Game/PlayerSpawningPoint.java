
package GameModelLayer.Game;

/**
 *
 * 
 * @author perthoresson
 */
public class PlayerSpawningPoint implements ISpawningPoints {
    
    private boolean isTaken;
    
    public PlayerSpawningPoint (boolean isTaken){
        this.isTaken = isTaken;
    }
    
    public boolean isInUse() {
        return isTaken;
    }
    
}
