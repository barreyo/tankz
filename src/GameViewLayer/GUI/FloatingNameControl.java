/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.GUI;

import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.viewPort.EViewPorts;
import com.jme3.asset.AssetManager;
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

/**
 *
 * @author Barre
 */
public class FloatingNameControl extends AbstractControl {

    private Spatial player;
    private ViewPort vp;
    private BitmapText text;
    
    private FloatingNameControl() {} // Disable zero parameter instantiation
    
    public FloatingNameControl(Spatial player, AssetManager assetManager) {
        this.player = player;
        this.vp = EViewPorts.VIEW1.getViewPort();
        BitmapFont font = assetManager.loadFont("Interface/Fonts/loadingFont.fnt");
        text = new BitmapText(font, false);
        // Initial setup of the text
        text.setText("SpelareJHEJE");
        text.setColor(ColorRGBA.Magenta);
        text.setSize(1.0f);
//        text.setLocalTranslation(this.getScreenCoordinates().x, this.getScreenCoordinates().y, 1);
        text.setLocalTranslation(0.0f, 0.0f, 1);
        TanksGame.getApp().getGuiNode().attachChild(text);
        //vp.attachScene(text);
        System.out.println("TJENARE");
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        // Update text location
        System.out.println("DEBUG");
        //text.setLocalTranslation(this.getScreenCoordinates().x, this.getScreenCoordinates().y, 1);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        // Not in use
    }
        
    public Control cloneForSpatial(Spatial spatial) {
        // Not in use
        return null;
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
    
    private Vector2f getScreenCoordinates() {
        // Calculate the plane to project onto
        float f = player.getWorldTranslation().z - vp.getCamera().getLocation().z;
        float x = ((player.getWorldTranslation().x - vp.getCamera().getLocation().x) * 
                (f / player.getWorldTranslation().z)) + vp.getCamera().getLocation().x;
        float y = ((player.getWorldTranslation().y - vp.getCamera().getLocation().y) * 
                (f / player.getWorldTranslation().z)) + vp.getCamera().getLocation().y;
        return new Vector2f(x,y);
    }
}
