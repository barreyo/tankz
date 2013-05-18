package App;

import GameControllers.logic.GUIManager;
import GameControllers.logic.SoundManager;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.system.AppSettings;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main app class of the application Tanks.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
class TanksApp extends SimpleApplication { 
    private BulletAppState bulletAppState;
    
    /**
     * Creates a new TanksApp which starts in settings window.
     */
    TanksApp() {
        super(new StatsAppState());
    }
    
    /**
     *  Initiates the application.
     */
    @Override
    public void simpleInitApp() {
        // Creating and attaching an appstate needed to handle physics
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
   
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
     * Returns the bullet physics app state of the game.
     * 
     * @return bullet app state.
     */
    BulletAppState getBulletAppState() {
        return bulletAppState;
    }
    
    /**
     * Returns the video settings for the game.
     *  
     * @return vide settings.
     */
    AppSettings getSettings() {
        return settings;
    }
}
