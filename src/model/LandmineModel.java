
package model;

import com.jme3.math.Vector3f;
import utilities.Constants;

/**
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class LandmineModel extends AExplodingObject {
    /**
     * Creates a new lanmine model.
     */
    public LandmineModel() {
        super(Constants.LANDMINE_MASS, Constants.LANDMINE_DAMAGE, 
                Constants.LANDMINE_EXPLOSION_END_TIME, Constants.LANDMINE_LIFE_TIME );
    }

    /**
     * 
     * @param initialPos
     * @param player 
     */
    public void dropMine(Vector3f initialPos, IPlayer player) {
        this.initialPos = new Vector3f(initialPos);
        this.launcherPlayer = player;
        showInWorld();
    }
}
