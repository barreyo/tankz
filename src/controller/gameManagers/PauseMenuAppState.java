package controller.gameManagers;

import application.TanksAppAdapter;
import model.EApplicationState;
import model.ITanks;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
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
import java.util.List;

/**
 * An app state representing the pause menu.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class PauseMenuAppState extends AbstractAppState implements ScreenController {
    
    private static PauseMenuAppState instance;
    
    private Nifty nifty;
    private Element currentElement;
    private SoundHandle sound;
    private GameAppState gameApp;
    
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
     * {@inheritDoc}
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        super.stateAttached(stateManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        TanksAppAdapter.INSTANCE.removeGuiViewProcessor(niftyDisplay);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        showPauseMenu();
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public void cleanup() {
        super.cleanup();
    }

    /**
     * Hover event for menu items.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     * 
     * @param id nifty element id.
     * @param event nifty mouse event.
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
     * {@inheritDoc}
     */
    @Override
    public void bind(Nifty nifty, Screen screen) {
        // not necessary in this class.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStartScreen() {
        System.out.println("On start screen");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEndScreen() {
        System.out.println("On end screen");
    }

    /**
     * Show the pause menu.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void showPauseMenu() {
        // attach the nifty display to the gui view port as a processor
        TanksAppAdapter.INSTANCE.addGuiViewProcessor(niftyDisplay);
        TanksAppAdapter.INSTANCE.setCursorVisible(true);
        nifty.gotoScreen("pause");
    }

    /**
     * Resume the game.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void resume() {
        EApplicationState.setGameState(EApplicationState.RUNNING);
        gameApp.resumeGame();
        TanksAppAdapter.INSTANCE.setCursorVisible(false);
        TanksAppAdapter.INSTANCE.detachAppState(this);
    }
    
    /**
     * Go to main menu.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void menu() {
         EApplicationState.setGameState(EApplicationState.MAIN_MENU);
         GameMapManager.INSTANCE.cleanup();
         TanksAppAdapter.INSTANCE.detachAppState(this);
         GUIManager.INSTANCE.showMainMenu();
    }

    /**
     * Restart the game.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void restart() {
        EApplicationState.setGameState(EApplicationState.RUNNING);
        GameMapManager.INSTANCE.restartMap();
        TanksAppAdapter.INSTANCE.detachAppState(this);
    }

    /**
     * Finish the game.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void exit() {
        TanksAppAdapter.INSTANCE.stop();
    }

    /**
     * Pause the game.
     * 
     * @param gameModel model to pause. 
     */
    void setGameToPause(GameAppState gameApp) {
        this.gameApp = gameApp;
    }
}
