package GameView.Map;

import GameControllers.logic.GraphicManager;
import App.TanksAppAdapter;
import GameModel.ITanks;
import GameView.graphics.EGraphics;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.util.SkyFactory;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * First developed game map for tha game Tanks.
 *
 * @author Daniel
 */
public class GameWorld1 implements IGameWorld, PropertyChangeListener {
    
    private ITanks game;
    
    private Node mapNode;
    private Node mainScene;
    private DirectionalLight sun;
    
    private static final Vector3f LIGHT_DIR = new Vector3f(-4.9236743f, -1.27054665f, 5.896916f);
    
    /**
     * Creates a game map.
     */
    public GameWorld1(ITanks game) {
        this.game = game;
    }

    /**
     * @inheritdoc
     */
    public void load() {
        mainScene = new Node("Main Scene");
        TanksAppAdapter.INSTANCE.attachChildToRootNode(mainScene);
        
        sun = new DirectionalLight();
        sun.setDirection(LIGHT_DIR);
        sun.setColor(ColorRGBA.White.clone().multLocal(1.7f));
        TanksAppAdapter.INSTANCE.addLightToRootNode(sun);
        
        Spatial sky = SkyFactory.createSky(TanksAppAdapter.INSTANCE.getAssetManager(), 
                                        "Scenes/FullskiesSunset0068.dds", false);
        sky.setLocalScale(350);
        
        mainScene.attachChild(sky);
        
        // Load, attach map to root node, and add nodes and geoms in the map to physicsspace
        mapNode = (Node) GraphicManager.INSTANCE.createSpatial(EGraphics.MAP);
        mapNode.setShadowMode(RenderQueue.ShadowMode.Receive);
        TanksAppAdapter.INSTANCE.attachChildToRootNode(mapNode);
        TanksAppAdapter.INSTANCE.addAllToPhysicsSpace(mapNode);
        //TanksAppAdapter.INSTANCE.getPhysicsSpace().enableDebug(TanksAppAdapter.INSTANCE.getAssetManager());
    }

    /**
     * @inheritdoc
     */
    public void cleanup() {
        TanksAppAdapter.INSTANCE.detachAllRootChildren();
        TanksAppAdapter.INSTANCE.removeLightFromRootNode(sun);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
