package App;

import GameControllers.Managers.GUIManager;
import GameControllers.Managers.SoundManager;
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
   
    BulletAppState getBulletAppState() {
        return bulletAppState;
    }
    
    AppSettings getSettings() {
        return settings;
    }
}
