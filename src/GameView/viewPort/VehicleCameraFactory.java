package GameView.viewPort;

import GameUtilities.TankAppAdapter;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;

/**
 *
 * @author Daniel
 */
public final class VehicleCameraFactory {
    
    private VehicleCameraFactory() {}
   
    public static VehicleCamera getVehicleChaseCamera(Camera cam, Spatial spatial) {
        VehicleCamera chaseCam = new VehicleCamera(cam, spatial, TankAppAdapter.INSTANCE.getInputManager());
        chaseCam.setMaxDistance(20);
        chaseCam.setMinDistance(10);
        chaseCam.setDefaultDistance(15);
        chaseCam.setChasingSensitivity(5f);
        chaseCam.setSmoothMotion(true); //automatic following
        chaseCam.setUpVector(Vector3f.UNIT_Y);
        chaseCam.setTrailingEnabled(true);
        chaseCam.setDefaultVerticalRotation(0.3f);
        return chaseCam;
    }
}
