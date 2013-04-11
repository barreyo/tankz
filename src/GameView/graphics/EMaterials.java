
package GameView.graphics;

/**
 *  TODO Maybe remove this class (kan applicera material innan i scenecomposor)
 * 
 * @author Daniel
 */
public enum EMaterials {
    /**
     * 
     */
    NORMAL("Materials/Normal.j3m");
    private final String pathToMaterial;

    EMaterials(String materialLocation) {
        this.pathToMaterial = materialLocation;
    }

    /**
     *
     * @return
     */
    public String getPathToMaterial() {
        return pathToMaterial;
    }
}
