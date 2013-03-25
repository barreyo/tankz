/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.util;

import GameLogicLayer.Game.TanksGame;
import com.jme3.material.Material;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;

public class PreloadManager {

    private TanksGame app = TanksGame.getApp();
    private RenderManager renderManager = app.getRenderManager();

    public void preload(Material m) {
        m.preload(renderManager);
    }

    public void preload(Spatial s) {
        renderManager.preloadScene(s);
    }
}