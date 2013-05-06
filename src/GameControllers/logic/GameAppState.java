package GameControllers.logic;

import GameModel.EApplicationState;
import App.TanksAppAdapter;
import GameControllers.entitycontrols.PowerupControl;
import GameControllers.entitycontrols.TanksVehicleControl;
import GameModel.IArmedVehicle;
import GameModel.IExplodingProjectile;
import GameModel.IPlayer;
import GameModel.IPowerup;
import GameModel.ITanks;
import GameModel.IWorldObject;
import GameView.Map.IGameWorld;
import GameView.Sounds.ESounds;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;

/**
 * A game app state holding functionality for the game.
 * 
 * @author Daniel
 */
public class GameAppState extends AbstractAppState implements PhysicsCollisionListener{
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
        EApplicationState.setGameState(EApplicationState.RUNNING);
        gameWorld.load();
        
        TanksAppAdapter.INSTANCE.setBulletAppStateEnabled(true);

        loadDesktopInputs();
        
        SoundManager.INSTANCE.play(ESounds.GAMEMUSIC_1);
        
        gameModel.startGame();
        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(this);
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
        TanksAppAdapter.INSTANCE.removeAllPhysics();
        this.cleanup();
    }
    
    /**
     * 
     */
    @Override
    public void cleanup() {
      super.cleanup();
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

        TanksAppAdapter.INSTANCE.addInputListener(actionListener, PAUSE);
    }
    
     private void removeDesktopInputs() {
        if (TanksAppAdapter.INSTANCE.hasInputMapping(PAUSE)) {
            TanksAppAdapter.INSTANCE.deleteInputMapping(PAUSE);
        }
        TanksAppAdapter.INSTANCE.removeInputListener(actionListener);
    }
    
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            if (EApplicationState.getGameState() != EApplicationState.RUNNING) {
                return;
            }
            if (name.equals(PAUSE) && !isPressed) {
                EApplicationState.setGameState(EApplicationState.PAUSED);
                gameModel.pauseGame();
                GUIManager.INSTANCE.showPauseMenu(gameModel);
            }
        }
    };

    @Override
    public void collision(PhysicsCollisionEvent event) {
        if (event.getNodeA() != null && event.getNodeB() != null) {
            IWorldObject objA = event.getNodeA().getUserData("Model");
            IWorldObject objB = event.getNodeB().getUserData("Model");
            if (objA instanceof IArmedVehicle && objB instanceof IPowerup) {
                IPowerup powerup = (IPowerup) objB;
                gameModel.powerupPickedUp(powerup);
            } else if (objB instanceof IArmedVehicle && objA instanceof IPowerup) {
                IPowerup powerup = (IPowerup) objA;
                gameModel.powerupPickedUp(powerup);
            }
        }
    }
}
