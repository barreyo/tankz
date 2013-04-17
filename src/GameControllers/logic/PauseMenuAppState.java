package GameControllers.logic;

import GameModel.Game.EGameState;
import App.TanksAppAdapter;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector2f;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMouseMovedEvent;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.spi.sound.SoundHandle;
import de.lessvoid.nifty.tools.Color;

/**
 * An app state representing the pause menu.
 *
 * @author Daniel
 */
public class PauseMenuAppState extends AbstractAppState implements ScreenController {
    
    private Nifty nifty;
    private Element currentElement;
    private SoundHandle sound;
    private Vector2f cursorPosition;
    
    private NiftyJmeDisplay niftyDisplay;

    /**
     *  Create a pause menu app state.
     */
    public PauseMenuAppState() {
        niftyDisplay = new NiftyJmeDisplay(TanksAppAdapter.INSTANCE.getAssetManager(),
                      TanksAppAdapter.INSTANCE.getInputManager(), TanksAppAdapter.INSTANCE.getAudioRenderer(), TanksAppAdapter.INSTANCE.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        nifty.registerScreenController(this);
        nifty.fromXml("Interface/Nifty/PauseMenu.xml", "pause", this);

        nifty.getSoundSystem().addSound("hooverSound", "Sounds/click.ogg");
        sound = nifty.getSoundSystem().getSound("hooverSound");
    }

    /**
     *
     * @param stateManager
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        super.stateAttached(stateManager);

    }

    /**
     *
     * @param stateManager
     */
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        removeDesktopInputs();
        TanksAppAdapter.INSTANCE.removeGuiViewProcessor(niftyDisplay);
    }

    /**
     *
     * @param stateManager
     * @param app
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        EGameState.setGameState(EGameState.PAUSED);

        showPauseMenu();

        loadDesktopInputs();
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }

    private void loadDesktopInputs() {
        // add mappings
    }

    @Override
    public void update(float tpf) {
        //update loop
    }

    private void removeDesktopInputs() {
        //remove mappings
    }

    // ==== nifty ====
    /**
     *
     * @param id
     * @param event
     */
    @NiftyEventSubscriber(pattern = "pause_.*")
    public void onHover(String id, NiftyMouseMovedEvent event) {

        if (currentElement == null) { //initial element
            if (event.getElement().getRenderer(TextRenderer.class) != null) {
                currentElement = event.getElement();

                // hover
                TextRenderer renderer1 = currentElement.getRenderer(TextRenderer.class);
                renderer1.setColor(Color.BLACK);

                sound.play();
            }
        } else {
            if (event.getElement() != currentElement) {
                currentElement.getRenderer(TextRenderer.class).setColor(Color.WHITE);
                currentElement = null;
            }
        }
    }

    /**
     *
     * @param nifty
     * @param screen
     */
    public void bind(Nifty nifty, Screen screen) {
    }

    /**
     *
     */
    public void onStartScreen() {
    }

    /**
     *
     */
    public void onEndScreen() {
    }

    /**
     *
     */
    public void showPauseMenu() {
        // attach the nifty display to the gui view port as a processor
        TanksAppAdapter.INSTANCE.addGuiViewProcessor(niftyDisplay);
        TanksAppAdapter.INSTANCE.setCursorVisible(true);
        nifty.gotoScreen("pause");
    }

    /**
     *
     */
    public void resume() {
        EGameState.setGameState(EGameState.RUNNING);
        TanksAppAdapter.INSTANCE.setCursorVisible(false);
        TanksAppAdapter.INSTANCE.detachAppState(this);
    }

    /**
     *
     */
    public void restart() {
        EGameState.setGameState(EGameState.RUNNING);
        GameMapManager.INSTANCE.restartMap();
        TanksAppAdapter.INSTANCE.detachAppState(this);
        /* remove this later */
        //app.getStateManager().attach(app.getTanksAppStateManager().getAppState(LoadingScreenAppState.class));
    }

    /**
     *
     */
    public void showMainMenu() {
    }

    /**
     *
     */
    public void exit() {
        TanksAppAdapter.INSTANCE.stop();
    }
}
