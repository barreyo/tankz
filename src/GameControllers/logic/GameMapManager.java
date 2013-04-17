package GameControllers.logic;

import GameModel.Game.TanksGameModel;
import GameModel.Game.UserSettings;
import App.TanksAppAdapter;
import GameView.Map.IGameWorld;
import GameView.Map.GameWorld1;


/**
 * A manager that manages the different maps of the game.
 *
 * @author Daniel
 */
public enum GameMapManager implements IMapRelatedManager {
    INSTANCE;
    
    private IGameWorld currentGameMap;
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
     * @return
     */
    public IGameWorld getCurrentMap() {
        return currentGameMap;
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

        switch (gameMap) {
            case 1:
                currentGameMap = new GameWorld1(new TanksGameModel(UserSettings.INSTANCE.getPlayers()));
                break;
        }
        
        currentGameMap.load();
    }

    public void restartMap() {
        cleanup();

        //this calls currentGameMap.load() inside
        TanksAppAdapter.INSTANCE.attachAppState(TanksAppStateFactory.getAppState(LoadingScreenAppState.class));
    }

    public void loadNextMap() {
        if (currentIntGameMap == NUMBER_OF_MAPS) {
            currentIntGameMap = 1;
        } else {
            currentIntGameMap++;
        }

        cleanup();
        TanksAppAdapter.INSTANCE.attachAppState(TanksAppStateFactory.getAppState(LoadingScreenAppState.class));
    }

    public void loadPreviousMap() {
        if (currentIntGameMap == 1) {
            currentIntGameMap = NUMBER_OF_MAPS;
        } else {
            currentIntGameMap--;
        }

        cleanup();
        TanksAppAdapter.INSTANCE.attachAppState(TanksAppStateFactory.getAppState(LoadingScreenAppState.class));
    }

    @Override
    public void cleanup() {
        MaterialManager.INSTANCE.cleanup();
        PhysicsManager.INSTANCE.cleanup();
        SoundManager.INSTANCE.cleanup();
        ViewPortManager.INSTANCE.cleanup();
        GraphicManager.INSTANCE.cleanup();
        EffectsManager.INSTANCE.cleanup();
        currentGameMap.cleanup();
        // animManager.cleanup();
    }
}
