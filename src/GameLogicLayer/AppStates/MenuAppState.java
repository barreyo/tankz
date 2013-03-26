/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.AppStates;

import GameLogicLayer.GUI.GUIManager;
import GameLogicLayer.GUI.OptionsScreen;
import GameLogicLayer.Game.TanksGame;
import GameModelLayer.Game.GameState;
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
    private TanksGame app;
    private GUIManager guiManager;
    private Nifty nifty;
    private Element popupElement;
    private Element currentElement;
    private SoundHandle sound;

    /**
     *  Create a new main menu app state.
     */
    public MenuAppState() {
        app = TanksGame.getApp();
        guiManager = app.getGUIManager();
        nifty = guiManager.getNifty();
        nifty.fromXml("Interface/Nifty/MainMenu.xml", "start", this, new OptionsScreen());
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
        goToMainMenu();
        GameState.setGameState(GameState.MAIN_MENU);
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
        app.stop();
    }
    
    // TODO implement method to call for number of players
    /**
     *
     */
    public void loadOnePlayerGame() {
        guiManager.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadTwoPlayerGame() {
        guiManager.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadThreePlayerGame() {
        guiManager.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadFourPlayerGame() {
        guiManager.showLoadingScreen();
    }
}
