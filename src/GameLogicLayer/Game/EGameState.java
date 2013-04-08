
package GameLogicLayer.Game;

/**
 *
 * @author Daniel
 */
public enum EGameState {
    /**
     *
     */
    MAIN_MENU,
    /**
     *
     */
    PAUSED,
    /**
     *
     */
    RUNNING,
    /**
     *
     */
    LOADING,
    /**
     *
     */
    NONE,
    /**
     *
     */
    MULTI_MENU,
    /**
     *
     */
    LOADING_MAP;
    
    private static EGameState currentGameState = EGameState.NONE;
    private static EGameState previousGameState = EGameState.NONE;

    /**
     *
     * @return
     */
    public static EGameState getGameState() {
        return currentGameState;
    }

    /**
     *
     * @param currentGameState
     */
    public static void setGameState(EGameState currentGameState) {
        //store the currentgamestate into previous
        previousGameState = EGameState.currentGameState; 
        //store new game state into currentgamestate
        EGameState.currentGameState = currentGameState; 
    }

    /**
     *
     * @return
     */
    public static EGameState getPreviousGameState() {
        return previousGameState;
    }
    /*
    private static boolean moving = false;

    public static boolean isMoving() {
        return moving;
    }

    public static void setMoving(boolean moving) {
        GameState.moving = moving;
    }*/
}
