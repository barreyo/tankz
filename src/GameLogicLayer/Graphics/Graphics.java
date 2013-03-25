package GameLogicLayer.Graphics;

public enum Graphics {

    Tank("Models/tanken/tanken.j3o"), TEST_PLATFORM("Models/testFloor.j3o");
    
    private String fileLocation;

    Graphics(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getPath() {
        return fileLocation;
    }
}