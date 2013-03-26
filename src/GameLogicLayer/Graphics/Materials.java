
package GameLogicLayer.Graphics;

/**
 *
 * @author Daniel
 */
public enum Materials {
    /**
     *
     */
    NORMAL("Materials/Normal.j3m");
    private final String pathToMaterial;

    Materials(String materialLocation) {
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
