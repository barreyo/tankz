/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.GUI;

import GameLogicLayer.AppStates.LoadingScreenAppState;
import GameLogicLayer.AppStates.MenuAppState;
import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.util.Manager;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.ui.Picture;
import de.lessvoid.nifty.Nifty;

/**
 *
 * @author Daniel
 */
public class GUIManager implements Manager {
    private TanksGame app;
    private AppStateManager stateManager;
    private Nifty nifty;
    private final float GUI_WIDTH;
    private final Vector2f powerupSlotHUDPosition;
    
    public GUIManager() {
        app = TanksGame.getApp();
        initialiseNifty();
        stateManager = app.getStateManager();
        GUI_WIDTH = app.getSettings().getWidth() * 0.15f;
        powerupSlotHUDPosition = new Vector2f(
            app.getSettings().getWidth() * 0.2f, app.getSettings().getHeight() * 0.05f);
    }
    
    private void initialiseNifty() {
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(app.getAssetManager(),
        app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        nifty.enableAutoScaling(1280, 720);
        app.getGuiViewPort().addProcessor(niftyDisplay);
        //nifty.setDebugOptionPanelColors(true);
    }
    
    public void showMainMenu() {
        stateManager.attach(app.getTanksAppStateManager().getAppState(MenuAppState.class));
    }

    public void showLoadingScreen() {
        stateManager.attach(app.getTanksAppStateManager().getAppState(LoadingScreenAppState.class));
    }
    
    public void addPowerupItem(String fileName, ColorRGBA color, int index) {
        /* WIll be implemented later
        Picture p = null;
        p = new Picture("hudPic");
        p.setImage(myApp.getAssetManager(), fileName, true);
        p.setWidth(WIDTH);
        p.setHeight(WIDTH);

        p.getMaterial().setColor("Color", color);

        Vector2f position = initialFoodHUDPosition.clone();
        position.setX(position.getX() + (WIDTH * index) + (myApp.getSettings().getWidth() * 0.08f * index));
        p.setPosition(position.getX(), position.getY());
        createBorder(position);

        products.attachChild(p);
        myApp.getGuiNode().attachChild(products);*/
    }
    
    

    public void load(int level) {
        //load any nifty effects and uiImages on the screen
    }

    public void cleanup() {
       app.getGuiNode();
    }

}
