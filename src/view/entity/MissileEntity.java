package view.entity;

import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.effect.ParticleEmitter;
import java.beans.PropertyChangeEvent;
import java.util.Collection;
import model.IExplodingProjectile;
import utilities.Commands;
import view.effects.EEffects;

/**
 * The graphical representation of the Missile powerups projectile, when it is shot.
 * 
 * Connects the visual representation with its effects as well handling
 *  collision shapes.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class MissileEntity extends AExplodingEntity {
    private final Collection<ParticleEmitter> flameEffects;

    /**
     * Instantiates the object. Creating a missile
     * 
     * @param proj The projectile being shot
     */
    public MissileEntity(IExplodingProjectile proj) {
        super(proj, EGraphics.SHARK, EEffects.EXPLOSION.getEmitters());
        flameEffects = EEffects.FLAME.getEmitters();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanup() {
        super.cleanup();
        flameEffects.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Commands.SHOW_FLAME)) {
            showEffects(flameEffects, spatial.getWorldTranslation());
        }
        super.propertyChange(evt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void impact() {
        super.impact();
        hideEffects(flameEffects);
    }
}
