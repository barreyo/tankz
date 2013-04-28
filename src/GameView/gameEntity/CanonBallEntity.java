
package GameView.gameEntity;

import GameModel.IExplodingProjectile;
import App.TanksAppAdapter;
import GameUtilities.Commands;
import GameView.effects.EEffects;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.effect.ParticleEmitter;
import com.jme3.scene.Spatial;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;

/**
 * A missile projectile.
 *
 * @author Daniel
 */
public final class CanonBallEntity extends AGameEntity{
    private IExplodingProjectile projectile;
    private final Collection<ParticleEmitter> effects;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public CanonBallEntity(IExplodingProjectile proj) {
        super(EGraphics.BOMB);
        effects = EEffects.EXPLOSION.getEmitters();
        
        spatial.setUserData("Model", proj);
        projectile = proj;
        
        proj.addObserver(this);
        hideFromWorld();
        TanksAppAdapter.INSTANCE.attachChildToRootNode(spatial);
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
        TanksAppAdapter.INSTANCE.detachChildFromRootNode(spatial);
        projectile.removeObserver(this);
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Commands.SHOW)) {
            showInWorld();
            updatePosition();
            updateRotation();
        } else if (evt.getPropertyName().equals(Commands.CLEANUP)) {
            // Clean up
            cleanup();
        } else if (evt.getPropertyName().equals(Commands.EXPLOSION_FINISHED)) {
            for (ParticleEmitter effect : effects) {
                if (effect.getParent() != null) {
                    // Remove effect from world
                    effect.killAllParticles();
                    effect.removeFromParent();
                }
            }
        } else if (evt.getPropertyName().equals(Commands.HIDE)) {
            hideFromWorld();
        }
        pcs.firePropertyChange(evt);
    }
    
    public void impact() {
        hideFromWorld();
        showEffect();
    }

    private void showEffect() {
        for (ParticleEmitter effect : effects) {
            if (effect != null) {
                effect.setLocalTranslation(spatial.getWorldTranslation());
                TanksAppAdapter.INSTANCE.attachChildToRootNode(effect);
                effect.emitAllParticles();
            }
        }
    }

    private void updatePosition() {
        spatial.setLocalTranslation(projectile.getInitialPosition());
    }
    
    private void updateRotation() {
        spatial.setLocalRotation(projectile.getRotation());
    }
  
    private void showInWorld() {
        spatial.setCullHint(Spatial.CullHint.Dynamic);
    }

    public void hideFromWorld() {
        spatial.setCullHint(Spatial.CullHint.Always);
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
