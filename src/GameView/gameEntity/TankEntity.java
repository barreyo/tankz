
package GameView.gameEntity;

import GameControllers.entitycontrols.EControls;
import GameControllers.entitycontrols.TanksVehicleControl;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.export.Savable;
import com.jme3.renderer.queue.RenderQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * The visual game entity class of a tank.
 * 
 * @author Daniel
 */
public class TankEntity extends AGameEntity implements Savable {

    /**
     * Creates a tank game entity.
     */
    public TankEntity() {
        super(EGraphics.TANK);
        // Save this as user data for the spatial -> ie if you can access the spatial
        // you can access this.
        spatial.setShadowMode(RenderQueue.ShadowMode.Cast);
        spatial.setUserData("entity", this);
    }

    /**
     *  @inheritdoc
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }

    /**
     *  @inheritdoc
     */
    @Override
    public void cleanup() {
        // TODO Change
        ((TanksVehicleControl)spatial.getControl(EControls.VEHICLE_CONTROL.getControl())).cleanup();
        //TankAppAdapter.INSTANCE.removeFromPhysicsSpace(vehicle);
        //spatial.removeControl(vehicle);
        spatial.setUserData("entity", null);
        //vehicle = null;
    }

    /**
     *
     * @param e
     * @throws IOException
     */
    @Override
    public void write(JmeExporter e) throws IOException {
        OutputCapsule capsule = e.getCapsule(this);
    }

    /**
     *
     * @param e
     * @throws IOException
     */
    @Override
    public void read(JmeImporter e) throws IOException {
        InputCapsule capsule = e.getCapsule(this);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addObserver(PropertyChangeListener l) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeObserver(PropertyChangeListener l) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
