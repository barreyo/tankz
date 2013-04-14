package GameView.viewPort;

import App.TanksApp;
import com.jme3.renderer.Camera;
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
        TanksApp app = TanksApp.getApp();
        Camera cam = app.getCamera().clone();
        cam.setViewPort(left, right, bottom, top);
        
        view = app.getRenderManager().createMainView(loc, cam);
        view.setClearFlags(true, true, true);
        view.setEnabled(false);
    }

    /**
     * Returns the viewport of this enum.
     */
    public ViewPort getViewPort() {
        return view;
    }
}
