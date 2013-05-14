
package GameModel;

import com.jme3.input.KeyInput;

/**
 * Enum defining the global inputs of the game.
 * 
 * @author Johan Backman
 */
public enum EGlobalInputs {
    
    /**
     * Toggle sound on/off button.
     */
    SoundToggle(KeyInput.KEY_9);
    
    private final int toggleSound;
    
    private EGlobalInputs(int key) {
        toggleSound = key;
    }
    
    /**
     * Returns the mute key.
     * 
     * @return mute key.
     */
    public int getMuteButton() {
        return toggleSound;
    }
    
}
