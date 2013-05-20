
package view.gameEntity;

import model.LandmineModel;
import view.effects.EEffects;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;

/**
 *
 * @author perthoresson
 */
public final class LandmineEntity extends AExplodingEntity {
    
    /**
     *
     */
    public LandmineEntity(LandmineModel landmine){
        super(landmine, EGraphics.LANDMINE, EEffects.EXPLOSION.getEmitters());
    }

    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents().multLocal(1, 2, 1));
    }
}
