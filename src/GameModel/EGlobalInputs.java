
package GameModel;

import com.jme3.input.KeyInput;

/**
 * Enum defining the global inputs of the game.
 * 
 * @author Johan Backman
 */
public enum EGlobalInputs {
    
    /**
     * Sound inputs.
     */
    SoundInput(KeyInput.KEY_8, KeyInput.KEY_9);
    
    private final int toggleMusic, toggleFX;
    
    private EGlobalInputs(int music, int fx) {
        toggleMusic = music;
        toggleFX = fx;
    }
    
    /**
     * Returns the toggle music key.
     * 
     * @return music key.
     */
    public int getToggleMusicButton() {
        return toggleMusic;
    }
    
    /**
     * Returns the toggle FX key.
     * 
     * @return FX key.
     */
    public int getToggleFXButton() {
        return toggleFX;
    }
}
