
package GameView.GUI;

/**
 * Paths to the fonts used in the game.
 * 
 * @author Johan Backman
 */
public enum EFonts {
    
    /**
     * The main font for the main menu text.
     */
    HELVETICA("Interface/Fonts/helvetica.fnt"),
    
    /**
     * The main font for in game text.
     */
    INGAME("Interface/Fonts/helvetica.fnt"),
    
    /**
     * The main font for HUD elements.
     */
    HANDDRAWNSHAPES("Interface/Fonts/HandDrawnShapes.fnt");
    
    private String path;
    
    private EFonts(String path) {
        this.path = path;
    }
    
    /**
     * Returns the path to the font enum.
     * 
     * @return font path.
     */
    public String getPath() {
        return path;
    }
    
}
