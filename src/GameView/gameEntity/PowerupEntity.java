
package GameView.gameEntity;

import model.IPowerup;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;

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
    
    /**
     * @inheritdoc
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }
}
