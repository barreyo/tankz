package GameLogicLayer.GUI;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 * @author Daniel
 */
public class OptionsScreen implements ScreenController {
     private Nifty nifty;

    /**
     *
     * @param nifty
     * @param screen
     */
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        System.out.println("bind( " + screen.getScreenId() + ")");
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
    public void gotoMainMenu() {
        nifty.gotoScreen("start");
    }

    // TODO Change this
    /**
     *
     * @return
     */
    public String getPlayerName() {
        return "wes";
    }
}
