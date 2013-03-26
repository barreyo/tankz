package GameLogicLayer.util;

import com.jme3.input.KeyInput;

/**
 *
 * @author Daniel
 */
public enum EPlayerInputs {

    Player1(KeyInput.KEY_A, KeyInput.KEY_D, KeyInput.KEY_W, KeyInput.KEY_S, KeyInput.KEY_RETURN),
    Player2(KeyInput.KEY_H, KeyInput.KEY_K, KeyInput.KEY_U, KeyInput.KEY_J, KeyInput.KEY_O);
    //Player3, Player4;
    private final int left, right, up, down, reset;
    
    private boolean isInUse;

    private EPlayerInputs(int l, int r, int u, int d, int re) {
        left = l;
        right = r;
        up = u;
        down = d;
        reset = re;
    }

    public int getLeftKey() {
        return left;
    }

    public int getRightKey() {
        return right;
    }

    public int getUpKey() {
        return up;
    }

    public int getDownKey() {
        return down;
    }

    public int getResetKey() {
        return reset;
    }
    
    public boolean isInUse() {
        return isInUse;
    }
    
    public void setInUse(boolean using) {
        isInUse = using;
    }
}
