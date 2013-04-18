package GameControllers.logic;

import GameModel.EGameState;
import App.TanksAppAdapter;
import GameModel.ITanks;
import GameView.Map.IGameWorld;
import GameView.Sounds.ESounds;
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
    // Input mapping command
    private static final String PAUSE = "PAUSE";
    
    private ITanks gameModel;
    private IGameWorld gameWorld;
    
    // This will be our game controller, ie will get a game model and a gameworld
    public GameAppState(ITanks game, IGameWorld gameWorld) { 
        this.gameModel = game;
        this.gameWorld = gameWorld;
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
        gameWorld.load();
        
        TanksAppAdapter.INSTANCE.setBulletAppStateEnabled(true);

        loadDesktopInputs();
        
        //Stops the menu sound and plays game sound.
        //Did not work in stateDetached in MenuAppState
        SoundManager.INSTANCE.stop(ESounds.MENU_SOUND);
        SoundManager.INSTANCE.play(ESounds.GAMEMUSIC_1);
    }
     
     /**
     * Called when appstate is detached from statemanager.
     * 
     * @param stateManager
     */
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        TanksAppAdapter.INSTANCE.setBulletAppStateEnabled(false);
        this.cleanup();
    }
    
    /**
     * 
     */
    @Override
    public void cleanup() {
      super.cleanup();
      // unregister all my listeners, detach all my nodes, etc...
      gameModel.cleanup();
      gameWorld.cleanup();
      removeDesktopInputs();
      GUIManager.INSTANCE.cleanup();
    }

    @Override
    public void setEnabled(boolean enabled) {
      // Pause and unpause
      super.setEnabled(enabled);
    }

    // Note that update is only called while the state is both attached and enabled.
    @Override
    public void update(float tpf) {
        gameModel.update(tpf);
    }
    
    private void loadDesktopInputs() {
        if (TanksAppAdapter.INSTANCE.hasInputMapping("SIMPLEAPP_Exit")) {
            TanksAppAdapter.INSTANCE.deleteInputMapping("SIMPLEAPP_Exit");
        }

        TanksAppAdapter.INSTANCE.addInputMapping(PAUSE, new KeyTrigger(KeyInput.KEY_ESCAPE),
                              new KeyTrigger(KeyboardInputEvent.KEY_PAUSE),
                              new KeyTrigger(KeyboardInputEvent.KEY_P));
        /*inputManager.addMapping(NEXT_LEVEL, new KeyTrigger(KeyInput.KEY_F2));
        inputManager.addMapping(PREVIOUS_LEVEL, new KeyTrigger(KeyInput.KEY_F1));*/

        TanksAppAdapter.INSTANCE.addInputListener(actionListener, PAUSE);
    }
    
     private void removeDesktopInputs() {
        TanksAppAdapter.INSTANCE.deleteInputMapping(PAUSE);

        TanksAppAdapter.INSTANCE.removeInputListener(actionListener);
    }
    
    private ActionListener actionListener = new ActionListener() {
        @Override
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
