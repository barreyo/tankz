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
import com.jme3.scene.Node;
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
    
    public Nifty getNifty() {
        return nifty;
    }
    
    public void addPowerupItem(String fileName, ColorRGBA color, int index) {
        /* TODO To be implemented
        Picture p = null;
        p = new Picture("hudPic");
        p.setImage(app.getAssetManager(), fileName, true);
        p.setWidth(GUI_WIDTH);
        p.setHeight(GUI_WIDTH);

        p.getMaterial().setColor("Color", color);

        Vector2f position = powerupSlotHUDPosition.clone();
        position.setX(position.getX() + (GUI_WIDTH * index) + (app.getSettings().getWidth() * 0.08f * index));
        p.setPosition(position.getX(), position.getY());
        createBorder(position);

        products.attachChild(p);
        myApp.getGuiNode().attachChild(products);*/
    }
    
    private Picture border;

    private Node products = new Node("products");

    private void createBorder(Vector2f position) {
        border = new Picture("border");
        border.setImage(app.getAssetManager(), "Interface/inventoryBorder.png", true);

        border.setWidth(GUI_WIDTH);
        border.setHeight(GUI_WIDTH);

        border.setPosition(position.getX(), position.getY());
        app.getGuiNode().attachChild(border);
    }

    public void createUIProducts() {

       // String[] fileNames = {"appleImg.png", "appleImg.png", "appleImg.png"};
       // ColorRGBA[] colors = {ColorRGBA.Pink, ColorRGBA.Green, ColorRGBA.Red};

        for (int i = 0, length = 3; i < length; i++) {

            Vector2f position = powerupSlotHUDPosition.clone();
            position.setX(position.getX() + (GUI_WIDTH * i) + (app.getSettings().getWidth() * 0.08f * i));
            createBorder(position);
        }

        app.getGuiNode().attachChild(products);
    }

    // between 0 and 2
    public void setImageToIndex(Picture image, int index) {

        if (index < 0) {
            index = 0;
        } else if (index > 2) {
            index = 2;
        }

        // find the position to put the image
    }
    
    

    public void load(int level) {
        //load any nifty effects and uiImages on the screen
    }

    public void cleanup() {
       app.getGuiNode();
    }

}
