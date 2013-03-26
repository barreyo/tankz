package GameLogicLayer.Graphics;

import GameViewLayer.graphics.EGraphics;
import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.util.Manager;
import GameLogicLayer.util.PreloadManager;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;
import java.util.EnumMap;

/**
 *
 * @author Daniel
 */
public class GraphicManager implements Manager {

    private TanksGame app;
    private AssetManager assetManager;
    private EnumMap<EGraphics, Spatial> graphicMap = new EnumMap<EGraphics, Spatial>(EGraphics.class);;
    private PreloadManager preloadManager;
    
    /**
     *
     */
    public GraphicManager() {
        app = TanksGame.getApp();
        assetManager = app.getAssetManager();
        preloadManager = app.getPreloadManager();
    }

    /**
     *
     * @param level
     */
    @Override
    public void load(int level) {
        //load all needed graphics
        if (level == 1) {
            loadGraphics(new EGraphics[]{EGraphics.TEST_PLATFORM});
        } else if (level == 2) {
            loadGraphics(new EGraphics[]{EGraphics.TEST_PLATFORM});
        }
    }

    private void loadGraphics(EGraphics[] graphics) {
        for (EGraphics graphic : graphics) {
            Spatial s = createSpatial(graphic);
            preloadManager.preload(s);
            graphicMap.put(graphic, s);
        }
    }

    /**
     *
     * @param graphic
     * @return
     */
    public Spatial createSpatial(EGraphics graphic) {
        return assetManager.loadModel(graphic.getPath());
    }

    /**
     *
     */
    @Override
    public void cleanup() {
        graphicMap.clear();
    }
}
