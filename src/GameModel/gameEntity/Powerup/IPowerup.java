
package GameModel.gameEntity.Powerup;

import GameUtilities.IObservable;
import com.jme3.math.Vector3f;

/**
 *
 * @author Garpetun
 */
public interface IPowerup extends IObservable {
    public void addPowerup();
    public void removePowerup();
    public float getMASS();

    public Vector3f getPosition();
}