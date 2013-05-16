
package GameView.graphics;

/**
 * This is a lol-class.
 * 
 * SHOULD BE REMOVED
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
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
