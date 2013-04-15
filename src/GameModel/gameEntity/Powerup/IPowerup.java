/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel.gameEntity.Powerup;

import GameModel.Game.PowerupSpawningPoint;
import GameModel.IObservable;
import GameModel.Player.IPlayer;
import GameModel.gameEntity.Vehicle.IArmedVehicle;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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