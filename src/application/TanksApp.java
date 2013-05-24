package application;

import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.system.AppSettings;
import controller.managers.GUIManager;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
     * Initiates the application.
     */
    @Override
    public void simpleInitApp() {
        // Creating and attaching an appstate needed to handle physics
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);

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

    /**
     * Change the application to fullscreen with current video settings.
     */
    public void toggleFullscreen(boolean bool) {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode[] modes = device.getDisplayModes();
        int i = 0; // note: there are usually several, let's pick the first
        settings.setResolution(modes[i].getWidth(), modes[i].getHeight());
        settings.setFrequency(modes[i].getRefreshRate());
        settings.setDepthBits(modes[i].getBitDepth());
        settings.setFullscreen(bool);
        this.setSettings(settings);
        this.restart(); // restart the context to apply changes
    }
}
