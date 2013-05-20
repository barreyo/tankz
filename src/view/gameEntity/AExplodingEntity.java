package view.gameEntity;

import model.IExplodingObject;
import utilities.Commands;
import com.jme3.effect.ParticleEmitter;
import java.beans.PropertyChangeEvent;
import java.util.Collection;

/**
 *
 * @author Bex
 */
public abstract class AExplodingEntity extends AGameEntity {
    
    final IExplodingObject explodingObject;
    private final Collection<ParticleEmitter> effects;
    
    AExplodingEntity(IExplodingObject explodingObject, EGraphics graphic, Collection<ParticleEmitter> effects) {
        super(explodingObject, graphic);
        
        this.effects = effects;
        this.explodingObject = explodingObject;
    }

    public void impact() {
        hideFromWorld();
        showEffects(effects, spatial.getWorldTranslation());
    }
    
    @Override
    void updatePosition() {
        spatial.setLocalTranslation(explodingObject.getInitialPosition());
    }
    
    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Commands.EXPLOSION_FINISHED)) {
            for (ParticleEmitter effect : effects) {
                if (effect.getParent() != null) {
                    // Remove effect from world
                    effect.killAllParticles();
                    effect.removeFromParent();
                }
            }
        }
        super.propertyChange(evt);
    }
    
    /**
     * @inheritdoc
     */
    @Override
    public void cleanup() {
        super.cleanup();
        effects.clear();
    }
}
