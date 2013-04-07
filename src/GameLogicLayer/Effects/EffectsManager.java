package GameLogicLayer.Effects;

import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.util.IManager;
import GameLogicLayer.util.PreloadManager;
import GameLogicLayer.viewPort.EViewPorts;
import GameLogicLayer.viewPort.ViewPortManager;
import GameViewLayer.effects.EEffects;
import GameViewLayer.effects.IEffect;
import com.jme3.effect.ParticleEmitter;
import com.jme3.renderer.ViewPort;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Set;

/**
 *
 * @author Per
 */
public class EffectsManager implements IManager {

    private TanksGame app;
    private EnumMap<EEffects, ParticleEmitter> effectsMap = new EnumMap<EEffects, ParticleEmitter>(EEffects.class);
    private PreloadManager preloadManager;
    private ViewPortManager viewPortManager;

    /**
     *  Creates an manager for effects.
     */
    public EffectsManager() {
        app = TanksGame.getApp();
        preloadManager = app.getPreloadManager();
        viewPortManager = app.getViewPortManager();
    }

    /**
     *
     * @param level
     */
    @Override
    public void load(int level) {
        // Call this after loading viewports
        Collection<EViewPorts> views = viewPortManager.getViews();
        if (level == 1) {
            loadGraphics(new EEffects[]{EEffects.EXPLOSION}, views);
        } else if (level == 2) {
            
        }
    }

    private void loadGraphics(EEffects[] effects, Collection<EViewPorts> views) {
        for (EViewPorts view : views) {
            for (EEffects effect : effects) {
                ParticleEmitter emitter = effect.getEmitter();
                preloadManager.preload(emitter, view.getViewPort());
                effectsMap.put(effect, emitter);
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
