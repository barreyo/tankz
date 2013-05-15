package GameControllers.logic;

import App.TanksAppAdapter;
import GameModel.ITanks;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Spatial;
import de.lessvoid.nifty.Nifty;
import java.util.List;

/**
 *
 * @author Daniel
 */
public enum GUIManager {
    /**
     * Get access to the GUI manager through this singleton.
     */
    INSTANCE;

    private Nifty nifty;
    private List<Spatial> hudElements;
    
    /**
     * A manager that controls the GUI.
     */
    private GUIManager() {
        initialiseNifty();
    }
    
    private void initialiseNifty() {
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(TanksAppAdapter.INSTANCE.getAssetManager(),
                TanksAppAdapter.INSTANCE.getInputManager(), TanksAppAdapter.INSTANCE.getAudioRenderer(), 
                TanksAppAdapter.INSTANCE.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        nifty.enableAutoScaling(1280, 720);
        TanksAppAdapter.INSTANCE.addGuiViewProcessor(niftyDisplay);
    }
    
    /**
     * Show main menu of the game.
     */
    public void showMainMenu() {
        TanksAppAdapter.INSTANCE.attachAppState(MenuAppState.getInstance());
    }

    /**
     * Show the loading screen.
     */
    public void showLoadingScreen() {
        TanksAppAdapter.INSTANCE.attachAppState(LoadingScreenAppState.getInstance());
    }
    
    /**
     * Show the ingame pause menu.
     * 
     * @param gameModel gameModel that is in use.
     */
    public void showPauseMenu(ITanks gameModel) {
        PauseMenuAppState.getInstance().setGameToPause(gameModel);
        hudElements = TanksAppAdapter.INSTANCE.getGuiChildren();
        TanksAppAdapter.INSTANCE.detachAllGUIChildren(); // Hide all HUD components
        TanksAppAdapter.INSTANCE.attachAppState(PauseMenuAppState.getInstance());
    }
    
    /**
     * Return the current instance of Nifty GUI library.
     * 
     * @return nifty.
     */
    public Nifty getNifty() {
        return nifty;
    }
    
    /**
     * Clean the GUI node.
     */
    public void cleanup() {
        TanksAppAdapter.INSTANCE.detachAllGUIChildren();
    }
    
    /**
     * Returns the HUD elements last attached to the guiNode since last paus.
     * 
     * @return hud elements attached to guiNode.
     */
    public List<Spatial> getHudElements() {
        return hudElements;
    }
}
