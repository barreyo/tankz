/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Graphics;

import GameLogicLayer.Game.TanksGame;
import GameViewLayer.graphics.EMaterials;
import GameLogicLayer.util.IManager;
import GameLogicLayer.util.PreloadManager;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import java.util.EnumMap;

/**
 *
 * @author Daniel
 */
public class MaterialManager implements IManager {
    
    private EnumMap<EMaterials, Material> materialMap = new EnumMap<EMaterials, Material>(EMaterials.class);
    private TanksGame app;
    private AssetManager assetManager;
    private PreloadManager preloadManager;
    
    /**
     *
     */
    public MaterialManager() {
        app = TanksGame.getApp();
        assetManager = app.getAssetManager();
        preloadManager = app.getPreloadManager();
        
    }

    /**
     *
     * @param level
     */
    public void load(int level) {

        //create an instance of these materials and use them for each level
        if(level == 1) {
            //loadMaterials(new Materials[]{Materials.NORMAL});
        } else if (level == 2) {
            // NOT IMPLEMENTED YET
        }
    }

    private void loadMaterials(EMaterials[] materials) {
        for (EMaterials material : materials) {
            Material m = assetManager.loadMaterial(material.getPathToMaterial());
            materialMap.put(material, m);
            preloadManager.preload(m);
        }
    }

    /**
     *
     * @param material
     * @return
     */
    public Material getMaterial(EMaterials material) {
        return materialMap.get(material);
    }

    // remove all entries to the materialMap
    /**
     *
     */
    public void cleanup() {
        materialMap.clear();
    }
    
}
