package GameControllers.logic;

import App.TanksAppAdapter;
import GameModel.EApplicationState;
import GameView.Sounds.ESounds;
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
public final class LoadingScreenAppState extends AbstractAppState {

    private static LoadingScreenAppState instance;
    private Nifty nifty;
    private int frameCount;
    private static final int FRAME_COUNT = 50;

    /**
     *
     */
    private LoadingScreenAppState() {
        // Get managers
        nifty = GUIManager.INSTANCE.getNifty();
    }

    /**
     *
     * @return
     */
    public static synchronized LoadingScreenAppState getInstance() {
        if (instance == null) {
            instance = new LoadingScreenAppState();
        }
        return instance;
    }

    /**
     *
     */
    private void showLoadingScreen() {
        nifty.gotoScreen("loadingScreen");
    }

    /**
     *
     * @param stateManager
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        super.stateAttached(stateManager);
        showLoadingScreen();
        EApplicationState.setGameState(EApplicationState.LOADING_MAP);
        frameCount = 0;
    }

    /**
     *
     * @param stateManager
     */
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        nifty.gotoScreen("end");
    }

    /**
     *
     * @param stateManager
     * @param app
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
    }

    @Override
    public void update(float tpf) {
        if (frameCount == FRAME_COUNT) { //using 150 as a debug, this is where you load the game

            TanksAppAdapter.INSTANCE.setCursorVisible(false);

            GameMapManager.INSTANCE.load(GameMapManager.INSTANCE.getCurrentIntMap());

            //at end of loading
            TanksAppAdapter.INSTANCE.detachAppState(this);
            SoundManager.INSTANCE.stop(ESounds.MENU_SOUND);

            EApplicationState.setGameState(EApplicationState.RUNNING);
            System.out.println("Finished loading game");
        }
        frameCount++;
    }
}
