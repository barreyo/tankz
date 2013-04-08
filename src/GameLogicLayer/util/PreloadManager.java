
package GameLogicLayer.util;

import GameLogicLayer.Effects.EffectsManager;
import GameLogicLayer.AppStates.TanksGame;
import com.jme3.effect.ParticleEmitter;
import com.jme3.material.Material;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;

/**
 * Used to preload different kinds of game data.
 * 
 * @author Daniel
 */
public class PreloadManager {
    private static PreloadManager instance;

    private TanksGame app = TanksGame.getApp();
    private RenderManager renderManager = app.getRenderManager();

    private PreloadManager() {}
    
    public static synchronized PreloadManager getInstance() {
        if (instance == null) {
            instance = new PreloadManager();
        }
        return instance;
    }
    /**
     * Preloads the specified material.
     * 
     * @param m the material to be preloaded
     */
    public void preload(Material m) {
        m.preload(renderManager);
    }

    /**
     * Preloads the specified spatial.
     * 
     * @param s the spatial to be preloaded
     */
    public void preload(Spatial s) {
        renderManager.preloadScene(s);
    }
    
    /**
     * Preloads the specified effect for the specified viewport.
     * 
     * @param effect The effect to be preloaded
     * @param view The viewport which the effect will be preloaded for
     */
    public void preload(ParticleEmitter effect, ViewPort view) {
        effect.preload(renderManager, view);
    }
}