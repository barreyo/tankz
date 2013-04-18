/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameControllers.entitycontrols;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

/**
 *
 * @author backman
 */
public class PUBoxAnimationControl extends AbstractControl {

    public PUBoxAnimationControl(){} // empty serialization constructor
    
    @Override
    protected void controlUpdate(float tpf) {
        spatial.rotate(tpf * 0.8f, tpf * 0.7f, tpf * 0.5f);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        // Not used
    }

    public Control cloneForSpatial(Spatial spatial) {
        final PUBoxAnimationControl control = new PUBoxAnimationControl();
        control.setSpatial(spatial);
        return control;
    }

    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
    }
}
