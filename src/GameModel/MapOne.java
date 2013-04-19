/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import GameControllers.TanksFactory;
import com.jme3.math.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Garpetun
 */
public class MapOne implements IMap {
    private final List playerSpawningPoints = new ArrayList<PlayerSpawningPoint>();
    private final List powerupSpawningPoints = new ArrayList<PowerupSpawningPoint>();
  
    public MapOne() {
        playerSpawningPoints.add(new PlayerSpawningPoint(false, new Vector3f(10,2,10)));
        
        powerupSpawningPoints.add(new PowerupSpawningPoint(false, new Vector3f(20,1.5f,20)));
    }

    @Override
    public List<PlayerSpawningPoint> getPlayerSpawningPoints() {
        return playerSpawningPoints;
    }

    @Override
    public List<PowerupSpawningPoint> getPowerupSpawningPoints() {
        return powerupSpawningPoints;
    }
}
