/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.AppStates;

import GameLogicLayer.Game.GameManager;
import GameModelLayer.Game.GameState;
import GameViewLayer.Map.TanksDefaultMap;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;

/**
 *
 * @author Daniel
 */
public class GameAppState extends AbstractAppState {
    
    private GameManager app;
    private InputManager inputManager;
    
    private static final String PAUSE = "PAUSE";

    public GameAppState() {
        app = GameManager.getApp();
        inputManager = app.getInputManager();
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
      super.initialize(stateManager, app); 
   }
    
     @Override
    public void stateAttached(AppStateManager stateManager) {
        super.stateAttached(stateManager);
        GameState.setGameState(GameState.RUNNING);
        //showHud();
        
        app.getStateManager().attach(app.getTanksAppStateManager().getAppState(TanksDefaultMap.class));
        app.getBulletAppState().setEnabled(true);

        loadDesktopInputs();
    }
     
      @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        removeDesktopInputs();
        // deatch all Level States
        app.getStateManager().detach(app.getStateManager().getState(TanksDefaultMap.class));
        app.getBulletAppState().setEnabled(false);

        // TODO: pause any playing music
        app.getGuiNode().detachAllChildren();
    }
    
    @Override
    public void cleanup() {
      super.cleanup();
      // unregister all my listeners, detach all my nodes, etc...
      this.app.getRootNode().detachAllChildren(); // modify scene graph...
      //this.app.doSomethingElse();                 // call custom methods...
    }

    @Override
    public void setEnabled(boolean enabled) {
      // Pause and unpause
      super.setEnabled(enabled);
      if(enabled){
        // init stuff that is in use while this state is RUNNING
      } else {
        // take away everything not needed while this state is PAUSED
      }
    }

    // Note that update is only called while the state is both attached and enabled.
    @Override
    public void update(float tpf) {
      // do the following while game is RUNNING
      //this.app.getRootNode().getChild("blah").scale(tpf); // modify scene graph...
      //x.setUserData(...);                                 // call some methods...
    }
    
    private void loadDesktopInputs() {
        if (inputManager.hasMapping("SIMPLEAPP_Exit")) {
            inputManager.deleteMapping("SIMPLEAPP_Exit");
        }

        inputManager.addMapping(PAUSE, new KeyTrigger(KeyInput.KEY_ESCAPE),
                              new KeyTrigger(KeyboardInputEvent.KEY_PAUSE),
                              new KeyTrigger(KeyboardInputEvent.KEY_P));
        /*inputManager.addMapping(NEXT_LEVEL, new KeyTrigger(KeyInput.KEY_F2));
        inputManager.addMapping(PREVIOUS_LEVEL, new KeyTrigger(KeyInput.KEY_F1));*/

        inputManager.addListener(actionListener, PAUSE);
    }
    
     private void removeDesktopInputs() {
        inputManager.deleteMapping(PAUSE);

        inputManager.removeListener(actionListener);
    }
    
    private ActionListener actionListener = new ActionListener() {

        public void onAction(String name, boolean isPressed, float tpf) {

            if (GameState.getGameState() != GameState.RUNNING) {
                return;
            }
            if (name.equals(PAUSE) && !isPressed) {
                app.getStateManager().detach(GameAppState.this);
                app.getStateManager().attach(app.getTanksAppStateManager().getAppState(MenuAppState.class));
            }
        }
    };
}
