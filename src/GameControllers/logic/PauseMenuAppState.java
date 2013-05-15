package GameControllers.logic;

import GameModel.EApplicationState;
import App.TanksAppAdapter;
import GameModel.ITanks;
import GameUtilities.Commands;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector2f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Spatial;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMouseMovedEvent;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.spi.sound.SoundHandle;
import de.lessvoid.nifty.tools.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * An app state representing the pause menu.
 *
 * @author Daniel
 */
public class PauseMenuAppState extends AbstractAppState implements ScreenController {
    private static PauseMenuAppState instance;
    
    private Nifty nifty;
    private Element currentElement;
    private SoundHandle sound;
    private Vector2f cursorPosition;
    private ITanks gameModel;
    
    private NiftyJmeDisplay niftyDisplay;
    
    /**
     *  Create a pause menu app state.
     */
    private PauseMenuAppState() {
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
     * @return
     */
    public static synchronized PauseMenuAppState getInstance() {
        if (instance == null) {
            instance = new PauseMenuAppState();
        }
        return instance;
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

        showPauseMenu();
    }

    @Override
    public void cleanup() {
        super.cleanup();
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
                
                if (!SoundManager.INSTANCE.isSoundFXMuted()) {
                    sound.play();
                }
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
        EApplicationState.setGameState(EApplicationState.RUNNING);
        gameModel.resumeGame();
        TanksAppAdapter.INSTANCE.setCursorVisible(false);
        TanksAppAdapter.INSTANCE.detachAppState(this);
        List<Spatial> hudElements = GUIManager.INSTANCE.getHudElements();
        for (Spatial spatial : hudElements) {
            TanksAppAdapter.INSTANCE.attachChildToGUINode(spatial);
        }
    }
    
    /**
     * 
     */
    public void menu() {
        EApplicationState.setGameState(EApplicationState.MAIN_MENU);
        TanksAppAdapter.INSTANCE.detachAppState(this);
        GUIManager.INSTANCE.showMainMenu();
    }

    /**
     *
     */
    public void restart() {
        EApplicationState.setGameState(EApplicationState.RUNNING);
        GameMapManager.INSTANCE.restartMap();
        TanksAppAdapter.INSTANCE.detachAppState(this);
    }

    /**
     *
     */
    public void showMainMenu() {
    }

    /**
     * Stop this AppState.
     */
    public void exit() {
        TanksAppAdapter.INSTANCE.stop();
    }

    void setGameToPause(ITanks gameModel) {
        this.gameModel = gameModel;
    }
}
