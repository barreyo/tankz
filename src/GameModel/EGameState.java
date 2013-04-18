
package GameModel;

/**
 * Enum representing the game state.
 * 
 * @author Daniel
 */
public enum EGameState {
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
    
    private static EGameState currentGameState = EGameState.NONE;
    private static EGameState previousGameState = EGameState.NONE;

    /**
     * Returns the current EGameState.
     * 
     * @return the current EGameState
     */
    public static EGameState getGameState() {
        return currentGameState;
    }

    /**
     * Sets the current EGameState.
     * @param currentGameState the EGameState to set.
     */
    public static void setGameState(EGameState currentGameState) {
        //store the currentgamestate into previous
        previousGameState = EGameState.currentGameState; 
        //store new game state into currentgamestate
        EGameState.currentGameState = currentGameState; 
    }

    /**
     * Returns the previous EGameState.
     * @return the previous EGameState.
     */
    public static EGameState getPreviousGameState() {
        return previousGameState;
    }
}
