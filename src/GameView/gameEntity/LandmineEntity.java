
package GameView.gameEntity;

import App.TanksAppAdapter;
import GameModel.LandmineModel;
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
 *
 * @author perthoresson
 */
public final class LandmineEntity extends AGameEntity {
    private LandmineModel landmine;
    private final Collection<ParticleEmitter> effects;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     *
     */
    public LandmineEntity(LandmineModel landmine){
        super(EGraphics.LANDMINE);
        effects = EEffects.EXPLOSION.getEmitters();
        
        spatial.setUserData("Model", landmine);
        this.landmine = landmine;
        
        landmine.addObserver(this);
        hideFromWorld();
        TanksAppAdapter.INSTANCE.attachChildToRootNode(spatial);
    }

    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }

    @Override
    public void cleanup() {
        TanksAppAdapter.INSTANCE.detachChildFromRootNode(spatial);
        landmine.removeObserver(this);
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
        spatial.setLocalTranslation(landmine.getInitialPosition());
    }
    
    private void updateRotation() {
        spatial.setLocalRotation(landmine.getRotation());
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
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
    
}
