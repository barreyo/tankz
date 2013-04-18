
package GameView.gameEntity;

import GameModel.gameEntity.Vehicle.IArmedVehicle;
import App.TanksAppAdapter;
import GameUtilities.Util;
import GameView.effects.EEffects;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.effect.ParticleEmitter;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;

/**
 * The visual game entity class of a tank.
 * 
 * @author Daniel
 */
public final class TankEntity extends AGameEntity {
    
    private IArmedVehicle armedVehicle;
    private final Collection<ParticleEmitter> shootEffects;
    //private final Collection<ParticleEmitter> blownUpEffects;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Creates a tank game entity.
     */
    public TankEntity(IArmedVehicle armedVehicle) {
        super(EGraphics.TANK);
        spatial.setShadowMode(RenderQueue.ShadowMode.Cast);
        shootEffects = EEffects.SHOOT.getEmitters();
        
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
        //((TanksVehicleControl)spatial.getControl(EControls.VEHICLE_CONTROL.getControl())).cleanup();
        //TankAppAdapter.INSTANCE.removeFromPhysicsSpace(vehicle);
        //spatial.removeControl(vehicle);
        //vehicle = null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IArmedVehicle.SHOOT)) {
            showShootEffect();
        }
        if (evt.getPropertyName() != null) {
            //Pass on
            pcs.firePropertyChange(evt);
        }
        if(evt.getPropertyName().equalsIgnoreCase(IArmedVehicle.VEHICLE_DESTROYED)){
            if (spatial.getParent() != null) {
                //TODO - Respawn tank at different location after some 1-2 seconds 
                // instead of removing tank.
                
                // Remove tank from world
                spatial.removeFromParent();
            }
            //Pass on
            pcs.firePropertyChange(evt);
        }
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
        TanksAppAdapter.INSTANCE.attachChildToRootNode(spatial);
    }

    private void showShootEffect() {
        if (spatial.getParent() != null) {
            for (ParticleEmitter effect : shootEffects) {
                if (effect != null) {
                    effect.setLocalTranslation(armedVehicle.getFirePosition());
                    spatial.getParent().attachChild(effect);
                    effect.emitAllParticles();
                }
            }
        }
    }
}
