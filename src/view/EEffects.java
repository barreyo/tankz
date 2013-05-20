package view;

import com.jme3.effect.ParticleEmitter;
import java.util.Collection;


/**
 *
 * @author Daniel
 */
public enum EEffects {
    /**
     *
     */
    EXPLOSION(new ExplosionEffect()),
    /**
     *
     */
    SHOOT(new ShootEffect()),
    /**
     *
     */
    TANK_BLOWN_UP(new DestroyedEffect()),
    /**
     *
     */
    SMOKE(new SmokeEffect()),
    /**
     * 
    */
    FLAME(new FlameEffect()),
    /**
     * 
     */
    NUKE(new NapalmEffect());
    
    
    private IEffect effect;
    
    EEffects(IEffect effect){
        this.effect = effect;
    }
    
    /**
     *
     * @return
     */
    public Collection<ParticleEmitter> getEmitters() {
        return effect.getParticleEmitters();
    }
}
