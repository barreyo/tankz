package controller;

import view.ViewPortManager;
import view.EffectsManager;
import view.GraphicManager;
import application.TanksAppAdapter;
import view.GameWorld1;
import controller.TanksFactory;


/**
 * A manager that manages the different maps of the game, singleton.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum GameMapManager {
    /**
     * Access to an instance of this singleton.
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
     * Returns the current map.
     * 
     * @return current map.
     */
    public int getCurrentIntMap() {
        return currentIntGameMap;
    }

    /**
     * Set the current map.
     * 
     * @param gameMap map number.
     */
    public void setCurrentIntGameMap(int gameMap) {
        this.currentIntGameMap = gameMap;
    }

    /**
     * Load a game map.
     * 
     * @param gameMap game map number.
     */
    public GameAppState load(int gameMap) {
        switch (gameMap) {
            case 1:
                currentGame = TanksFactory.getNewGame(GameWorld1.class, MenuAppState.getInstance().getPlayerNames());
                break;
            default:
                currentGame = TanksFactory.getNewGame(GameWorld1.class, MenuAppState.getInstance().getPlayerNames());
        }
        return currentGame;
    }

    /**
     * Restart the current map.
     */
    public void restartMap() {
        cleanup();
        TanksAppAdapter.INSTANCE.attachAppState(LoadingScreenAppState.getInstance());
    }

    /**
     * Load the next map.
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
     * Load the previous map.
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

    /**
     * Kill all game related managers.
     */
    public void cleanup() {
        PhysicsManager.INSTANCE.cleanup();
        SoundManager.INSTANCE.cleanup();
        ViewPortManager.INSTANCE.cleanup();
        GraphicManager.INSTANCE.cleanup();
        EffectsManager.INSTANCE.cleanup();
        TanksAppAdapter.INSTANCE.detachAppState(currentGame);
    }
}
