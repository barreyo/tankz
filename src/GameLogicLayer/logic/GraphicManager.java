package GameLogicLayer.logic;

import GameViewLayer.graphics.EGraphics;
import GameLogicLayer.logic.IManager;
import GameLogicLayer.logic.PreloadManager;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;
import java.util.EnumMap;

/**
 * Extenda assetmanager?
 * @author Daniel
 */
public class GraphicManager implements IManager {
    private static GraphicManager instance;

    private TanksGame app;
    private AssetManager assetManager;
    private EnumMap<EGraphics, Spatial> graphicMap = new EnumMap<EGraphics, Spatial>(EGraphics.class);;
    private PreloadManager preloadManager;
    
    /**
     *
     */
    private GraphicManager() {
        app = TanksGame.getApp();
        assetManager = app.getAssetManager();
        preloadManager = PreloadManager.getInstance();
    }
    
    public static synchronized GraphicManager getInstance() {
        if (instance == null) {
            instance = new GraphicManager();
        }
        return instance;
    }

    /**
     *
     * @param level
     */
    @Override
    public void load(int level) {
        if (level == 1) {
            loadGraphics(new EGraphics[]{EGraphics.SHARK, EGraphics.TANK});
        } else if (level == 2) {
            //loadGraphics(new EGraphics[]{EGraphics.TEST_PLATFORM});
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
