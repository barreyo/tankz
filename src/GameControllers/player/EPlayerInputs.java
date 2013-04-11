package GameControllers.player;

import com.jme3.input.KeyInput;

/**
 * Enum defining the different set of keyboard inputs used in Tanks.
 *
 * @author Daniel
 */
public enum EPlayerInputs {

    Player1(KeyInput.KEY_A, KeyInput.KEY_D, KeyInput.KEY_W, KeyInput.KEY_S, KeyInput.KEY_RETURN, KeyInput.KEY_Q),
    Player2(KeyInput.KEY_H, KeyInput.KEY_K, KeyInput.KEY_U, KeyInput.KEY_J, KeyInput.KEY_O, KeyInput.KEY_Y),
    Player3(KeyInput.KEY_LEFT, KeyInput.KEY_RIGHT, KeyInput.KEY_UP, KeyInput.KEY_DOWN, KeyInput.KEY_NUMPAD0, KeyInput.KEY_NUMPAD1),
    Player4(KeyInput.KEY_NUMPAD4, KeyInput.KEY_NUMPAD6, KeyInput.KEY_NUMPAD8, KeyInput.KEY_NUMPAD5, KeyInput.KEY_NUMPAD9, KeyInput.KEY_NUMPAD7);
    
    private final int left, right, up, down, reset, shoot;
    
    private boolean isInUse;

    private EPlayerInputs(int l, int r, int u, int d, int re, int sh) {
        left = l;
        right = r;
        up = u;
        down = d;
        reset = re;
        shoot = sh;
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
