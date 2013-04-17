package GameControllers.logic;

import App.TanksAppAdapter;
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
public enum GUIManager {
    INSTANCE;

    private Nifty nifty;
    private final float GUI_WIDTH;
    private final Vector2f powerupSlotHUDPosition;
    
    /**
     *
     */
    private GUIManager() {
        initialiseNifty();
        GUI_WIDTH = TanksAppAdapter.INSTANCE.getSettings().getWidth() * 0.15f;
        powerupSlotHUDPosition = new Vector2f(TanksAppAdapter.INSTANCE.getSettings().getWidth() * 0.2f, 
                TanksAppAdapter.INSTANCE.getSettings().getHeight() * 0.05f);
    }
    
    private void initialiseNifty() {
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(TanksAppAdapter.INSTANCE.getAssetManager(),
                TanksAppAdapter.INSTANCE.getInputManager(), TanksAppAdapter.INSTANCE.getAudioRenderer(), 
                TanksAppAdapter.INSTANCE.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        nifty.enableAutoScaling(1280, 720);
        TanksAppAdapter.INSTANCE.addGuiViewProcessor(niftyDisplay);
        //nifty.setDebugOptionPanelColors(true);
    }
    
    /**
     *
     */
    public void showMainMenu() {
        TanksAppAdapter.INSTANCE.attachAppState(TanksAppStateFactory.getAppState(MenuAppState.class));
    }

    /**
     *
     */
    public void showLoadingScreen() {
        TanksAppAdapter.INSTANCE.attachAppState(TanksAppStateFactory.getAppState(LoadingScreenAppState.class));
    }
    
    /**
     *
     */
    public void showPauseMenu() {
        TanksAppAdapter.INSTANCE.attachAppState(TanksAppStateFactory.getAppState(PauseMenuAppState.class));
    }
    
    /**
     *
     * @return
     */
    public Nifty getNifty() {
        return nifty;
    }
    
    /**
     *
     * @param fileName
     * @param color
     * @param index
     */
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
        border.setImage(TanksAppAdapter.INSTANCE.getAssetManager(), "Interface/inventoryBorder.png", true);

        border.setWidth(GUI_WIDTH);
        border.setHeight(GUI_WIDTH);

        border.setPosition(position.getX(), position.getY());
        TanksAppAdapter.INSTANCE.attachChildToGUINode(border);
    }

    /**
     *
     */
    public void createUIProducts() {

       // String[] fileNames = {"appleImg.png", "appleImg.png", "appleImg.png"};
       // ColorRGBA[] colors = {ColorRGBA.Pink, ColorRGBA.Green, ColorRGBA.Red};

        for (int i = 0, length = 3; i < length; i++) {

            Vector2f position = powerupSlotHUDPosition.clone();
            position.setX(position.getX() + (GUI_WIDTH * i) + (TanksAppAdapter.INSTANCE.getSettings().getWidth() * 0.08f * i));
            createBorder(position);
        }

        TanksAppAdapter.INSTANCE.attachChildToGUINode(products);
    }

    // between 0 and 2
    /**
     *
     * @param image
     * @param index
     */
    public void setImageToIndex(Picture image, int index) {

        if (index < 0) {
            index = 0;
        } else if (index > 2) {
            index = 2;
        }

        // find the position to put the image
    }
}
