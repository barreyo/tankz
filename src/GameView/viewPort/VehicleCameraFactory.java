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
        chaseCam.setMaxDistance(15);
        chaseCam.setMinDistance(5);
        chaseCam.setDefaultDistance(12);
        chaseCam.setChasingSensitivity(50f);
        chaseCam.setSmoothMotion(true); //automatic following
        chaseCam.setUpVector(Vector3f.UNIT_Y);
        chaseCam.setTrailingEnabled(true);
        chaseCam.setDefaultVerticalRotation(0.3f);
        return chaseCam;
    }
}
