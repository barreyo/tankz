package GameControllers.logic;

import GameView.viewPort.EViewPorts;
import GameView.effects.EEffects;
import com.jme3.effect.ParticleEmitter;
import java.util.Collection;
import java.util.EnumMap;

/**
 *
 * @author Per
 */
public enum EffectsManager implements IMapRelatedManager {
    /**
     *
     */
    INSTANCE;

    private EnumMap<EEffects, Collection<ParticleEmitter>> effectsMap = new EnumMap<EEffects, Collection<ParticleEmitter>>(EEffects.class);

    /**
     *
     * @param level
     */
    @Override
    public void load(int level) {
        // Call this after loading viewports
        Collection<EViewPorts> views = ViewPortManager.INSTANCE.getViews();
        if (level == 1) {
            loadGraphics(new EEffects[]{EEffects.EXPLOSION}, views);
        } else if (level == 2) {
            
        }
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
     *  Releases resources held by this manager.
     */
    @Override
    public void cleanup() {
        effectsMap.clear();
    }
}
