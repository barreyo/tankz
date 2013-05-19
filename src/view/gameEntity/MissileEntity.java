package view.gameEntity;

import model.IExplodingProjectile;
import utilities.Commands;
import view.effects.EEffects;
import view.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.effect.ParticleEmitter;
import java.beans.PropertyChangeEvent;
import java.util.Collection;

/**
 *
 * @author Daniel
 */
public final class MissileEntity extends AExplodingEntity {
    private final Collection<ParticleEmitter> flameEffects;

    /**
     *
     * @param proj
     */
    public MissileEntity(IExplodingProjectile proj) {
        super(proj, EGraphics.SHARK, EEffects.EXPLOSION.getEmitters());
        flameEffects = EEffects.FLAME.getEmitters();
    }

    /**
     * @inheritdoc
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }

    /**
     * @inheritdoc
     */
    @Override
    public void cleanup() {
        super.cleanup();
        flameEffects.clear();
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Commands.SHOW_FLAME)) {
            showEffects(flameEffects, spatial.getWorldTranslation());
        }
        super.propertyChange(evt);
    }

    /**
     *
     */
    @Override
    public void impact() {
        super.impact();
        hideEffects(flameEffects);
    }
}
