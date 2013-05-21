package view.entity;

import com.jme3.effect.ParticleEmitter;
import java.beans.PropertyChangeEvent;
import java.util.Collection;
import model.IExplodingObject;
import utilities.Commands;

/**
 * Abstraction of an exploding entity.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public abstract class AExplodingEntity extends AGameEntity {
    
    final IExplodingObject explodingObject;
    private final Collection<ParticleEmitter> effects;
    
    /**
     * Instantiates the object.
     * Creating the exploding entity
     * 
     * @param explodingObject The object
     * @param graphic The objects graphics
     * @param effects The objects effects
     */
    AExplodingEntity(IExplodingObject explodingObject, EGraphics graphic, Collection<ParticleEmitter> effects) {
        super(explodingObject, graphic);
        
        this.effects = effects;
        this.explodingObject = explodingObject;
    }

    /**
     * This method is called whenever the exploding entity makes impact.
     */
    public void impact() {
        hideFromWorld();
        showEffects(effects, spatial.getWorldTranslation());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    void updatePosition() {
        spatial.setLocalTranslation(explodingObject.getInitialPosition());
    }
    
    /**
     * {@inheritDoc}
     */
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
     * {@inheritDoc}
     */
    @Override
    public void cleanup() {
        super.cleanup();
        effects.clear();
    }
}
