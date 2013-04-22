package GameView.gameEntity;

import GameModel.IExplodingProjectile;
import GameView.effects.EEffects;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.effect.ParticleEmitter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;

/**
 *
 * @author Daniel
 */
public final class MissileEntity extends AGameEntity {
    private IExplodingProjectile projectile;
    private final Collection<ParticleEmitter> effects;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public MissileEntity(IExplodingProjectile proj) {
        super(EGraphics.BOMB);
        effects = EEffects.EXPLOSION.getEmitters();
        
        setModel(proj);
    }
    
    /**
     * @inheritdoc
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new SphereCollisionShape(0.4f);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void cleanup() {
        if (spatial.getParent() != null) {
            // Remove ourself from world
            spatial.removeFromParent();
        }
        projectile.removeObserver(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      
    }
    public void setModel(IExplodingProjectile proj) {
        if (projectile != null) {
            this.cleanup();
        }
        projectile = proj;
        if (projectile != null) {
            projectile.addObserver(this);
        }
    }

    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
}
