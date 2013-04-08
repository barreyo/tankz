
package GameViewLayer.viewPort;

import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;

/**
 * A camera that is used to follow a vehicle.
 * 
 * It uses the abilities of the default chase camera but
 * calculates the horizontal direction vector of the camera diffrently.
 * 
 * @author Daniel
 */
public class VehicleCamera extends ChaseCamera {
    
    /**
     * {@inheritdoc}
     */
    public VehicleCamera(Camera cam, final Spatial target) {
        super(cam, target);
    }

    /**
     * {@inheritdoc}
     */
    public VehicleCamera(Camera cam) {
        super(cam);
    }

    /**
     * {@inheritdoc}
     */
    public VehicleCamera(Camera cam, InputManager inputManager) {
        super(cam, inputManager);
    }

    /**
     * {@inheritdoc}
     */
    public VehicleCamera(Camera cam, final Spatial target, InputManager inputManager) {
        super(cam, target, inputManager);
    }
    
    private Vector3f horLookAt;
    
    /**
     * Sets the horizontal vector that is used to specify the horizontal direction of the camera.
     * 
     * @param lo 
     */
    public void setHorizonalLookAt(Vector3f lo) {
        horLookAt = lo.normalizeLocal();
    }
   
    /**
     * {@inheritdoc}
     */
    protected void computePosition() {
        if (horLookAt == null) 
            return;
        float hDistance = (distance) * FastMath.sin((FastMath.PI / 2) - vRotation);
        pos.set(-hDistance * horLookAt.x, (distance) * FastMath.sin(vRotation), -hDistance * horLookAt.z);
        pos.addLocal(target.getWorldTranslation());
    }
}
