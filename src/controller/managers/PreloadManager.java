
package controller.managers;

import application.TanksAppAdapter;
import com.jme3.effect.ParticleEmitter;
import com.jme3.material.Material;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;

/**
 * Used to preload different kinds of game data.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum PreloadManager {
    /**
     * Gain access to this singleton instance.
     */
    INSTANCE;
    
    /**
     * Preloads the specified material.
     * 
     * @param m the material to be preloaded
     */
    public void preload(Material m) {
        m.preload(TanksAppAdapter.INSTANCE.getRenderManager());
    }

    /**
     * Preloads the specified spatial.
     * 
     * @param s the spatial to be preloaded
     */
    public void preload(Spatial s) {
        TanksAppAdapter.INSTANCE.preloadSpatial(s);
    }
    
    /**
     * Preloads the specified effect for the specified viewport.
     * 
     * @param effect The effect to be preloaded
     * @param view The viewport which the effect will be preloaded for
     */
    public void preload(ParticleEmitter effect, ViewPort view) {
        effect.preload(TanksAppAdapter.INSTANCE.getRenderManager(), view);
    }
}