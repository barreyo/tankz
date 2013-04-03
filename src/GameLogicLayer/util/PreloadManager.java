
package GameLogicLayer.util;

import GameLogicLayer.Game.TanksGame;
import com.jme3.effect.ParticleEmitter;
import com.jme3.material.Material;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;

/**
 *
 * @author Daniel
 */
public class PreloadManager {

    private TanksGame app = TanksGame.getApp();
    private RenderManager renderManager = app.getRenderManager();

    /**
     *
     * @param m
     */
    public void preload(Material m) {
        m.preload(renderManager);
    }

    /**
     *
     * @param s
     */
    public void preload(Spatial s) {
        renderManager.preloadScene(s);
    }
    
    /**
     * 
     * @param effect
     * @param view 
     */
    public void preload(ParticleEmitter effect, ViewPort view) {
        effect.preload(renderManager, view);
    }
}