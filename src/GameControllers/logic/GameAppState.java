package GameControllers.logic;

import GameModel.Game.EGameState;
import GameModel.Game.TanksGameModel;
import GameModel.Game.UserSettings;
import GameModel.Player.IPlayer;
import App.TanksAppAdapter;
import GameModel.Game.ITanks;
import GameView.GUI.HealthView;
import GameView.GUI.PowerupSlotView;
import GameView.GUI.TimerView;
import GameView.Map.IGameWorld;
import GameView.Sounds.ESounds;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * A game app state holding functionality for the game.
 * 
 * @author Daniel
 */
public class GameAppState extends AbstractAppState {
    
    
    // Input mapping command
    private static final String PAUSE = "PAUSE";

    /* This will be our game controller, ie will get a game model and a gameworld
    public GameAppState(ITanks game, IGameWorld gameWorld) { 
        
    }*/
    
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
        showHud();
        
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
        removeDesktopInputs();
        // deatch all Level States
        TanksAppAdapter.INSTANCE.setBulletAppStateEnabled(false);

        // TODO: pause any playing music
        TanksAppAdapter.INSTANCE.detachAllGUIChildren();
    }
    
    /**
     * 
     */
    @Override
    public void cleanup() {
      super.cleanup();
      // unregister all my listeners, detach all my nodes, etc...
      TanksAppAdapter.INSTANCE.detachAllRootChildren(); // modify scene graph...
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
    
    /**
     * Show all HUD elements of the screen.
     */
    private void showHud() {
        
        List<PowerupSlotView> psvList = new ArrayList<PowerupSlotView>();
        int i = 0;
        for ( IPlayer p : UserSettings.INSTANCE.getPlayers() ) {
            psvList.add(new PowerupSlotView(p, ViewPortManager.INSTANCE.getViewportForPlayer(p)));
            psvList.get(i).show();
            i++;
        }
        TimerView timerView = new TimerView(new TanksGameModel(UserSettings.INSTANCE.getPlayers()));
        timerView.show();
        
        List<HealthView> hpvList = new ArrayList<HealthView>();
        i = 0;
        for ( IPlayer p : UserSettings.INSTANCE.getPlayers() ) {
            hpvList.add(new HealthView(p, ViewPortManager.INSTANCE.getViewportForPlayer(p)));
            hpvList.get(i).show();
            i++;
        }
    }
    
    /**
     * Hide all HUD elements of the screen.
     */
    private void hideHud() {
        
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
