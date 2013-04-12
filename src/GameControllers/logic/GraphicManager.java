package GameControllers.logic;

import GameControllers.logic.IMapRelatedManager;
import GameControllers.logic.PreloadManager;
import GameUtilities.TankAppAdapter;
import GameView.graphics.EGraphics;
import GameView.graphics.EGraphics;
import com.jme3.scene.Spatial;
import java.util.EnumMap;

/**
 * @author Daniel
 */
public enum GraphicManager implements IMapRelatedManager {
    INSTANCE;

    private EnumMap<EGraphics, Spatial> graphicMap = new EnumMap<EGraphics, Spatial>(EGraphics.class);

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
            PreloadManager.INSTANCE.preload(s);
            graphicMap.put(graphic, s);
        }
    }

    /**
     *
     * @param graphic
     * @return
     */
    public Spatial createSpatial(EGraphics graphic) {
        return TankAppAdapter.INSTANCE.loadModel(graphic.getPath());
    }

    /**
     *
     */
    @Override
    public void cleanup() {
        graphicMap.clear();
    }
}
