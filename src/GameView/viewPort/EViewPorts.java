package GameView.viewPort;

import GameUtilities.TankAppAdapter;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.post.filters.DepthOfFieldFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.Caps;
import com.jme3.renderer.ViewPort;

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
        Camera cam = TankAppAdapter.INSTANCE.getCamera();
        cam.setViewPort(left, right, bottom, top);
        
        view = TankAppAdapter.INSTANCE.createMainView(loc, cam);
        view.setClearFlags(true, true, true);
        view.setEnabled(false);
        
        FilterPostProcessor fpp = new FilterPostProcessor(TankAppAdapter.INSTANCE.getAssetManager());
        
        /*
        DepthOfFieldFilter dof = new DepthOfFieldFilter();
        dof.setFocusDistance(0);
        dof.setFocusRange(100);
        fpp.addFilter(dof);
        
       
        // uncomment this for cartoon, trees gets fucked up though
        if (TankAppAdapter.INSTANCE.rendererContains(Caps.GLSL100)){
            fpp.addFilter(new CartoonEdgeFilter());
        }*/
        
        view.addProcessor(fpp);
    }

    /**
     * Returns the viewport of this enum.
     */
    public ViewPort getViewPort() {
        return view;
    }
}
