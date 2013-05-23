/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.maps;

import application.TanksAppAdapter;
import com.jme3.asset.AssetManager;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import java.beans.PropertyChangeListener;
import model.ITanks;
import view.entity.EGraphics;
import view.entity.GraphicManager;

/**
 *
 * @author perthoresson
 */
public class GameWorld2 implements IGameWorld{
    
        
    private ITanks game;
    
    private Node mapNode;
    private Node mainScene;
    private DirectionalLight sun;
    
    private static final Vector3f LIGHT_DIR = new Vector3f(-4.9236743f, -1.27054665f, 5.896916f);
    
    /**
     * Instantiates the object.
     * Creates a game map
     * 
     * @param game The game to be created
     */
    public GameWorld2(ITanks game) {
        this.game = game;
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public void load() {
        mainScene = new Node("Main Scene");
        TanksAppAdapter.INSTANCE.attachChildToRootNode(mainScene);
        
        AssetManager assetManager = TanksAppAdapter.INSTANCE.getAssetManager();
        sun = new DirectionalLight();
        sun.setDirection(LIGHT_DIR);
        sun.setColor(ColorRGBA.White.clone().multLocal(1.7f));
        TanksAppAdapter.INSTANCE.addLightToRootNode(sun);
        

        
        final Vector3f normalScale = new Vector3f(-1, 1, 1);
        
        //sky.setLocalScale(600);
        

        
        // Load, attach map to root node, and add nodes and geoms in the map to physicsspace
        mapNode = (Node) GraphicManager.INSTANCE.createSpatial(EGraphics.MAP2);
        mapNode.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        TanksAppAdapter.INSTANCE.attachChildToRootNode(mapNode);
        TanksAppAdapter.INSTANCE.addAllToPhysicsSpace(mapNode);
        //TanksAppAdapter.INSTANCE.enableDebug();
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public void cleanup() {
        TanksAppAdapter.INSTANCE.detachAllRootChildren();
        TanksAppAdapter.INSTANCE.removeLightFromRootNode(sun);
    }
    
    
    
}
