
package view.effects;

import com.jme3.effect.ParticleEmitter;
import java.util.Collection;

/**
 *
 * @author Daniel
 */
public interface IEffect {
    /**
     *
     * @return
     */
    public Collection<ParticleEmitter> getParticleEmitters();
}
