/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.controls;

import GameLogicLayer.Game.TanksGame;
import GameModelLayer.Game.GameState;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.io.IOException;

/**
 * A Basic control used to hold common data and functinality of controls.
 *
 * @author Daniel
 */
public abstract class BaseControl implements Control {
    
    TanksGame app;
    
    boolean enabled = true;

    Spatial spatial;

    /**
     * Creates a basecontrol. Only visible in same package.
     */
    BaseControl() {
        app = TanksGame.getApp();
    }

    /**
     * Registers a spatial to control.
     * 
     * @param spatial The spatial to control
     * @throws IllegalStateException if controller already been added to a spatial
     */
    @Override
    public void setSpatial(Spatial spatial) {
        if (this.spatial != null && spatial != null) {
            throw new IllegalStateException("This control has already been added to a Spatial");
        }
        this.spatial = spatial;
    }

    /**
     * Returns the spatial under control.
     * 
     * @return spatial under control
     */
    public Spatial getSpatial() {
        return spatial;
    }

    /**
     * Sets if the control is enabled or disabled.
     * 
     * @param enabled Toggles enabling of control.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns if the control is enabled.
     * 
     * @return if the control is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Used to release resources.
     */
    public void cleanup() {
        spatial.removeControl(this);
    }

    /**
     * To be implemented in subclass.
     * 
     * @param tpf 
     */
    abstract void controlUpdate(float tpf);

    /**
     * @inheritdoc
     */
    @Override
    public void update(float tpf) {
        if (!enabled || GameState.getGameState() != GameState.RUNNING) {
            return;
        }

        controlUpdate(tpf);
    }

    /**
     * @param rm 
     * @param vp 
     * @inheritdoc
     */
    @Override
    public void render(RenderManager rm, ViewPort vp) {
        if (!enabled) {
            return;
        }
    }

    /**
     * @param ex 
     * @throws IOException 
     * @inheritdoc
     */
    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule oc = ex.getCapsule(this);
        oc.write(enabled, "enabled", true);
        oc.write(spatial, "spatial", null);
    }

    /**
     * @param im 
     * @throws IOException 
     * @inheritdoc
     */
    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule ic = im.getCapsule(this);
        enabled = ic.readBoolean("enabled", true);
        spatial = (Spatial) ic.readSavable("spatial", null);
    }

    /**
     * NOT supported
     * @param spatial 
     * @return 
     */
    @Override
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
