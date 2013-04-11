package GameView.effects;

import com.jme3.effect.ParticleEmitter;

/**
 *
 * @author Daniel
 */
public enum EEffects {
    EXPLOSION(new ExplosionEffect());
    
    private IEffect effect;
    
    EEffects(IEffect effect){
        this.effect = effect;
    }
    
    public ParticleEmitter getEmitter() {
        return effect.getParticleEmitter();
    }
}
