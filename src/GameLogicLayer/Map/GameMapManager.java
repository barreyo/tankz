package GameLogicLayer.Map;

import GameLogicLayer.AppStates.LoadingScreenAppState;
import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.Graphics.GraphicManager;
import GameLogicLayer.Graphics.MaterialManager;
import GameLogicLayer.Physics.PhysicsManager;
import GameLogicLayer.Sounds.SoundManager;
import GameLogicLayer.util.IManager;
import GameViewLayer.Map.IGameMap;
import GameViewLayer.Map.GameMap1;


/**
 * A manager that manages the different maps of the game.
 *
 * @author Daniel
 */
public class GameMapManager implements IManager {

    private TanksGame app;
    private MaterialManager materialManager;
    private PhysicsManager physicsManager;
    private SoundManager soundManager;
    private GraphicManager graphicsManager;
    
    private IGameMap currentGameMap;
    private int currentIntGameMap;
    private static final int NUMBER_OF_MAPS = 1; 
            
    //private CinematicComposition cc;

    /**
     * Creates a manager for game maps.
     */
    public GameMapManager() {
        app = TanksGame.getApp();
        materialManager = app.getMaterialManager();
        physicsManager = app.getPhysicsManager();
        soundManager = app.getSoundManager();
        graphicsManager = app.getGraphicManager();
        currentIntGameMap = 1;
    }

    /**
     *
     * @return
     */
    public int getCurrentIntMap() {
        return currentIntGameMap;
    }

    /**
     *
     * @param gameMap
     */
    public void setCurrentIntGameMap(int gameMap) {
        this.currentIntGameMap = gameMap;
    }

    /**
     *
     * @return
     */
    public IGameMap getCurrentMap() {
        return currentGameMap;
    }

    /**
     *
     * @param gameMap
     */
    public void load(int gameMap) {

        materialManager.load(gameMap);
        physicsManager.load(gameMap);
        soundManager.load(gameMap);
        graphicsManager.load(gameMap);

        switch (gameMap) {
            case 1:
                currentGameMap = new GameMap1();
                break;
        }
        
        currentGameMap.load();
    }

    public void restartMap() {
        cleanup();

        //this calls currentGameMap.load() inside
        app.getStateManager().attach(app.getTanksAppStateManager().getAppState(LoadingScreenAppState.class));
    }

    public void loadNextMap() {
        if (currentIntGameMap == NUMBER_OF_MAPS) {
            currentIntGameMap = 1;
        } else {
            currentIntGameMap++;
        }

        cleanup();
        app.getStateManager().attach(app.getTanksAppStateManager().getAppState(LoadingScreenAppState.class));
    }

    public void loadPreviousMap() {
        if (currentIntGameMap == 1) {
            currentIntGameMap = NUMBER_OF_MAPS;
        } else {
            currentIntGameMap--;
        }

        cleanup();
        app.getStateManager().attach(app.getTanksAppStateManager().getAppState(LoadingScreenAppState.class));
    }

    @Override
    public void cleanup() {
        materialManager.cleanup();
        physicsManager.cleanup();
        soundManager.cleanup();
        graphicsManager.cleanup();
        currentGameMap.cleanup();
        // animManager.cleanup();
    }
}
