
package model;

import com.jme3.input.KeyInput;

/**
 * Enum defining the global inputs of the game.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public enum EGlobalInputs {
    
    /**
     * Global inputs.
     */
    GlobalInput(KeyInput.KEY_8, KeyInput.KEY_9, KeyInput.KEY_5);
  
    
    private final int toggleMusic, toggleFX, changeMap;
    
    private EGlobalInputs(int music, int fx, int changeMap) {
        toggleMusic = music;
        toggleFX = fx;
        this.changeMap = changeMap;
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
    
    /**
     * 
     * @return change map key. 
     */
    public int getChangeMapButton(){
        return changeMap;
    }
}
