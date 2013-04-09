package GameLogicLayer.logic;

import GameLogicLayer.logic.GUIManager;
import GameModelLayer.Game.EGameState;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;

/**
 * A game app state holding functionality for the game.
 * 
 * @author Daniel
 */
public class GameAppState extends AbstractAppState {
    
    private TanksGame app;
    
    // Input mapping command
    private static final String PAUSE = "PAUSE";

    /**
     *  Creates a new game app state.
     */
    public GameAppState() {
        app = TanksGame.getApp();
    }
    
    /**
     *
     * @param stateManager
     * @param app
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
      super.initialize(stateManager, app); 
   }
    
     /**
     * Called when appstate is attached to statemanager.
     * 
     * @param stateManager The statemanager that this is attached to.
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        super.stateAttached(stateManager);
        EGameState.setGameState(EGameState.RUNNING);
        //showHud();
        
        app.getStateManager().attach(TanksAppStateFactory.getAppState(CommonMapAppState.class));
        app.getBulletAppState().setEnabled(true);

        loadDesktopInputs();
    }
     
     /**
     * Called when appstate is detached from statemanager.
     * 
     * @param stateManager
     */
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        removeDesktopInputs();
        // deatch all Level States
        app.getStateManager().detach(app.getStateManager().getState(CommonMapAppState.class));
        app.getBulletAppState().setEnabled(false);

        // TODO: pause any playing music
        app.getGuiNode().detachAllChildren();
    }
    
    /**
     * 
     */
    @Override
    public void cleanup() {
      super.cleanup();
      // unregister all my listeners, detach all my nodes, etc...
      this.app.getRootNode().detachAllChildren(); // modify scene graph...
    }

    @Override
    public void setEnabled(boolean enabled) {
      // Pause and unpause
      super.setEnabled(enabled);
    }

    // Note that update is only called while the state is both attached and enabled.
    @Override
    public void update(float tpf) {
    }
    
    private void loadDesktopInputs() {
        if (TanksInputAdapter.INSTANCE.hasMapping("SIMPLEAPP_Exit")) {
            TanksInputAdapter.INSTANCE.deleteMapping("SIMPLEAPP_Exit");
        }

        TanksInputAdapter.INSTANCE.addMapping(PAUSE, new KeyTrigger(KeyInput.KEY_ESCAPE),
                              new KeyTrigger(KeyboardInputEvent.KEY_PAUSE),
                              new KeyTrigger(KeyboardInputEvent.KEY_P));
        /*inputManager.addMapping(NEXT_LEVEL, new KeyTrigger(KeyInput.KEY_F2));
        inputManager.addMapping(PREVIOUS_LEVEL, new KeyTrigger(KeyInput.KEY_F1));*/

        TanksInputAdapter.INSTANCE.addListener(actionListener, PAUSE);
    }
    
     private void removeDesktopInputs() {
        TanksInputAdapter.INSTANCE.deleteMapping(PAUSE);

        TanksInputAdapter.INSTANCE.removeListener(actionListener);
    }
    
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean isPressed, float tpf) {
            System.out.println(EGameState.getGameState().name());
            if (EGameState.getGameState() != EGameState.RUNNING) {
                return;
            }
            if (name.equals(PAUSE) && !isPressed) {
                GUIManager.INSTANCE.showPauseMenu();
            }
        }
    };
}
