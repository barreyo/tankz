
package GameView.GUI;

/**
 *
 * @author backman
 */
public enum EPowerupIcons {
    /**
     * Haste powerup icon.
     */
    HASTE("Interface/PowerupIcons/Haste.png"),
    
    /**
     * Homing powerup icon.
     */
    HOMING("Interface/PowerupIcons/Homing.png"),
    
    /**
     * Beer powerup icon.
     */
    BEER("Interface/PowerupIcons/Beer.png"),
    
    /**
     * Empty powerup icon.
     */
    EMPTY("Interface/PowerupIcons/Empty.png"),
    
    /**
     * Landmine powerup icon.
     */
    LANDMINE("Interface/PowerupIcons/Landmine.png");
    
    private String path;
    
    private EPowerupIcons(String path) {
        this.path = path;
    }
    
    /**
     * Get the path to the given powerup icon.
     * 
     * @return icon path.
     */
    public String getPath() {
        return path;
    }
}
