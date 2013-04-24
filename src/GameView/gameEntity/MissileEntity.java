package GameView.gameEntity;

import App.TanksAppAdapter;
import GameModel.IExplodingProjectile;
import GameView.effects.EEffects;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.effect.ParticleEmitter;
import com.jme3.math.Quaternion;
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
        super(EGraphics.SHARK);
        effects = EEffects.EXPLOSION.getEmitters();
        
        setModel(proj);
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
        if (spatial.getParent() != null) {
            // Remove ourself from world
            spatial.removeFromParent();
        }
        projectile.removeObserver(this);
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IExplodingProjectile.END_OF_LIFETIME)) {
            // Clean up
            cleanup();
        } else if (evt.getPropertyName().equals(IExplodingProjectile.EXPLOSION_FINISHED)) {
            for (ParticleEmitter effect : effects) {
                if (effect.getParent() != null) {
                    // Remove effect from world
                    effect.killAllParticles();
                    effect.removeFromParent();
                }
            }
            projectile.removeObserver(this);
        } else if (evt.getPropertyName().equals(IExplodingProjectile.IMPACT_MADE)) {
            impact();
        } else if (evt.getPropertyName().equals(IExplodingProjectile.ROTATE)) {
            spatial.setLocalRotation((Quaternion)evt.getNewValue());
        }
        pcs.firePropertyChange(evt);
    }
    
    private void impact() {
        showEffect();
        if (spatial.getParent() != null) {
            // Remove projectile from world
            spatial.removeFromParent();
        }
    }

    private void showEffect() {
        for (ParticleEmitter effect : effects) {
            if (effect != null && spatial.getParent() != null) {
                effect.setLocalTranslation(spatial.getLocalTranslation());
                spatial.getParent().attachChild(effect);
                effect.emitAllParticles();
            }
        }
    }

    public IExplodingProjectile getModel() {
       return projectile;
    }
    
    public Collection<ParticleEmitter> getEffects() {
        return effects;
    }

    public void setModel(IExplodingProjectile proj) {
        if (projectile != null) {
            this.cleanup();
        }
        projectile = proj;
        if (projectile != null) {
            projectile.addObserver(this);
        }
        updatePosition();
        updateRotation();
        attachToRootNode();
    }
    
    private void updatePosition() {
        spatial.setLocalTranslation(projectile.getPosition());
    }
    
    private void updateRotation() {
        spatial.setLocalRotation(projectile.getRotation());
    }
    
    private void attachToRootNode() {
        TanksAppAdapter.INSTANCE.attachChildToRootNode(spatial);
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
