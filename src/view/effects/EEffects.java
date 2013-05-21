package view.effects;

import com.jme3.effect.ParticleEmitter;
import java.util.Collection;


/**
 * Enum holding all the effects.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum EEffects {
    /**
     * The explosion effect.
     */
    EXPLOSION(new ExplosionEffect()),
    
    /**
     * The shooting effect.
     */
    SHOOT(new ShootEffect()),
    
    /**
     * The effect that is shown when the tank blows up.
     */
    TANK_BLOWN_UP(new DestroyedEffect()),
    
    /**
     * The smoke effect.
     */
    SMOKE(new SmokeEffect()),
    
    /**
     * The flame effect.
     */
    FLAME(new FlameEffect()),
    
    /**
     * The napalm effect.
     */
    NAPALM(new NapalmEffect());
    
    
    private IEffect effect;

    private EEffects(IEffect effect){
        this.effect = effect;
    }
    
    /**
     * Returns the particle emitters.
     * 
     * @return the emitters
     */
    public Collection<ParticleEmitter> getEmitters() {
        return effect.getParticleEmitters();
    }
}
