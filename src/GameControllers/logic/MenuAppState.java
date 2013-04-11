
package GameControllers.logic;

import GameControllers.GUI.OptionsScreenController;
import GameModel.Game.UserSettings;
import GameModel.Game.EGameState;
import GameModel.gameEntity.Vehicle.TankModel;
import GameUtilities.TankAppAdapter;
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

/**
 * An app state representing the main menu.
 * 
 * @author Daniel
 */
public class MenuAppState extends AbstractAppState implements ScreenController {
    private Nifty nifty;
    private Element popupElement;
    private Element currentElement;
    private SoundHandle sound;

    /**
     *  Create a new main menu app state.
     */
    public MenuAppState() {
        nifty = GUIManager.INSTANCE.getNifty();
        nifty.fromXml("Interface/Nifty/MainMenu.xml", "start", this, new OptionsScreenController());
        //nifty.addXml("Interface/Nifty/MultiMenu.xml");

        nifty.getSoundSystem().addSound("hooverSound", "Sounds/click.ogg");

        sound = nifty.getSoundSystem().getSound("hooverSound");
        sound.setVolume(1.0f);
    }

    /**
     * 
     * @param stateManager
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        TankAppAdapter.INSTANCE.setCursorVisible(true);
        goToMainMenu();
        EGameState.setGameState(EGameState.MAIN_MENU);
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
        TankAppAdapter.INSTANCE.stop();
    }
    
    /**
     *
     */
    public void loadOnePlayerGame() {
        UserSettings.INSTANCE.addPlayer("Player1", new TankModel());
        GUIManager.INSTANCE.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadTwoPlayerGame() {
        UserSettings.INSTANCE.addPlayer("Player1", new TankModel());
        UserSettings.INSTANCE.addPlayer("Player2", new TankModel());
        GUIManager.INSTANCE.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadThreePlayerGame() {
        UserSettings.INSTANCE.addPlayer("Player1", new TankModel());
        UserSettings.INSTANCE.addPlayer("Player2", new TankModel());
        UserSettings.INSTANCE.addPlayer("Player3", new TankModel());
        GUIManager.INSTANCE.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadFourPlayerGame() {
        UserSettings.INSTANCE.addPlayer("Player1", new TankModel());
        UserSettings.INSTANCE.addPlayer("Player2", new TankModel());
        UserSettings.INSTANCE.addPlayer("Player3", new TankModel());
        UserSettings.INSTANCE.addPlayer("Player4", new TankModel());
        GUIManager.INSTANCE.showLoadingScreen();
    }
}
