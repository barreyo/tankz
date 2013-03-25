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
 *
 * @author Daniel
 */
public abstract class BaseControl implements Control {
    
    protected TanksGame app;
    protected boolean enabled = true;
    protected Spatial spatial;
    
    protected BaseControl() {
        app = TanksGame.getApp();
    }

    @Override
    public void setSpatial(Spatial spatial) {
        if (this.spatial != null && spatial != null) {
            throw new IllegalStateException("This control has already been added to a Spatial");
        }
        this.spatial = spatial;
    }

    public Spatial getSpatial() {
        return spatial;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void cleanup() {
        spatial.removeControl(this);
    }

    /**
     * To be implemented in subclass.
     */
    protected abstract void controlUpdate(float tpf);

    @Override
    public void update(float tpf) {
        if (!enabled || GameState.getGameState() != GameState.RUNNING) {
            return;
        }

        controlUpdate(tpf);
    }

    @Override
    public void render(RenderManager rm, ViewPort vp) {
        if (!enabled) {
            return;
        }
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule oc = ex.getCapsule(this);
        oc.write(enabled, "enabled", true);
        oc.write(spatial, "spatial", null);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule ic = im.getCapsule(this);
        enabled = ic.readBoolean("enabled", true);
        spatial = (Spatial) ic.readSavable("spatial", null);
    }

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
