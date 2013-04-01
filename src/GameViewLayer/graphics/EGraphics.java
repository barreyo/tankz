package GameViewLayer.graphics;

/**
 * Enum holding relative paths to models.
 * 
 * @author Daniel
 */
public enum EGraphics {

    /**
     *  Model used for tanks.
     */
    TANK("Models/tanken/tanken.j3o"),
    /**
     * Model used for missile projectiles.
     */
    SHARK("Models/shark/shark.j3o");
    
    private String fileLocation;

    private EGraphics(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * Returns the relative path to the model.
     * @return the relative path of the model
     */
    public String getPath() {
        return fileLocation;
    }
}