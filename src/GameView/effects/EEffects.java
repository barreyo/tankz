package GameView.effects;

import com.jme3.effect.ParticleEmitter;
import java.util.Collection;


/**
 *
 * @author Daniel
 */
public enum EEffects {
    EXPLOSION(new ExplosionEffect()),
    SHOOT(new ShootEffect()),
    TANK_BLOWN_UP(new DestroyedEffect());
    
    
    private IEffect effect;
    
    EEffects(IEffect effect){
        this.effect = effect;
    }
    
    public Collection<ParticleEmitter> getEmitters() {
        return effect.getParticleEmitters();
    }
}
