
package model;

/**
 * All object that can take damage should implement this interface lol.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public interface IDamageableObject extends IWorldObject{
    
    /**
     * Applies damage to the world object.
     * 
     * @param hp the damage to apply
     * @return returns true if the object was killed, false otherwise.
     */
    public boolean applyDamageToKill(int hp);
}
