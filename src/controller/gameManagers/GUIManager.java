package controller.gameManagers;

import application.TanksAppAdapter;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;

/**
 * Manger for all GUI elements, singleton.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum GUIManager {
    
    /**
     * Get access to the GUI manager through this singleton.
     */
    INSTANCE;

    private Nifty nifty;
    
    /**
     * A manager that controls the GUI.
     */
    private GUIManager() {
        initialiseNifty();
    }
    
    // init all Nifty relate objects.
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
    void showPauseMenu(GameAppState gameApp) {
        PauseMenuAppState.getInstance().setGameToPause(gameApp);
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
}
