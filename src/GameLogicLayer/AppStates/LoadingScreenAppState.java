/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.AppStates;

import GameLogicLayer.GUI.GUIManager;
import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.Map.GameMapManager;
import GameModelLayer.Game.GameState;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 * @author Daniel
 */
public class LoadingScreenAppState extends AbstractAppState implements ScreenController {

    private AppStateManager stateManager;
    private TanksGame app;
    private GUIManager guiManager;
    private GameMapManager mapManager;
    private Nifty nifty;
   
    private int frameCount;
    
    private static final int FRAME_COUNT = 150;
    
    private boolean isLoaded = false;
    
    public LoadingScreenAppState() {
        // Get managers
        app = TanksGame.getApp();
        guiManager = app.getGUIManager();
        mapManager = app.getMapManager();
        nifty = guiManager.getNifty();

        // Register as a screen controller and add screen to Hud handler Nifty
        nifty.registerScreenController(this);
        nifty.addXml("Interface/Nifty/LoadingScreen.xml");
    }
    
    public void showLoadingScreen() {
        nifty.gotoScreen("loadingScreen");
    }
    
    @Override
    public void stateAttached(AppStateManager stateManager) {
        this.stateManager = stateManager;
        GameState.setGameState(GameState.LOADING_MAP);
        frameCount = 0;
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager) {
        isLoaded = true;
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
    }
    
    @Override
    public void update(float tpf) {

        if (frameCount == 1) {
            //load the screen
            showLoadingScreen();
        } else if (frameCount == FRAME_COUNT) { //using 150 as a debug, this is where you load the game

            //at end of loading
            stateManager.detach(this);

            app.getInputManager().setCursorVisible(false);
            
            mapManager.load(mapManager.getCurrentIntMap());
            stateManager.attach(app.getTanksAppStateManager().getAppState(GameAppState.class));
        }
        frameCount++;
    }
    
    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen");
    }

    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen");
    }

    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("bind( " + screen.getScreenId() + ")");
    }
}
