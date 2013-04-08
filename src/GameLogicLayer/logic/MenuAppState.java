/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.logic;

import GameLogicLayer.logic.GUIManager;
import GameLogicLayer.GUI.OptionsScreenController;
import GameLogicLayer.logic.GameManager;
import GameModelLayer.Game.EGameState;
import GameModelLayer.gameEntity.Vehicle.TankModel;
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
    
    private GameManager gameManager;

    /**
     *  Create a new main menu app state.
     */
    public MenuAppState() {
        app = TanksGame.getApp();
        guiManager = GUIManager.getInstance();
        gameManager = GameManager.getInstance();
        nifty = guiManager.getNifty();
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
        app.stop();
    }
    
    // TODO implement method to call for number of players
    /**
     *
     */
    public void loadOnePlayerGame() {
        gameManager.createPlayer("Player1", new TankModel());
        guiManager.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadTwoPlayerGame() {
        gameManager.createPlayer("Player1", new TankModel());
        gameManager.createPlayer("Player2", new TankModel());
        guiManager.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadThreePlayerGame() {
        gameManager.createPlayer("Player1", new TankModel());
        gameManager.createPlayer("Player2", new TankModel());
        gameManager.createPlayer("Player3", new TankModel());
        guiManager.showLoadingScreen();
    }
    
    /**
     *
     */
    public void loadFourPlayerGame() {
        gameManager.createPlayer("Player1", new TankModel());
        gameManager.createPlayer("Player2", new TankModel());
        gameManager.createPlayer("Player3", new TankModel());
        gameManager.createPlayer("Player4", new TankModel());
        guiManager.showLoadingScreen();
    }
}
