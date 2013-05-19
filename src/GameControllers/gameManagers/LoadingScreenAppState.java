package GameControllers.gameManagers;

import application.TanksAppAdapter;
import model.EApplicationState;
import GameView.Sounds.ESounds;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;

/**
 * Loading screen app state, singleton.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class LoadingScreenAppState extends AbstractAppState {

    private static LoadingScreenAppState instance;
    private Nifty nifty;
    private int frameCount;
    private static final int FRAME_COUNT = 50;
    private GameAppState gameApp;

    private LoadingScreenAppState() {
        // Nifty GUI processor.
        nifty = GUIManager.INSTANCE.getNifty();
    }

    /**
     * Returns the instance to this singleton.
     * 
     * @return instance.
     */
    public static synchronized LoadingScreenAppState getInstance() {
        if (instance == null) {
            instance = new LoadingScreenAppState();
        }
        return instance;
    }

    /**
     * Show the loading screen, go to the loading screen nifty screen.
     */
    private void showLoadingScreen() {
        nifty.gotoScreen("loadingScreen");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        super.stateAttached(stateManager);
        showLoadingScreen();
        EApplicationState.setGameState(EApplicationState.LOADING_MAP);
        frameCount = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        nifty.gotoScreen("end");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        if (frameCount == 0) {
            TanksAppAdapter.INSTANCE.setCursorVisible(false);
            System.out.println("LOADING GAME");
        } else if (frameCount == 20) {
            SoundManager.INSTANCE.load();
        } else if (frameCount == 21) {
            PhysicsManager.INSTANCE.load();
        } else if (frameCount == 22) {
            ViewPortManager.INSTANCE.load();
        } else if (frameCount == 23) {
            GraphicManager.INSTANCE.load();
        } else if (frameCount == 24) {
            EffectsManager.INSTANCE.load();
        } else if (frameCount == 25) {
            gameApp = GameMapManager.INSTANCE.load(GameMapManager.INSTANCE.getCurrentIntMap());
        } else if (frameCount == FRAME_COUNT) {
            //at end of loading
            TanksAppAdapter.INSTANCE.attachAppState(gameApp);
            TanksAppAdapter.INSTANCE.detachAppState(this);
            SoundManager.INSTANCE.stop(ESounds.MENU_SOUND);

            EApplicationState.setGameState(EApplicationState.RUNNING);
            System.out.println("Finished loading game");
        }
        frameCount++;
    }
}
