package GameViewLayer.effects;

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
    
    public IEffect getEffect() {
        return effect;
    }
}
