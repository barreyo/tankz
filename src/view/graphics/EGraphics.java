package view.graphics;

/**
 * Enum holding relative paths to models.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum EGraphics {

    /**
     *  Model used for tanks.
     */
    TANK("Models/Tankz/Tankz.j3o"),
    
    /**
     * Model used for missile projectiles.
     */
    SHARK("Models/shark/shark.j3o"),
    /**
     * The default map.
     */
    MAP1("Scenes/Map2/Map2_treeslol.j3o"),
    /**
     * Model used for powerup boxes.
     */
    POWERUP("Models/PUBox/PUBox.j3o"),
    /**
     * Model used for bomb.
     */
    BOMB("Models/bomb/bomb.j3o"),
    
    /**
     * Model used for landmine.
     */
    LANDMINE("Models/landmine/landmine.j3o"),
    
    /**
     * Model used for AirCall powerup.
     */
    NUKE("Models/nuclear/nuclear.j3o");
    
    private String fileLocation;

    private EGraphics(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * Returns the relative path to the model.
     * 
     * @return the relative path of the model
     */
    public String getPath() {
        return fileLocation;
    }
}