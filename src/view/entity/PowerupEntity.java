
package view.entity;

import model.IPowerup;

/**
 * The graphical representation of the powerups, when out in the world.
 * 
 * Connects the visual representation with its effects as well handling
 *  collision shapes.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class PowerupEntity extends AGameEntity {
    /**
     * Instantiates the object.
     * 
     * @param pow The powerup representated
     */
    public PowerupEntity(IPowerup pow) {
        super(pow, EGraphics.POWERUP);
    }
}
