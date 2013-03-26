package GameViewLayer.graphics;

/**
 *
 * @author Daniel
 */
public enum EGraphics {

    /**
     *
     */
    TANK("Models/tanken/tanken.j3o"),
    /**
     *
     */
    TEST_PLATFORM("Models/testFloor.j3o"),
    /**
     *
     */
    SHARK("Models/shark/shark.j3o");
    
    private String fileLocation;

    EGraphics(String fileLocation) {
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