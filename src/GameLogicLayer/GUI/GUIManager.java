/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.GUI;

import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.util.Manager;
import com.jme3.app.state.AppStateManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;

/**
 *
 * @author Daniel
 */
public class GUIManager implements Manager {
    private TanksGame myApp;
    private AppStateManager stateManager;
    private Nifty nifty;
    
    public GUIManager() {
        myApp = TanksGame.getApp();

        initialiseNifty();
        stateManager = myApp.getStateManager();
    }
    
    private void initialiseNifty() {
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(myApp.getAssetManager(),
        myApp.getInputManager(),
        myApp.getAudioRenderer(),
        myApp.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        nifty.enableAutoScaling(1280, 720);
        myApp.getGuiViewPort().addProcessor(niftyDisplay);
        //nifty.setDebugOptionPanelColors(true);
    }

    public void load(int level) {
        //load any nifty effects and uiImages on the screen
    }

    public void cleanup() {
       myApp.getGuiNode();
    }

}
