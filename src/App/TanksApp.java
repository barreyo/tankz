package App;

import GameControllers.logic.GUIManager;
import GameControllers.logic.SoundManager;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.renderer.Caps;
import com.jme3.system.AppSettings;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * The main controller of the game TanksGame.
 *
 * @author Daniel, Per, Johan, Albin
 */
public class TanksApp extends SimpleApplication {
    // A reference to this app. This is needed to allow access to managers.
    private static TanksApp tanksApp;
    
    // Managers
    private BulletAppState bulletAppState;
    
    private FilterPostProcessor fpp;
    
    CartoonEdgeFilter cef = new CartoonEdgeFilter();
    
    
    
    /*
     * Creates a new TanksGame which starts in settings window.
     */
    /**
     *
     */
    public TanksApp() {
        super(new StatsAppState());
    }
    
    /**
     *  Initiates the application.
     */
    @Override
    public void simpleInitApp() {
        tanksApp = this;
        
        // Attaching a physics appstate
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        /*
        // Creating different essential managers
        userSettings = new UserSettings();*/
        
        //Loads the sounds needed before game start.
        SoundManager.INSTANCE.preLoad();
        // Show the main menu
        GUIManager.INSTANCE.showMainMenu();
        
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
    public static TanksApp getApp() {
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
}
