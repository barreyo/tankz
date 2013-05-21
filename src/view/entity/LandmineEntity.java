
package view.entity;

import model.LandmineModel;
import view.effects.EEffects;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;

/**
 * The graphical representation of the Landmine powerups projectile, when it is laid.
 * 
 * Connects the visual representation with its effects as well handling
 *  collision shapes.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class LandmineEntity extends AExplodingEntity {
    
    /**
     * Instantiates the object. Creates a landmine
     */
    public LandmineEntity(LandmineModel landmine){
        super(landmine, EGraphics.LANDMINE, EEffects.EXPLOSION.getEmitters());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents().multLocal(1, 2, 1));
    }
}
