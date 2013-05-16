
package GameView.gameEntity;

import App.TanksAppAdapter;
import GameModel.IArmedVehicle;
import GameUtilities.Commands;
import GameView.effects.EEffects;
import GameView.graphics.EGraphics;
import GameView.physics.ECollisionShapes;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.effect.ParticleEmitter;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;

/**
 * The visual game entity class of a tank.
 * 
 * Connects the visual representation with its effects as well handling
 *  collision shapes.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class TankEntity extends AGameEntity {
    
    private IArmedVehicle armedVehicle;
    private final Collection<ParticleEmitter> shootEffects;
    private final Collection<ParticleEmitter> blownUpEffects;
    private final Collection<ParticleEmitter> smokeEffects;
    private final Collection<ParticleEmitter> flameEffects;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Creates a tank game entity.
     * 
     * @param armedVehicle 
     */
    public TankEntity(IArmedVehicle armedVehicle) {
        super(EGraphics.TANK);
        
        spatial.setUserData("Model", armedVehicle);
        
        spatial.setShadowMode(RenderQueue.ShadowMode.Cast);
        shootEffects = EEffects.SHOOT.getEmitters();
        blownUpEffects = EEffects.TANK_BLOWN_UP.getEmitters();
        smokeEffects = EEffects.SMOKE.getEmitters();
        flameEffects = EEffects.FLAME.getEmitters();
        
        setModel(armedVehicle);
        showInWorld();
    }

    /**
     *  @inheritdoc
     */
    @Override
    public CollisionShape getCollisionShape() {
        return ECollisionShapes.VEHICLE.createCollisionShape();
    }

    /**
     *  @inheritdoc
     */
    @Override
    public void cleanup() {
        setModel(null);
    }

    /**
     * @inheritdoc 
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Commands.SHOOT)) {
            showEffects(shootEffects, armedVehicle.getFirePosition());
        } else if(evt.getPropertyName().equals(Commands.HIDE)){
                // Remove tank from world
                this.showEffects(blownUpEffects, armedVehicle.getFirePosition());
                this.hideFromWorld();
        } else if (evt.getPropertyName().equals(Commands.SHOW)) {
            showInWorld();
            updatePosition();
            updateRotation();
        } else if (evt.getPropertyName().equals(Commands.CLEANUP)) {
            this.cleanup();
        } else if (evt.getPropertyName().equals(Commands.SHOW_SMOKE)) {
            showEffects(smokeEffects, armedVehicle.getExhaustPosition());
        } else if (evt.getPropertyName().equals(Commands.HIDE_SMOKE)) {
            hideEffects(smokeEffects);
        } else if (evt.getPropertyName().equals(Commands.SHOW_FLAME)) {
            showEffects(flameEffects, armedVehicle.getExhaustPosition());
        } else if (evt.getPropertyName().equals(Commands.HIDE_FLAME)) {
            hideEffects(flameEffects);
        }
        pcs.firePropertyChange(evt);
    }

    /**
     * @inheritdoc 
     */
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /**
     * Sets a model representation of an IArmedVehicle to be
     *  associated to this TankEntity.
     * 
     * @param vehicle The IArmedVehicle to be associated to this
     */
    public void setModel(IArmedVehicle vehicle) {
        if (armedVehicle != null) {
           armedVehicle.removeObserver(this);
           
        }
        armedVehicle = vehicle;
        if (armedVehicle != null) {
            armedVehicle.addObserver(this);
        }
    }

    /**
     * Sets the position of the spatial in the world.
     * 
     * @param pos The position
     */
    public void setPosition(Vector3f pos) {
        spatial.setLocalTranslation(pos);
    }

    /**
     * Toggles the spatial to be shown in the world.
     */
    private void showInWorld() {
        TanksAppAdapter.INSTANCE.attachChildToRootNode(spatial);
    }

    /**
     * Toggles the spatial to be hidden from the world.
     */
    private void hideFromWorld() {
        TanksAppAdapter.INSTANCE.detachChildFromRootNode(spatial);
    }
    
    /**
     * Updates the position of the spatial to
     *  the position of the model representation.
     */
    private void updatePosition() {
        spatial.setLocalTranslation(armedVehicle.getPosition());
    }
    
    /**
     * Updates the rotation of the spatial to
     *  the rotation of the model representation.
     */
    private void updateRotation() {
        spatial.setLocalRotation(armedVehicle.getRotation());
    }
    
    /**
     * Shows the effects that this methods gets in, at the position given.
     * 
     * @param effects The effects that gets shown
     * @param position The position at which the effects get shown
     */
    private synchronized void showEffects(Collection<ParticleEmitter> effects, Vector3f position) {
        if (spatial.getParent() != null) {
            for (ParticleEmitter effect : effects) {
                if (effect != null) {
                    effect.setLocalTranslation(position);
                    spatial.getParent().attachChild(effect);
                    effect.emitAllParticles();
                }
            }
        }
    }
    
    /**
     * Hides the given effects.
     * 
     * @param effects The effects to be hidden
     */
    private synchronized void hideEffects(Collection<ParticleEmitter> effects) {
        if (spatial.getParent() != null) {
            for (ParticleEmitter effect : effects) {
                if (effect != null) {
                    spatial.getParent().detachChild(effect);
                }
            }
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    public void impact() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
