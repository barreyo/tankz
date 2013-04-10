
package GameModelLayer.Game;

/**
 *
 * @author perthoresson
 */
public class PowerupSpawningPoint implements ISpawningPoints {
    
    private boolean isTaken;

    public PowerupSpawningPoint (boolean isTaken){
        this.isTaken = isTaken;
    }
    
    public boolean isInUse() {
        return isTaken;
    }
    
}