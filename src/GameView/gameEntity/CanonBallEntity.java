
package GameView.gameEntity;

import GameModel.IExplodingProjectile;
import GameView.effects.EEffects;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;

/**
 * A missile projectile.
 *
 * @author Daniel
 */
public final class CanonBallEntity extends AExplodingEntity {

    /**
     *
     * @param proj
     */
    public CanonBallEntity(IExplodingProjectile proj) {
        super(proj, EGraphics.BOMB, EEffects.EXPLOSION.getEmitters());
    }
    
    /**
     * @inheritdoc
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }
}
