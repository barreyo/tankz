package GameControllers.logic;

import App.TanksAppAdapter;
import GameModel.ITanks;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;

/**
 *
 * @author Daniel
 */
public enum GUIManager {
    INSTANCE;

    private Nifty nifty;
    
    /**
     *
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
     *
     */
    public void showMainMenu() {
        TanksAppAdapter.INSTANCE.attachAppState(MenuAppState.getInstance());
    }

    /**
     *
     */
    public void showLoadingScreen() {
        TanksAppAdapter.INSTANCE.attachAppState(LoadingScreenAppState.getInstance());
    }
    
    /**
     *
     */
    public void showPauseMenu(ITanks gameModel) {
        PauseMenuAppState.getInstance().setGameToPause(gameModel);
        TanksAppAdapter.INSTANCE.attachAppState(PauseMenuAppState.getInstance());
    }
    
    /**
     *
     * @return
     */
    public Nifty getNifty() {
        return nifty;
    }
    
    public void cleanup() {
        TanksAppAdapter.INSTANCE.detachAllGUIChildren();
    }
}
