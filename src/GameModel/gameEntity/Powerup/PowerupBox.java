/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel.gameEntity.Powerup;

import GameModel.Game.UserSettings;
import GameUtilities.IObservable;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Garpetun
 */
public class PowerupBox implements IPowerupBox, IObservable {

    private EPowerup powerup;
    private boolean isVisible;
    private Vector3f position;
    
    public static final float MASS = 10f;
    private final float HASTE_FACTOR = 1000;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public PowerupBox(Vector3f pos) {
        powerup = EPowerup.HASTE;
        position = pos;
    }
    
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    public void addPowerup() {
        isVisible = true;
    }
    
    /**
     * Use the powerup, removing it from world.
     */
    public void removePowerup() {
        for (int i = 0; i < UserSettings.INSTANCE.getPlayers().size(); i++) {
            if (UserSettings.INSTANCE.getPlayers().get(i) != null) {
                 System.out.println("SETTING POWERUP ON ALL PLAYERS");
                 UserSettings.INSTANCE.getPlayers().get(i).setPowerup(EPowerup.HASTE);
            }
        }
        isVisible = false;
        System.out.println("REMOVING POWERUP FROM MODEL");
        pcs.firePropertyChange(null, null, null);
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getMASS() {
        return MASS;
    }
    
    
}
