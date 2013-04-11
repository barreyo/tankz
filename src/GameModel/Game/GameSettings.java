
package GameModel.Game;

/**
 * Representation of the rules.
 * 
 * @author perthoresson
 */
public class GameSettings {
    
    private float gameTime;
    private int killsToWin;
    
    /**
     * Basic constructor.
     * 
     * @param gameTime
     * @param killsToWin 
     */
    public GameSettings (float gameTime, int killsToWin){
        this.gameTime = gameTime;
        this.killsToWin = killsToWin;
    }
    
    /**
     * Sets the game time.
     * @param gameTime 
     */
    public void setGameTime (float gameTime){
        this.gameTime = gameTime;
    }
    
    /**
     * Sets number of kills to win.
     * @param kills 
     */
    public void setKillsToWin(int kills){
        this.killsToWin = kills;
    }
    
    /**
     * Returns the game time.
     * @return gameTime
     */
    public float getGameTime(){
        return gameTime;
    }
    
    /**
     * Returns number of kills to win.
     * 
     * @return killsToWin
     */
    public int killsToWin(){
        return killsToWin;
    }
    
}
