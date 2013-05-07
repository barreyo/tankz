
package GameModel;

/**
 *
 * @author Bex
 */
public interface IDamageableObject extends IWorldObject{
    /**
     * Applies damage to the world object.
     * @param hp the damage to apply
     */
    public void applyDamage(int hp);
}
