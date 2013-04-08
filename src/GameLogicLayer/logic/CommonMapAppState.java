package GameLogicLayer.logic;

import GameModelLayer.Game.EGameState;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.post.filters.DepthOfFieldFilter;
import com.jme3.post.filters.LightScatteringFilter;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.util.SkyFactory;

/**
 * An appstate holding common functionality and visuality for different maps.
 * 
 * @author Daniel
 */
public class CommonMapAppState extends AbstractAppState {
  
    private TanksGame app;
    private Node rootNode;
    private AssetManager assetManager;
    private Vector3f lightDir = new Vector3f(-4.9236743f, -1.27054665f, 5.896916f);
    
    /**
     * Creates a new common map appstate.
     */
    public CommonMapAppState() {
        app = TanksGame.getApp();
        rootNode = app.getRootNode();
        assetManager = app.getAssetManager();
        loadCommon();
    }

    /**
     * Loads the common functionality and visuality of maps.
     */
    private void loadCommon() {
        
        Node mainScene = new Node("Main Scene");
        rootNode.attachChild(mainScene);
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(lightDir);
        sun.setColor(ColorRGBA.White.clone().multLocal(1.7f));
        rootNode.addLight(sun);
        
        Spatial sky = SkyFactory.createSky(assetManager, "Scenes/FullskiesSunset0068.dds", false);
        sky.setLocalScale(350);
        
        mainScene.attachChild(sky);
        
        FilterPostProcessor fpp = app.getFpp();
        /* Disabled for performance atm
        BloomFilter bloom = new BloomFilter();
        
        bloom.setExposurePower(55);
        bloom.setBloomIntensity(1.0f);
        
        fpp.addFilter(bloom);
        
        LightScatteringFilter lsf = new LightScatteringFilter(lightDir.mult(-300));
        lsf.setLightDensity(1.0f);
        fpp.addFilter(lsf);*/
        
        DepthOfFieldFilter dof = new DepthOfFieldFilter();
        dof.setFocusDistance(0);
        dof.setFocusRange(100);
        fpp.addFilter(dof);
        
        app.getViewPort().addProcessor(fpp);
    }
    
    /**
     *  
     * @param stateManager
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        super.stateAttached(stateManager);
    }
    
    /**
     *
     * @param stateManager
     * @param app
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
    }
    
    /**
     *
     * @param stateManager
     */
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
    }
    
    @Override
    public void update(float tpf) {
        if (EGameState.getGameState() == EGameState.PAUSED) {
            return;
        }
    }
}
