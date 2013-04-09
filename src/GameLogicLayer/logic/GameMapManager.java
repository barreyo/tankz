package GameLogicLayer.logic;

import GameModelLayer.Game.TanksGameModel;
import GameModelLayer.Game.UserSettings;
import GameViewLayer.Map.IGameMap;
import GameViewLayer.Map.GameMap1;


/**
 * A manager that manages the different maps of the game.
 *
 * @author Daniel
 */
public enum GameMapManager implements IMapRelatedManager {
    INSTANCE;

    private TanksGame app;
    
    private IGameMap currentGameMap;
    private int currentIntGameMap;
    private static final int NUMBER_OF_MAPS = 1; 

    /**
     * Creates a manager for game maps.
     */
    private GameMapManager() {
        app = TanksGame.getApp();
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

        MaterialManager.INSTANCE.load(gameMap);
        PhysicsManager.INSTANCE.load(gameMap);
        SoundManager.INSTANCE.load(gameMap);
        ViewPortManager.INSTANCE.load();
        GraphicManager.INSTANCE.load(gameMap);
        EffectsManager.INSTANCE.load(gameMap);

        switch (gameMap) {
            case 1:
                currentGameMap = new GameMap1(new TanksGameModel(UserSettings.INSTANCE.getPlayers()));
                break;
        }
        
        currentGameMap.load();
    }

    public void restartMap() {
        cleanup();

        //this calls currentGameMap.load() inside
        app.getStateManager().attach(TanksAppStateFactory.getAppState(LoadingScreenAppState.class));
    }

    public void loadNextMap() {
        if (currentIntGameMap == NUMBER_OF_MAPS) {
            currentIntGameMap = 1;
        } else {
            currentIntGameMap++;
        }

        cleanup();
        app.getStateManager().attach(TanksAppStateFactory.getAppState(LoadingScreenAppState.class));
    }

    public void loadPreviousMap() {
        if (currentIntGameMap == 1) {
            currentIntGameMap = NUMBER_OF_MAPS;
        } else {
            currentIntGameMap--;
        }

        cleanup();
        app.getStateManager().attach(TanksAppStateFactory.getAppState(LoadingScreenAppState.class));
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
