package GameView.Map;

import GameControllers.logic.GraphicManager;
import App.TanksAppAdapter;
import GameModel.ITanks;
import GameView.graphics.EGraphics;
import com.jme3.asset.AssetManager;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
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
     * @param game 
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
        
        AssetManager assetManager = TanksAppAdapter.INSTANCE.getAssetManager();
        sun = new DirectionalLight();
        sun.setDirection(LIGHT_DIR);
        sun.setColor(ColorRGBA.White.clone().multLocal(1.7f));
        TanksAppAdapter.INSTANCE.addLightToRootNode(sun);
        
        Texture westTex = assetManager.loadTexture("Textures/sky/west.jpg");
        Texture eastTex = assetManager.loadTexture("Textures/sky/almostWest.jpg");
        Texture northTex = assetManager.loadTexture("Textures/sky/center.jpg");
        Texture southTex = assetManager.loadTexture("Textures/sky/east.jpg");
        Texture upTex = assetManager.loadTexture("Textures/sky/top.jpg");
        Texture downTex = assetManager.loadTexture("Textures/sky/bot.jpg");
        
        final Vector3f normalScale = new Vector3f(-1, 1, 1);
        Spatial sky = SkyFactory.createSky(
                        assetManager,
                        westTex,
                        eastTex,
                        northTex,
                        southTex,
                        upTex,
                        downTex,
                        normalScale);
        //sky.setLocalScale(600);
        
        mainScene.attachChild(sky);
        
        // Load, attach map to root node, and add nodes and geoms in the map to physicsspace
        mapNode = (Node) GraphicManager.INSTANCE.createSpatial(EGraphics.MAP1);
        mapNode.setShadowMode(RenderQueue.ShadowMode.Receive);
        TanksAppAdapter.INSTANCE.attachChildToRootNode(mapNode);
        TanksAppAdapter.INSTANCE.addAllToPhysicsSpace(mapNode);
//        TanksAppAdapter.INSTANCE.getPhysicsSpace().enableDebug(TanksAppAdapter.INSTANCE.getAssetManager());
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
