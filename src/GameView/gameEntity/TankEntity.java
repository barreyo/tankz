
package GameView.gameEntity;

import GameControllers.entitycontrols.EControls;
import GameControllers.entitycontrols.TanksVehicleControl;
import GameModel.gameEntity.Vehicle.IArmedVehicle;
import GameUtilities.TankAppAdapter;
import GameUtilities.Util;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The visual game entity class of a tank.
 * 
 * @author Daniel
 */
public final class TankEntity extends AGameEntity {
    
    private IArmedVehicle armedVehicle;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Creates a tank game entity.
     */
    public TankEntity(IArmedVehicle armedVehicle) {
        super(EGraphics.TANK);
        // Save this as user data for the spatial -> ie if you can access the spatial
        // you can access this.
        spatial.setShadowMode(RenderQueue.ShadowMode.Cast);
        
        setModel(armedVehicle);
        show();
    }

    /**
     *  @inheritdoc
     */
    @Override
    public CollisionShape getCollisionShape() {
        Geometry chasis = Util.findGeom(spatial, "Chassi");
        //Create a hull collision shape for the chassis
        CollisionShape carHull = CollisionShapeFactory.createDynamicMeshShape(chasis);
        return carHull;
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
        //vehicle = null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
    }

    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    public void setModel(IArmedVehicle vehicle) {
        if (armedVehicle != null) {
           armedVehicle.removeObserver(this);
        }
        armedVehicle = vehicle;
        if (armedVehicle != null) {
            armedVehicle.addObserver(this);
        }
    }

    public void setPosition(Vector3f pos) {
        spatial.setLocalTranslation(pos);
    }

    private void show() {
        TankAppAdapter.INSTANCE.attachChildToRootNode(spatial);
    }
}
