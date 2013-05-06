package GameView.GUI;

import App.TanksAppAdapter;
import GameModel.ApplicationSettings;
import GameModel.IPlayer;
import GameModel.Player;
import com.jme3.bounding.BoundingVolume;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Barre
 */
public class FloatingNameViewControl extends AbstractControl {

    private IPlayer player;
    private ViewPort vp;
    private BitmapText text;
    private List<IPlayer> players;
    
    private FloatingNameViewControl() {} // Disable zero parameter instantiation
    
    /**
     *
     * @param player
     * @param vp
     */
    public FloatingNameViewControl(IPlayer player, ViewPort vp) {
//        this.player = player;
//        this.vp = vp;
//        this.fillPlayersList();
//        BitmapFont font = TanksAppAdapter.INSTANCE.getAssetManager().loadFont("Interface/Fonts/loadingFont.fnt");
//        text = new BitmapText(font, false);
//        // Initial setup of the text
//        text.setText("SpelareJHEJE");
//        text.setColor(ColorRGBA.Magenta);
//        text.setSize(1.0f);
        
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        // Update text location
        //System.out.println("DEBUG");
        //text.setLocalTranslation(this.getScreenCoordinates().x, this.getScreenCoordinates().y, 1);
    }

    /**
     *
     * @param rm
     * @param vp
     */
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        // Not in use
    }
        
    /**
     *
     * @param spatial
     * @return
     */
    public Control cloneForSpatial(Spatial spatial) {
        // Not in use
        return null;
    }
    
    private void fillPlayersList() {
        players = new ArrayList<IPlayer>();
        /*for (IPlayer p : ApplicationSettings.INSTANCE.getPlayerNames()) {
            if (!p.equals(player) ) {
                players.add(p);
            }
        }*/
    }
    
    private boolean checkIfInViewPort(Spatial spatial) {
        BoundingVolume bv = spatial.getWorldBound();
        int planeState = vp.getCamera().getPlaneState();
        // Set planestate to 0 so the test is possible
        vp.getCamera().setPlaneState(0);
        Camera.FrustumIntersect res = vp.getCamera().contains(bv);
        // Return the planestate to the original value
        vp.getCamera().setPlaneState(planeState);
        return res == Camera.FrustumIntersect.Intersects;
    }
}
