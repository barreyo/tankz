package GameControllers.logic;

import App.TanksAppAdapter;
import GameControllers.TanksFactory;
import GameView.Map.GameWorld1;


/**
 * A manager that manages the different maps of the game.
 *
 * @author Daniel
 */
public enum GameMapManager {
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

        switch (gameMap) {
            case 1:
                currentGame = TanksFactory.getNewGame(GameWorld1.class, MenuAppState.getInstance().getPlayerNames());
                break;
            default:
                currentGame = TanksFactory.getNewGame(GameWorld1.class, MenuAppState.getInstance().getPlayerNames());
        }
        
        TanksAppAdapter.INSTANCE.attachAppState(currentGame);
    }

    /**
     *
     */
    public void restartMap() {
        cleanup();

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

    public void cleanup() {
        PhysicsManager.INSTANCE.cleanup();
        SoundManager.INSTANCE.cleanup();
        ViewPortManager.INSTANCE.cleanup();
        GraphicManager.INSTANCE.cleanup();
        EffectsManager.INSTANCE.cleanup();
        TanksAppAdapter.INSTANCE.detachAppState(currentGame);
    }
}
