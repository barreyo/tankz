
package GameModel;

/**
 * Enum representing the game state.
 * 
 * @author Daniel
 */
public enum EApplicationState {
    /**
     * Main menu state.
     */
    MAIN_MENU,
    /**
     * Pause menu state.
     */
    PAUSED,
    /**
     * Running state.
     */
    RUNNING,
    /**
     * Loading screen state.
     */
    LOADING,
    /**
     * No state.
     */
    NONE,
    /**
     * Multiplayer choosing menu state.
     */
    MULTI_MENU,
    /**
     * Loading map state.
     */
    LOADING_MAP;
    
    private static EApplicationState currentGameState = EApplicationState.NONE;
    private static EApplicationState previousGameState = EApplicationState.NONE;

    /**
     * Returns the current EGameState.
     * 
     * @return the current EGameState
     */
    public static EApplicationState getGameState() {
        return currentGameState;
    }

    /**
     * Sets the current EGameState.
     * @param currentGameState the EGameState to set.
     */
    public static void setGameState(EApplicationState currentGameState) {
        //store the currentgamestate into previous
        previousGameState = EApplicationState.currentGameState; 
        //store new game state into currentgamestate
        EApplicationState.currentGameState = currentGameState; 
    }

    /**
     * Returns the previous EGameState.
     * @return the previous EGameState.
     */
    public static EApplicationState getPreviousGameState() {
        return previousGameState;
    }
}
