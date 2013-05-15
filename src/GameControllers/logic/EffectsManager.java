package GameControllers.Managers;

import GameView.viewPort.EViewPorts;
import GameView.effects.EEffects;
import com.jme3.effect.ParticleEmitter;
import java.util.Collection;
import java.util.EnumMap;

/**
 *
 * @author Per
 */
public enum EffectsManager {

    /**
     *
     */
    INSTANCE;
    private EnumMap<EEffects, Collection<ParticleEmitter>> effectsMap = new EnumMap<EEffects, Collection<ParticleEmitter>>(EEffects.class);

    /**
     *
     * @param level
     */
    public void load() {
        // Call this after loading viewports
        Collection<EViewPorts> views = ViewPortManager.INSTANCE.getViews();
        loadGraphics(new EEffects[]{EEffects.EXPLOSION, EEffects.FLAME, EEffects.SHOOT,
                    EEffects.SMOKE, EEffects.TANK_BLOWN_UP}, views);
    }

    private void loadGraphics(EEffects[] effects, Collection<EViewPorts> views) {
        for (EViewPorts view : views) {
            for (EEffects effect : effects) {
                for (ParticleEmitter emitter : effect.getEmitters()) {
                    PreloadManager.INSTANCE.preload(emitter, view.getViewPort());
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
