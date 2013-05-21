package view.effects;

import application.TanksAppAdapter;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import model.IArmedVehicle;
import model.IPlayer;
import utilities.Commands;

/**
 * The ring that appears in front of a player when they get the AirStrikePowerup.
 * Uses Circle3D to paint the ring
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class AirCallIndicator implements PropertyChangeListener {
    
    private Circle3D circle;
    private Geometry g;
    private IArmedVehicle vehicle;
    private IPlayer player;
    
    /**
     * Instantiates the object.
     * Creates the ring in front of the player
     * 
     * @param player The player that uses the powerup
     */
    public AirCallIndicator(IPlayer player) {
        this.vehicle = player.getVehicle();
        this.player = player;
        circle = new Circle3D(14f);
        g = new Geometry("quad", circle);
        //g.rotate(-FastMath.HALF_PI, 0, 0);
        g.setMaterial(TanksAppAdapter.INSTANCE.getAssetManager().loadMaterial("Common/Materials/RedColor.j3m"));
        vehicle.addObserver(this);
        this.player.addObserver(this);
    }
    
    private void show() {
        TanksAppAdapter.INSTANCE.attachChildToRootNode(g);
    }
    
    private void hide() {
        TanksAppAdapter.INSTANCE.detachChildFromRootNode(g);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getSource() instanceof IArmedVehicle || pce.getSource() instanceof IPlayer) {
            String propertyName = pce.getPropertyName();
            if (propertyName.equals(Commands.SHOW_AIRCALL)) {
                this.show();
            }
            if (propertyName.equals(Commands.HIDE_AIRCALL)) {
                this.hide();
            }
            if (propertyName.equals(Commands.AIRCALL)) {
                Vector3f launchOrigin = new Vector3f(vehicle.getPosition()).add(vehicle.getDirection().normalize().mult(50));
                g.setLocalTranslation(launchOrigin.x, 0.1f, launchOrigin.z);
            }
        }
    }
}
