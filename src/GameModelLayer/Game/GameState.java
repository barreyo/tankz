
package GameModelLayer.Game;

public enum GameState {
    MAIN_MENU, PAUSED, RUNNING, LOADING, NONE, MULTI_MENU;
    
    private static GameState currentGameState = GameState.NONE;
    private static GameState previousGameState = GameState.NONE;

    public static GameState getGameState() {
        return currentGameState;
    }

    public static void setGameState(GameState currentGameState) {
        //store the currentgamestate into previous
        previousGameState = GameState.currentGameState; 
        //store new game state into currentgamestate
        GameState.currentGameState = currentGameState; 
    }

    public static GameState getPreviousGameState() {
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
