package GameLogicLayer.logic;

import GameViewLayer.viewPort.EViewPorts;
import GameViewLayer.effects.EEffects;
import com.jme3.effect.ParticleEmitter;
import java.util.Collection;
import java.util.EnumMap;

/**
 *
 * @author Per
 */
public class EffectsManager implements IManager {
    
    private static EffectsManager instance;

    private TanksGame app;
    private EnumMap<EEffects, ParticleEmitter> effectsMap = new EnumMap<EEffects, ParticleEmitter>(EEffects.class);
    private PreloadManager preloadManager;
    private ViewPortManager viewPortManager;

    /**
     *  Creates an manager for effects.
     */
    private EffectsManager() {
        app = TanksGame.getApp();
        preloadManager = PreloadManager.getInstance();
        viewPortManager = ViewPortManager.getInstance();
    }
    
    public static synchronized EffectsManager getInstance() {
        if (instance == null) {
            instance = new EffectsManager();
        }
        return instance;
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
