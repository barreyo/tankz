/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Graphics;

import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.util.Manager;
import GameLogicLayer.util.PreloadManager;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import java.util.EnumMap;

/**
 *
 * @author Daniel
 */
public class MaterialManager implements Manager {
    
    private EnumMap<Materials, Material> materialMap = new EnumMap<Materials, Material>(Materials.class);
    private TanksGame app;
    private AssetManager assetManager;
    private PreloadManager preloadManager;
    
    public MaterialManager() {
        app = TanksGame.getApp();
        assetManager = app.getAssetManager();
        preloadManager = app.getPreloadManager();
        
    }

    public void load(int level) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // remove all entries to the materialMap
    public void cleanup() {
        materialMap.clear();
    }
    
}
