
package view.entity;

import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import model.IExplodingProjectile;
import view.effects.EEffects;

/**
 * The graphical representation of the Nuke powerups projectile, when it is shot.
 * 
 * Connects the visual representation with its effects as well handling
 * collision shapes.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class NapalmEntity extends AExplodingEntity {

    /**
     * Instantiates the object.
     * 
     * @param proj The model of the object
     */
    public NapalmEntity(IExplodingProjectile proj) {
        super(proj, EGraphics.NAPALM, EEffects.NAPALM.getEmitters());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }
}
