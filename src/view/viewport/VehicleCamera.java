
package view.viewport;

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
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class VehicleCamera extends ChaseCamera {

    /**
     * Instatiate a vehicle chase camera.
     * 
     * @param cam viewport camera.
     * @param inputManager inputmanager of the game.
     * @param target spatial to follow.
     */
    public VehicleCamera(Camera cam, final Spatial target,
                                InputManager inputManager) {
        super(cam, target, inputManager);
    }
    
    private Vector3f horLookAt;
    
    /**
     * Sets the horizontal vector that is used to specify
     * the horizontal direction of the camera.
     * 
     * @param lo direction to look at.
     */
    public void setHorizonalLookAt(Vector3f lo) {
        horLookAt = lo.normalizeLocal();
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    protected void computePosition() {
        if (horLookAt == null) 
            return;
        float hDistance = (distance)
                * FastMath.sin((FastMath.PI / 2) - vRotation);
        pos.set(-hDistance * horLookAt.x, (distance) * FastMath.sin(vRotation),
                                                     -hDistance * horLookAt.z);
        pos.addLocal(target.getWorldTranslation());
    }
}
