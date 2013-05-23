
package view.entity;

import model.IExplodingProjectile;
import view.effects.EEffects;

/**
 * A missile projectiles visual representation.
 * Also contains the collisionshape
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class CanonBallEntity extends AExplodingEntity {

    /**
     * Instantiates the object.
     * 
     * @param proj The projectile that is the cannonball
     */
    public CanonBallEntity(IExplodingProjectile proj) {
        super(proj, EGraphics.BOMB, EEffects.EXPLOSION.getEmitters());
    }
}
