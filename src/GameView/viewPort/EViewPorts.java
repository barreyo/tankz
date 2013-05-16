package GameView.viewPort;

import App.TanksAppAdapter;
import GameUtilities.Constants;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.post.filters.DepthOfFieldFilter;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.Caps;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.EdgeFilteringMode;

/**
 * Enum holding the different kind of viewports used in Tanks.
 * 
 * @author Daniel
 */
public enum EViewPorts {
    /**
     *
     */
    CENTER(0f, 1f, 0f, 1f, "Center"),
    /**
     *
     */
    TOP(0f, 1f, 0.5f, 1f, "Top"),
    /**
     *
     */
    BOTTOM(0f, 1f, 0f, 0.5f, "Bottom"),
    /**
     *
     */
    TOP_LEFT(0f, 0.5f, 0.5f, 1f, "TopLeft"),
    /**
     *
     */
    TOP_RIGHT(0.5f, 1f, 0.5f, 1f, "TopRight"),
    /**
     *
     */
    BOTTOM_Left(0f, 0.5f, 0f, 0.5f, "BottomLeft"),
    /**
     *
     */
    BOTTOM_RIGHT(0.5f, 1f, 0f, 0.5f, "BottomRight");
    
    private final ViewPort view;
    
    private EViewPorts(float left, float right, float bottom, float top, String loc) {
        Camera cam = TanksAppAdapter.INSTANCE.getCamera();
        cam.setViewPort(left, right, bottom, top);
        
        view = TanksAppAdapter.INSTANCE.createMainView(loc, cam);
        view.setClearFlags(true, true, true);
        view.setEnabled(false);
       
        TanksAppAdapter.INSTANCE.getRootNode().setShadowMode(ShadowMode.Off);
        
        FilterPostProcessor fpp = new FilterPostProcessor(TanksAppAdapter.INSTANCE.getAssetManager());
        
        DirectionalLight light = new DirectionalLight();
        light.setDirection(new Vector3f(-.5f,-.5f,-.5f).normalizeLocal());
        
//        DirectionalLightShadowFilter dlsf = new DirectionalLightShadowFilter(TanksAppAdapter.INSTANCE.getAssetManager(), 1024, 4);
//        dlsf.setLight(light);
//        dlsf.setLambda(0.55f);
//        dlsf.setShadowIntensity(0.6f);        
//        dlsf.setEdgeFilteringMode(EdgeFilteringMode.Nearest);
//        
//        fpp.addFilter(dlsf);
        
        DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(TanksAppAdapter.INSTANCE.getAssetManager(), 1024, 4);
        dlsr.setLight(light);
        dlsr.setLambda(0.9f);
        dlsr.setShadowIntensity(0.35f);
        view.addProcessor(dlsr);
        
        /*DepthOfFieldFilter dof = new DepthOfFieldFilter();
        dof.setFocusDistance(0);
        dof.setFocusRange(100);
        fpp.addFilter(dof);*/
      
        // uncomment this for cartoon, trees gets fucked up though
        if (TanksAppAdapter.INSTANCE.rendererContains(Caps.GLSL100)){
            CartoonEdgeFilter cartoonFilter = new CartoonEdgeFilter();
            cartoonFilter.setEdgeWidth(0.6f);
            cartoonFilter.setDepthSensitivity(20);
            fpp.addFilter(cartoonFilter);
        } 
        
        view.addProcessor(fpp);
    }

    /**
     * Returns the viewport of this enum.
     * @return 
     */
    public ViewPort getViewPort() {
        return view;
    }
}
