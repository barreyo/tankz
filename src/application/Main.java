package application;

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
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class Main {
    /**
     * Main method.
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        // load the application settings
        AppSettings appSettings = new AppSettings(true);
        appSettings.setVSync(true);
        appSettings.setTitle("Tankz");
        appSettings.setFullscreen(true);

        try {
            appSettings.setIcons(new BufferedImage[]{ImageIO.read(new File("assets/Interface/icon.png"))});
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Icon missing.", ex);
        }

        // set the start image
        appSettings.setSettingsDialogImage("Interface/splashscreen.jpg");

        // apply the settings
        TanksAppAdapter.INSTANCE.setSettings(appSettings);

        // starts the application (GameNameGoesHere)
        TanksAppAdapter.INSTANCE.start();
    }
}
