
package GameModel;

/**
 *
 * @author Bex
 */
public interface IDamageableObject extends IWorldObject{
    /**
     * Applies damage to the world object.
     * @param hp the damage to apply
     * 
     * Returns true if the object was killed, false otherwise.
     */
    public boolean applyDamageToKill(int hp);
}
