package GameModel;

import com.jme3.input.KeyInput;

/**
 * Enum defining the different set of keyboard inputs used in Tanks.
 *
 * @author Daniel
 */
public enum EPlayerInputs {

    Player1(KeyInput.KEY_A, KeyInput.KEY_D, KeyInput.KEY_W, KeyInput.KEY_S, KeyInput.KEY_RETURN, KeyInput.KEY_Q, KeyInput.KEY_E, KeyInput.KEY_1),
    Player2(KeyInput.KEY_H, KeyInput.KEY_K, KeyInput.KEY_U, KeyInput.KEY_J, KeyInput.KEY_O, KeyInput.KEY_Y, KeyInput.KEY_I, KeyInput.KEY_2),
    Player3(KeyInput.KEY_LEFT, KeyInput.KEY_RIGHT, KeyInput.KEY_UP, KeyInput.KEY_DOWN, KeyInput.KEY_NUMPAD0, KeyInput.KEY_NUMPAD1, KeyInput.KEY_RSHIFT, KeyInput.KEY_3),
    Player4(KeyInput.KEY_NUMPAD4, KeyInput.KEY_NUMPAD6, KeyInput.KEY_NUMPAD8, KeyInput.KEY_NUMPAD5, KeyInput.KEY_NUMPAD9, KeyInput.KEY_NUMPAD7, KeyInput.KEY_X, KeyInput.KEY_4);
    
    private final int left, right, up, down, reset, shoot, powerup, showScoreboard;
    
    private boolean isInUse;

    /**
     * Instantiates the EPlayerInputs
     * 
     * @param l left key
     * @param r right key
     * @param u up key
     * @param d down key
     * @param re restart key
     * @param sh shoot key
     * @param pwr powerup key
     */
    private EPlayerInputs(int l, int r, int u, int d, int re, int sh, int pwr, int shscb) {
        left = l;
        right = r;
        up = u;
        down = d;
        reset = re;
        shoot = sh;
        powerup = pwr;
        showScoreboard = shscb;
    }

    /**
     * Returns an integer representing the left steering key.
     * 
     * @return an integer representing the left steering key
     */
    public int getLeftKey() {
        return left;
    }
    
    /**
     * Returns an integer representing the left steering key.
     * 
     * @return an integer representing the left steering key
     */
    public int getRightKey() {
        return right;
    }

        /**
     * Returns an integer representing the left steering key.
     * 
     * @return an integer representing the left steering key
     */
    public int getUpKey() {
        return up;
    }

        /**
     * Returns an integer representing the left steering key.
     * 
     * @return an integer representing the left steering key
     */
    public int getDownKey() {
        return down;
    }

        /**
     * Returns an integer representing the left steering key.
     * 
     * @return an integer representing the left steering key
     */
    public int getResetKey() {
        return reset;
    }
    
        /**
     * Returns an integer representing the left steering key.
     * 
     * @return an integer representing the left steering key
     */
    public int getShootKey() {
        return shoot;
    }
    
    /**
     * Returns an integer representing the powerup key.
     * 
     * @return an integer respresenting the powerup key.
     */
    public int getPowerupKey() {
        return powerup;
    }
    
    /**
     * Returns an integer representing the show scoreboard key.
     * 
     * @return an integer respresenting the show scoreboard key.
     */
    public int getScoreboardKey() {
        return showScoreboard;
    }
    
    /**
     * Returns a boolean indicating if this set of player inputs is in use.
     * 
     * @return a boolean indicating if this set of player inputs is in use.
     */
    public boolean isInUse() {
        return isInUse;
    }
    
    /**
     * Sets the boolean indicating if this set of player inputs is in use.
     * 
     * @param using the boolean indicating if this set of player inputs is in use.
     */
    public void setInUse(boolean using) {
        isInUse = using;
    }
}
