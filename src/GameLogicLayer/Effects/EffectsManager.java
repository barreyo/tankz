package GameLogicLayer.Effects;

import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.util.IManager;
import GameLogicLayer.util.PreloadManager;
import GameLogicLayer.viewPort.EViewPorts;
import GameViewLayer.effects.EEffects;
import GameViewLayer.effects.IEffect;
import com.jme3.effect.ParticleEmitter;
import com.jme3.renderer.ViewPort;
import java.util.EnumMap;

/**
 *
 * @author Per
 */
public class EffectsManager implements IManager {

    private TanksGame app;
    private EnumMap<EEffects, ParticleEmitter> effectsMap = new EnumMap<EEffects, ParticleEmitter>(EEffects.class);
    private PreloadManager preloadManager;

    /**
     *
     */
    public EffectsManager() {
        app = TanksGame.getApp();
        preloadManager = app.getPreloadManager();
    }

    /**
     *
     * @param level
     */
    @Override
    public void load(int level) {
        // TODO change this viewports has to be detirmined by number of players.
        if (level == 1) {
            loadGraphics(new EEffects[]{EEffects.EXPLOSION},
                         new EViewPorts[]{EViewPorts.VIEW1, EViewPorts.VIEW2});
        } else if (level == 2) {
            
        }
    }

    private void loadGraphics(EEffects[] effects, EViewPorts[] views) {
        for (EViewPorts view : views) {
            for (EEffects effect : effects) {
                ParticleEmitter emitter = effect.getEffect().getParticleEmitter();
                preloadManager.preload(emitter, view.getViewPort());
                effectsMap.put(effect, emitter);
            }
        }
    }

    /**
     *
     */
    @Override
    public void cleanup() {
        effectsMap.clear();
    }
}
