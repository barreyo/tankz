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
import com.jme3.input.InputManager;
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
 *
 * @author Daniel
 */
public class PauseMenuAppState extends AbstractAppState implements ScreenController {
    
    private TanksGame app;
    private InputManager inputManager;
    private GUIManager uiManager;
    private Nifty nifty;
    private Element currentElement;
    private SoundHandle sound;
    private Vector2f cursorPosition;
    private GameMapManager mapManager;
    
    private NiftyJmeDisplay niftyDisplay;

    public PauseMenuAppState() {
        app = TanksGame.getApp();
        inputManager = app.getInputManager();
        uiManager = app.getGUIManager();
        mapManager = app.getMapManager();
        niftyDisplay = new NiftyJmeDisplay(app.getAssetManager(),
                      inputManager, app.getAudioRenderer(), app.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        nifty.registerScreenController(this);
        nifty.fromXml("Interface/Nifty/PauseMenu.xml", "pause", this);

        nifty.getSoundSystem().addSound("hooverSound", "Sounds/click.ogg");
        sound = nifty.getSoundSystem().getSound("hooverSound");
    }

    @Override
    public void stateAttached(AppStateManager stateManager) {
        super.stateAttached(stateManager);

    }

    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        removeDesktopInputs();
        app.getGuiViewPort().removeProcessor(niftyDisplay);
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        GameState.setGameState(GameState.PAUSED);

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

    public void bind(Nifty nifty, Screen screen) {
    }

    public void onStartScreen() {
    }

    public void onEndScreen() {
    }

    public void showPauseMenu() {
        // attach the nifty display to the gui view port as a processor
        app.getGuiViewPort().addProcessor(niftyDisplay);

        inputManager.setCursorVisible(true);
        nifty.gotoScreen("pause");
        //inputManager.setCursorVisible(true);
    }

    public void resume() {
        GameState.setGameState(GameState.RUNNING);
        app.getStateManager().detach(this);
    }

    public void restart() {
        app.getStateManager().detach(this);

        /* remove this later */
        app.getStateManager().attach(app.getTanksAppStateManager().getAppState(LoadingScreenAppState.class));
        //mapManager.restartLevel();
    }

    public void showMainMenu() {
    }

    public void showOptionsScreen() {
    }

    public void exit() {
        app.stop();
    }
}
