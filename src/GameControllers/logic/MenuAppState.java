
package GameControllers.logic;

import GameModel.Game.ApplicationSettings;
import GameModel.Game.EGameState;
import App.TanksAppAdapter;
import GameView.Sounds.ESounds;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMouseMovedEvent;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.spi.sound.SoundHandle;
import de.lessvoid.nifty.tools.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * An app state representing the main menu.
 * 
 * @author Daniel
 */
public class MenuAppState extends AbstractAppState implements ScreenController {
    private static MenuAppState instance;
    
    private Nifty nifty;
    private Element popupElement;
    private Element currentElement;
    private SoundHandle sound;
    
    private final List<String> playerNames = new ArrayList<String>();

    /**
     *  Create a new main menu app state.
     */
    private MenuAppState() {
        nifty = GUIManager.INSTANCE.getNifty();
        nifty.fromXml("Interface/Nifty/MainMenu.xml", "start", this);
        //nifty.addXml("Interface/Nifty/MultiMenu.xml");

        nifty.getSoundSystem().addSound("hooverSound", "Sounds/click.ogg");

        sound = nifty.getSoundSystem().getSound("hooverSound");
        sound.setVolume(1.0f);
    }
    
    public static synchronized MenuAppState getInstance() {
        if (instance == null) {
            instance = new MenuAppState();
        }
        return instance;
    }

    /**
     * 
     * @param stateManager
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        TanksAppAdapter.INSTANCE.setCursorVisible(true);
        goToMainMenu();
        EGameState.setGameState(EGameState.MAIN_MENU);
        SoundManager.INSTANCE.play(ESounds.MENU_SOUND);
    }

    /**
     *
     * @param stateManager
     */
    @Override
    public void stateDetached(AppStateManager stateManager) {
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
    public void cleanup() {
        super.cleanup();
        playerNames.clear();
    }

    /**
     *
     */
    public void onStartScreen() {
        System.out.println("onStartScreen");
    }

    /**
     *
     */
    public void onEndScreen() {
        System.out.println("onEndScreen");
    }

    /**
     *
     */
    public void goToMainMenu() {
        nifty.gotoScreen("start");
    }
    
    /**
     *
     */
    public void goToMultiplayerScreen() {
       nifty.gotoScreen("multi");
    }

    /**
     *
     * @param id
     * @param event
     */
    @NiftyEventSubscriber(pattern = "main.*")
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
        popupElement = nifty.createPopup("popupExit");
    }

    /**
     *
     */
    public void closePopup() {
        nifty.closePopup(popupElement.getId());
    }

    /**
     *
     */
    public void quit() {
        nifty.gotoScreen("end");
    }

    /**
     *
     */
    public void showExitDialog() {
        nifty.showPopup(nifty.getCurrentScreen(), popupElement.getId(), null);
    }

    /**
     *
     */
    public void exit() {
        TanksAppAdapter.INSTANCE.stop();
    }
    
    /**
     *
     */
    public void loadOnePlayerGame() {
        playerNames.add("Player1");
        GUIManager.INSTANCE.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadTwoPlayerGame() {
        playerNames.add("Player1");
        playerNames.add("Player2");
        GUIManager.INSTANCE.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadThreePlayerGame() {
        playerNames.add("Player1");
        playerNames.add("Player2");
        playerNames.add("Player3");
        GUIManager.INSTANCE.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadFourPlayerGame() {
        playerNames.add("Player1");
        playerNames.add("Player2");
        playerNames.add("Player3");
        playerNames.add("Player4");
        GUIManager.INSTANCE.showLoadingScreen();
    }
    
    public ArrayList<String> getPlayerNames() {
        return new ArrayList<String>(playerNames);
    }
}
