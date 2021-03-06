
package view.gui;

/**
 * Paths to the fonts used in the game.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum EFonts {
    
    /**
     * The main font for the main menu text.
     */
    HELVETICA("Interface/Fonts/helvetica.fnt"),
    
    /**
     * Scorboard text style.
     */
    HELVETICABOLD("Interface/Fonts/helveticaBold.fnt"),
    
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
