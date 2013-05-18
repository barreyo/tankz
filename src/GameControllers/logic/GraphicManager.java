package GameControllers.logic;

import App.TanksAppAdapter;
import GameView.graphics.EGraphics;
import com.jme3.scene.Spatial;
import java.util.EnumMap;

/**
 * Manager managing graphic relate operations and instances.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum GraphicManager {

    /**
     * Gain access to this singleton.
     */
    INSTANCE;
    
    private EnumMap<EGraphics, Spatial> graphicMap = new EnumMap<EGraphics, Spatial>(EGraphics.class);

    /**
     * Load all game grahics, spatials.
     */
    public void load() {
        loadGraphics(new EGraphics[]{EGraphics.SHARK, EGraphics.TANK,
                    EGraphics.BOMB, EGraphics.LANDMINE, EGraphics.NUKE,
                    EGraphics.POWERUP, EGraphics.MAP1});
    }

    /**
     * Load specific graphics.
     * 
     * @param graphics list of spatials.
     */
    private void loadGraphics(EGraphics[] graphics) {
        for (EGraphics graphic : graphics) {
            Spatial s = createSpatial(graphic);
            PreloadManager.INSTANCE.preload(s);
            graphicMap.put(graphic, s);
        }
    }

    /**
     * Create a spatial from the grahics enum.
     * 
     * @param graphic spatial path.
     * @return created spatial.
     */
    public Spatial createSpatial(EGraphics graphic) {
        return TanksAppAdapter.INSTANCE.loadModel(graphic.getPath());
    }

    /**
     * Clear all loaded graphic.
     */
    public void cleanup() {
        graphicMap.clear();
    }
}
