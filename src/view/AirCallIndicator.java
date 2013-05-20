/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import application.TanksAppAdapter;
import model.IArmedVehicle;
import model.IPlayer;
import utilities.Commands;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author backman
 */
public class AirCallIndicator implements PropertyChangeListener {
    
    private Circle3D circle;
    private Geometry g;
    private IArmedVehicle vehicle;
    private IPlayer player;
    
    public AirCallIndicator(IPlayer player) {
        this.vehicle = player.getVehicle();
        this.player = player;
        circle = new Circle3D(18f);
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
