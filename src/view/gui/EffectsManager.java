package view.gui;

import view.gui.EEffects;
import com.jme3.effect.ParticleEmitter;
import java.util.Collection;
import java.util.EnumMap;
import view.viewport.EViewPorts;
import controller.managers.PreloadManager;
import controller.managers.PreloadManager;
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
     * Load all effects.
     */
    public void load() {
        // Call this after loading viewports
        Collection<EViewPorts> views = ViewPortManager.INSTANCE.getViews();
        loadGraphics(new EEffects[]{EEffects.EXPLOSION, EEffects.FLAME, EEffects.SHOOT,
                    EEffects.SMOKE, EEffects.TANK_BLOWN_UP}, views);
    }

    /**
     * Load graphics for effects.
     * 
     * @param effects effects to add graphics tp.
     * @param views viewports.
     */
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
