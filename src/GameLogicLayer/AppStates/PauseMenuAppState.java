/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.AppStates;

import GameLogicLayer.Game.TanksGame;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 * @author Daniel
 */
public class PauseMenuAppState extends AbstractAppState implements ScreenController {
    
    private TanksGame app;
    private InputManager inputManager;
    
    public PauseMenuAppState() {
        app = TanksGame.getApp();
        inputManager = app.getInputManager();
    }
    
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app); 
    }

    public void bind(Nifty nifty, Screen screen) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onStartScreen() {
        //Probably wont need those
    }

    public void onEndScreen() {
        //Probably wont need those
    }
}
