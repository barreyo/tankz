

import GameLogicLayer.AppStates.TanksGame;
import com.jme3.system.AppSettings;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Main class.
 *
 * @author Daniel
 */
public class Main {
    /**
     * Main method.
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        // Create app and start it
        TanksGame app = new TanksGame();
        
        // load the application settings
        AppSettings appSettings = new AppSettings(true);
        appSettings.setVSync(true);
        appSettings.setTitle("Tanks!");
        appSettings.setFullscreen(true);

        try {
            appSettings.setIcons(new BufferedImage[]{ImageIO.read(new File("assets/Interface/icon.jpeg"))});
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Icon missing.", ex);
        }

        // set the start image
        appSettings.setSettingsDialogImage("Interface/splashscreen.jpg");

        // apply the settings
        app.setSettings(appSettings);

        // starts the application (GameNameGoesHere)
        app.start();
    }
}
