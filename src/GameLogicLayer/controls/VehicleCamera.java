/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.controls;

import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;

/**
 *
 * @author Daniel
 */
public class VehicleCamera extends ChaseCamera {
    
    /**
     * Constructs the chase camera
     * @param cam the application camera
     * @param target the spatial to follow
     */
    public VehicleCamera(Camera cam, final Spatial target) {
        super(cam, target);
    }

    /**
     * Constructs the chase camera
     * if you use this constructor you have to attach the cam later to a spatial
     * doing spatial.addControl(chaseCamera);
     * @param cam the application camera
     */
    public VehicleCamera(Camera cam) {
        super(cam);
    }

    /**
     * Constructs the chase camera, and registers inputs
     * if you use this constructor you have to attach the cam later to a spatial
     * doing spatial.addControl(chaseCamera);
     * @param cam the application camera
     * @param inputManager the inputManager of the application to register inputs
     */
    public VehicleCamera(Camera cam, InputManager inputManager) {
        super(cam, inputManager);
    }

    /**
     * Constructs the chase camera, and registers inputs
     * @param cam the application camera
     * @param target the spatial to follow
     * @param inputManager the inputManager of the application to register inputs
     */
    public VehicleCamera(Camera cam, final Spatial target, InputManager inputManager) {
        super(cam, target, inputManager);
    }
    
    private Vector3f horLookAt;
    
    public void setHorizonalLookAt(Vector3f lo) {
        horLookAt = lo.normalizeLocal();
    }
   
    
    protected void computePosition() {
        if (horLookAt == null) 
            return;
        float hDistance = (distance) * FastMath.sin((FastMath.PI / 2) - vRotation);
        pos.set(-hDistance * horLookAt.x, (distance) * FastMath.sin(vRotation), -hDistance * horLookAt.z);
        pos.addLocal(target.getWorldTranslation());
    }
}
