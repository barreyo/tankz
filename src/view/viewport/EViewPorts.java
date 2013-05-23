package view.viewport;

import application.TanksAppAdapter;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.Caps;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.shadow.DirectionalLightShadowRenderer;

/**
 * Enum holding the different kind of viewports used in Tanks.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum EViewPorts {

    /**
     * Center viewport
     */
    CENTER(0f, 1f, 0f, 1f, "Center"),
    
    /**
     * Top viewport
     */
    TOP(0f, 1f, 0.5f, 1f, "Top"),
    
    /**
     * Bottom viewport
     */
    BOTTOM(0f, 1f, 0f, 0.5f, "Bottom"),
    
    /**
     * Top left viewport
     */
    TOP_LEFT(0f, 0.5f, 0.5f, 1f, "TopLeft"),
    
    /**
     * Top right viewport
     */
    TOP_RIGHT(0.5f, 1f, 0.5f, 1f, "TopRight"),
    
    /**
     * Bottom left viewport
     */
    BOTTOM_Left(0f, 0.5f, 0f, 0.5f, "BottomLeft"),
    
    /**
     * Bottom right viewport
     */
    BOTTOM_RIGHT(0.5f, 1f, 0f, 0.5f, "BottomRight");
    
    private ViewPort view;
    private String loc;
    private float left, right, bottom, top;

    private EViewPorts(float left, float right, float bottom,
            float top, String loc) {
        this.loc = loc;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }

    /**
     * Returns the viewport of this enum.
     *
     * @return the ViewPort
     */
    public ViewPort getViewPort() {
        if (view == null) {
            Camera cam = TanksAppAdapter.INSTANCE.getCamera();
            cam.setViewPort(left, right, bottom, top);
            view = TanksAppAdapter.INSTANCE.createMainView(loc, cam);
            view.setClearFlags(true, true, true);
            view.setEnabled(false);
            
            // Reset shadow mode for all root node instances.
            TanksAppAdapter.INSTANCE.getRootNode().setShadowMode(ShadowMode.Off);

            FilterPostProcessor fpp = new FilterPostProcessor(TanksAppAdapter.INSTANCE.getAssetManager());

            // Used for shadow direction, should maybe use the sun object in map.
            DirectionalLight light = new DirectionalLight();
            light.setDirection(new Vector3f(-.5f, -.5f, -.5f).normalizeLocal());
            
            // Create real time shadows.
            DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(TanksAppAdapter.INSTANCE.getAssetManager(), 1024, 4);
            dlsr.setLight(light);
            dlsr.setLambda(0.9f);
            dlsr.setShadowIntensity(0.35f);
            view.addProcessor(dlsr);

            // Toon edges for all world objects.
            if (TanksAppAdapter.INSTANCE.rendererContains(Caps.GLSL100)) {
                CartoonEdgeFilter cartoonFilter = new CartoonEdgeFilter();
                cartoonFilter.setEdgeWidth(0.6f);
                cartoonFilter.setDepthSensitivity(20);
                fpp.addFilter(cartoonFilter);
            }

            // filter for shadows.
            view.addProcessor(fpp);
        }
        return view;
    }
    
    public void cleanup() {
       view = null; 
    }
}
