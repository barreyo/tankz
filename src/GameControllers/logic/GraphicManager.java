package GameControllers.Managers;

import App.TanksAppAdapter;
import GameView.graphics.EGraphics;
import com.jme3.scene.Spatial;
import java.util.EnumMap;

/**
 * @author Daniel
 */
public enum GraphicManager {

    /**
     *
     */
    INSTANCE;
    private EnumMap<EGraphics, Spatial> graphicMap = new EnumMap<EGraphics, Spatial>(EGraphics.class);

    public void load() {
        loadGraphics(new EGraphics[]{EGraphics.SHARK, EGraphics.TANK,
                    EGraphics.BOMB, EGraphics.LANDMINE, EGraphics.NUKE,
                    EGraphics.POWERUP});
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
        return TanksAppAdapter.INSTANCE.loadModel(graphic.getPath());
    }

    /**
     *
     */
    public void cleanup() {
        graphicMap.clear();
    }
}
