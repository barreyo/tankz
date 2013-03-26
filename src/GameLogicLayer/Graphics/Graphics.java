package GameLogicLayer.Graphics;

/**
 *
 * @author Daniel
 */
public enum Graphics {

    /**
     *
     */
    TANK("Models/tanken/tanken.j3o"),
    /**
     *
     */
    TEST_PLATFORM("Models/testFloor.j3o");
    
    private String fileLocation;

    Graphics(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     *
     * @return
     */
    public String getPath() {
        return fileLocation;
    }
}