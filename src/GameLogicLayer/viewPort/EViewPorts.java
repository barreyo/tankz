package GameLogicLayer.viewPort;

import GameLogicLayer.Game.TanksGame;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;

/**
 *
 * @author Daniel
 */
public enum EViewPorts {
    VIEW1(0f, 1f, 0f, 0.5f, "Bottom"),
    VIEW2(0f, 1f, 0.5f, 1f, "Top");
    //VIEW3,
    //VIEW4;
    
    private ViewPort view;
    
    private EViewPorts(float left, float right, float bottom, float top, String loc) {
        TanksGame app = TanksGame.getApp();
        Camera cam = app.getCamera().clone();
        cam.setViewPort(left, right, bottom, top);
   
        view = app.getRenderManager().createMainView(loc, cam);
        view.setClearFlags(true, true, true);
    }
    
    public ViewPort getViewPort() {
        return view;
    }
}
