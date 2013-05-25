package view.effects;

import application.TanksAppAdapter;
import com.jme3.effect.ParticleEmitter;
import java.util.Collection;
import java.util.EnumMap;
import view.viewport.EViewPorts;
import view.viewport.ViewPortManager;

/**
 * Manager for the in game effects, singleton.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum EffectsManager {

    /**
     * Instance to this manager.
     */
    INSTANCE;
    private EnumMap<EEffects, Collection<ParticleEmitter>> effectsMap = new EnumMap<EEffects, Collection<ParticleEmitter>>(EEffects.class);

    /**
     * Load graphics for effects.
     * 
     * @param effects effects to add graphics tp.
     * @param views viewports.
     */
    public void load() {
        Collection<EViewPorts> views = ViewPortManager.INSTANCE.getViews();
        EEffects[] effects = EEffects.values();
        for (EViewPorts view : views) {
            for (EEffects effect : effects) {
                for (ParticleEmitter emitter : effect.getEmitters()) {
                    emitter.preload(TanksAppAdapter.INSTANCE.getRenderManager(), view.getViewPort());
                }
                effectsMap.put(effect, effect.getEmitters());
            }
        }
    }

    /**
     * Releases resources held by this manager.
     */
    public void cleanup() {
        effectsMap.clear();
    }
}
