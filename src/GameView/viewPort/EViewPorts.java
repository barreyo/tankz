package GameView.viewPort;

import App.TanksAppAdapter;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.post.filters.DepthOfFieldFilter;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.Caps;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.shadow.BasicShadowRenderer;
import com.jme3.shadow.PssmShadowRenderer;

/**
 * Enum holding the different kind of viewports used in Tanks.
 * 
 * @author Daniel
 */
public enum EViewPorts {
    CENTER(0f, 1f, 0f, 1f, "Center"),
    TOP(0f, 1f, 0.5f, 1f, "Top"),
    BOTTOM(0f, 1f, 0f, 0.5f, "Bottom"),
    TOP_LEFT(0f, 0.5f, 0.5f, 1f, "TopLeft"),
    TOP_RIGHT(0.5f, 1f, 0.5f, 1f, "TopRight"),
    BOTTOM_Left(0f, 0.5f, 0f, 0.5f, "BottomLeft"),
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
        
        PssmShadowRenderer pssmRenderer = new PssmShadowRenderer(TanksAppAdapter.INSTANCE.getAssetManager(), 1024, 6);
        pssmRenderer.setDirection(new Vector3f(0.01f, -0.01f, 0.01f).normalizeLocal());
        pssmRenderer.setShadowIntensity(0.35f);
        
        //SSAOFilter ssaoFilter = new SSAOFilter(12.94f, 43.92f, 0.33f, 0.61f);
        //fpp.addFilter(ssaoFilter);
        
        /*DepthOfFieldFilter dof = new DepthOfFieldFilter();
        dof.setFocusDistance(0);
        dof.setFocusRange(100);
        fpp.addFilter(dof);
        
       
        // uncomment this for cartoon, trees gets fucked up though
        if (TanksAppAdapter.INSTANCE.rendererContains(Caps.GLSL100)){
            fpp.addFilter(new CartoonEdgeFilter());
        } */
        
        view.addProcessor(fpp);
        view.addProcessor(pssmRenderer);
    }

    /**
     * Returns the viewport of this enum.
     */
    public ViewPort getViewPort() {
        return view;
    }
}
