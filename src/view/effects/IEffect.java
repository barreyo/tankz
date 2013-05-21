
package view.effects;

import com.jme3.effect.ParticleEmitter;
import java.util.Collection;

/**
 * Inferface for effects.
 * Every effect should have particle emitters in order to be able to be shown
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public interface IEffect {
    /**
     *
     * @return
     */
    public Collection<ParticleEmitter> getParticleEmitters();
}
