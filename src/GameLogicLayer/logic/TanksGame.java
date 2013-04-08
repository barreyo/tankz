package GameLogicLayer.logic;

import GameLogicLayer.logic.GUIManager;
import GameLogicLayer.logic.GUIManager;
import GameLogicLayer.logic.GUIManager;
import GameLogicLayer.player.UserSettings;
import GameModelLayer.Game.Game;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.post.FilterPostProcessor;
import com.jme3.system.AppSettings;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * The main controller of the game TanksGame.
 *
 * @author Daniel, Per, Johan, Albin
 */
public class TanksGame extends SimpleApplication {
    // A reference to this app. This is needed to allow access to managers.
    private static TanksGame tanksApp;
    private Game gameModel;
    
    // Managers
    private UserSettings userSettings;
    private BulletAppState bulletAppState;
    private FilterPostProcessor fpp;
 
    /*
     * Creates a new TanksGame which starts in settings window.
     */
    /**
     *
     */
    public TanksGame() {
        super(new StatsAppState());
    }
    
    /**
     *  Initiates the application.
     */
    @Override
    public void simpleInitApp() {
        tanksApp = this;
        gameModel = new Game();
        
        fpp = new FilterPostProcessor(assetManager);
        
        // Attaching a physics appstate
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        
        // Creating different essential managers
        userSettings = new UserSettings();
        
        // Show the main menu
        GUIManager.getInstance().showMainMenu();
        inputManager.setCursorVisible(true);
        
        // Detach the settings window.
        stateManager.detach(stateManager.getState(StatsAppState.class));
        
        // Used to logger messages for HUD handler Nifty
        Logger.getLogger("de.lessvoid.nifty").setLevel(Level.SEVERE);
        Logger.getLogger("NiftyInputEventHandlingLog").setLevel(Level.SEVERE);
    }
    
    /**
     *
     * @return
     */
    public static TanksGame getApp() {
        return tanksApp;
    }

    /**
     *
     * @return
     */
    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }
    
    /**
     *
     * @return
     */
    public AppSettings getSettings() {
        return settings;
    }

    /**
     *
     * @return
     */
    public UserSettings getUserSettings() {
        return userSettings;
    }

    /**
     *
     * @return
     */
    public FilterPostProcessor getFpp() {
        return fpp;
    }
    
    public Game getGameModel() {
        return gameModel;
    }
}
