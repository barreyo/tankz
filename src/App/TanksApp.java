package App;

import GameControllers.logic.GUIManager;
import GameControllers.logic.SoundManager;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.audio.AudioRenderer;
import com.jme3.bullet.BulletAppState;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * The main controller of the game TanksGame.
 *
 * @author Daniel, Per, Johan, Albin
 */
class TanksApp extends SimpleApplication { 
    // Managers
    private BulletAppState bulletAppState;
    
    /**
     * Creates a new TanksGame which starts in settings window.
     */
    TanksApp() {
        super(new StatsAppState());
    }
    
    /**
     *  Initiates the application.
     */
    @Override
    public void simpleInitApp() {
        // Attaching a physics appstate
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
     *
     * @return
     */
    BulletAppState getBulletAppState() {
        return bulletAppState;
    }
    
    /**
     *
     * @return
     */
    AppSettings getSettings() {
        return settings;
    }
}
