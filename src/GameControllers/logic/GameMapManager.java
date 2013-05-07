package GameControllers.logic;

import App.TanksAppAdapter;
import GameControllers.TanksFactory;


/**
 * A manager that manages the different maps of the game.
 *
 * @author Daniel
 */
public enum GameMapManager implements IMapRelatedManager {
    /**
     *
     */
    INSTANCE;
    
    private GameAppState currentGame;
    private int currentIntGameMap;
    private static final int NUMBER_OF_MAPS = 1; 

    /**
     * Creates a manager for game maps.
     */
    private GameMapManager() {
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
     * @param gameMap
     */
    public void load(int gameMap) {
        SoundManager.INSTANCE.load(gameMap);
        MaterialManager.INSTANCE.load(gameMap);
        PhysicsManager.INSTANCE.load(gameMap);
        ViewPortManager.INSTANCE.load();
        GraphicManager.INSTANCE.load(gameMap);
        EffectsManager.INSTANCE.load(gameMap);
        
        currentGame = TanksFactory.getNewGame(MenuAppState.getInstance().getPlayerNames());
        
        TanksAppAdapter.INSTANCE.attachAppState(currentGame);
    }

    /**
     *
     */
    public void restartMap() {
        cleanup();

        //this calls currentGameMap.load() inside
        TanksAppAdapter.INSTANCE.attachAppState(LoadingScreenAppState.getInstance());
    }

    /**
     *
     */
    public void loadNextMap() {
        if (currentIntGameMap == NUMBER_OF_MAPS) {
            currentIntGameMap = 1;
        } else {
            currentIntGameMap++;
        }

        cleanup();
        TanksAppAdapter.INSTANCE.attachAppState(LoadingScreenAppState.getInstance());
    }

    /**
     *
     */
    public void loadPreviousMap() {
        if (currentIntGameMap == 1) {
            currentIntGameMap = NUMBER_OF_MAPS;
        } else {
            currentIntGameMap--;
        }

        cleanup();
        TanksAppAdapter.INSTANCE.attachAppState(LoadingScreenAppState.getInstance());
    }

    @Override
    public void cleanup() {
        MaterialManager.INSTANCE.cleanup();
        PhysicsManager.INSTANCE.cleanup();
        SoundManager.INSTANCE.cleanup();
        ViewPortManager.INSTANCE.cleanup();
        GraphicManager.INSTANCE.cleanup();
        EffectsManager.INSTANCE.cleanup();
        TanksAppAdapter.INSTANCE.detachAppState(currentGame);
        // animManager.cleanup();
    }
}
