package GameLogicLayer.Graphics;

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
    private EnumMap<Graphics, Spatial> graphicMap = new EnumMap<Graphics, Spatial>(Graphics.class);;
    private PreloadManager preloadManager;
    
    public GraphicManager() {
        app = TanksGame.getApp();
        assetManager = app.getAssetManager();
        preloadManager = app.getPreloadManager();
    }

    @Override
    public void load(int level) {
        //load all needed graphics
        if (level == 1) {
            loadGraphics(new Graphics[]{Graphics.TEST_PLATFORM});
        } else if (level == 2) {
            loadGraphics(new Graphics[]{Graphics.TEST_PLATFORM});
        }
    }

    private void loadGraphics(Graphics[] graphics) {
        for (Graphics graphic : graphics) {
            Spatial s = createSpatial(graphic);
            preloadManager.preload(s);
            graphicMap.put(graphic, s);
        }
    }

    public Spatial createSpatial(Graphics graphic) {
        return assetManager.loadModel(graphic.getPath());
    }

    @Override
    public void cleanup() {
        graphicMap.clear();
    }
}
