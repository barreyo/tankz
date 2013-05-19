package GameControllers.logic;

import App.TanksAppAdapter;
import GameModel.EApplicationState;
import GameModel.IArmedVehicle;
import GameModel.IPowerup;
import GameModel.ITanks;
import GameModel.IWorldObject;
import GameUtilities.Constants;
import GameView.GUI.IHudElement;
import GameView.Map.IGameWorld;
import GameView.Sounds.ESounds;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;
import java.util.Collection;

/**
 * A game app state holding functionality for the game.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class GameAppState extends AbstractAppState implements PhysicsCollisionListener, ActionListener {
    // Input mapping command

    private static final String PAUSE = "PAUSE";
    private ITanks gameModel;
    private IGameWorld gameWorld;
    private Collection<IHudElement> gui;

    /**
     * Instantiate a main app state for the game itself.
     *
     * @param game model for the game.
     * @param gameWorld game world to be used.
     */
    public GameAppState(ITanks game, IGameWorld gameWorld, Collection<IHudElement> gui) {
        this.gameModel = game;
        this.gameWorld = gameWorld;
        this.gui = gui;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        super.stateAttached(stateManager);
        EApplicationState.setGameState(EApplicationState.RUNNING);

        TanksAppAdapter.INSTANCE.setBulletAppStateEnabled(true);

        loadDesktopInputs();

        if (!SoundManager.INSTANCE.isMusicMuted()) {
            SoundManager.INSTANCE.play(ESounds.GAMEMUSIC_1);
        }
  
        for (IHudElement element : gui) {
            element.show();
        }

        gameModel.startGame();
        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        TanksAppAdapter.INSTANCE.setBulletAppStateEnabled(false);
        TanksAppAdapter.INSTANCE.removeAllPhysics();
        this.cleanup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanup() {
        super.cleanup();
        gameModel.cleanup();
        gameWorld.cleanup();
        removeDesktopInputs();
        GUIManager.INSTANCE.cleanup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnabled(boolean enabled) {
        // Pause and unpause
        super.setEnabled(enabled);
    }

    /**
     * {@inheritDoc}
     */
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

        TanksAppAdapter.INSTANCE.addInputListener(this, PAUSE);
    }

    private void removeDesktopInputs() {
        if (TanksAppAdapter.INSTANCE.hasInputMapping(PAUSE)) {
            TanksAppAdapter.INSTANCE.deleteInputMapping(PAUSE);
        }
        TanksAppAdapter.INSTANCE.removeInputListener(this);
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (EApplicationState.getGameState() != EApplicationState.RUNNING) {
            return;
        }
        if (name.equals(PAUSE) && !isPressed) {
            EApplicationState.setGameState(EApplicationState.PAUSED);
            gameModel.pauseGame();
            TanksAppAdapter.INSTANCE.detachAllGUIChildren();
            GUIManager.INSTANCE.showPauseMenu(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collision(PhysicsCollisionEvent event) {
        if (event.getNodeA() != null && event.getNodeB() != null) {
            IWorldObject objA = event.getNodeA().getUserData(Constants.USER_DATA_MODEL);
            IWorldObject objB = event.getNodeB().getUserData(Constants.USER_DATA_MODEL);
            if (objA instanceof IArmedVehicle && objB instanceof IPowerup) {
                IPowerup powerup = (IPowerup) objB;
                gameModel.powerupPickedUp(powerup);
            } else if (objB instanceof IArmedVehicle && objA instanceof IPowerup) {
                IPowerup powerup = (IPowerup) objA;
                gameModel.powerupPickedUp(powerup);
            }
        }
    }

    void resumeGame() {
        gameModel.resumeGame();
        for (IHudElement element : gui) {
            element.show();
        }
    }
}
