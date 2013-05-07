
package GameView.gameEntity;

import GameModel.IArmedVehicle;
import App.TanksAppAdapter;
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
 * @author Daniel
 */
public final class TankEntity extends AGameEntity {
    
    private IArmedVehicle armedVehicle;
    private final Collection<ParticleEmitter> shootEffects;
    private final Collection<ParticleEmitter> blownUpEffects;
    private final Collection<ParticleEmitter> smokeEffects;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Creates a tank game entity.
     * @param armedVehicle 
     */
    public TankEntity(IArmedVehicle armedVehicle) {
        super(EGraphics.TANK);
        
        spatial.setUserData("Model", armedVehicle);
        
        spatial.setShadowMode(RenderQueue.ShadowMode.Cast);
        shootEffects = EEffects.SHOOT.getEmitters();
        blownUpEffects = EEffects.TANK_BLOWN_UP.getEmitters();
        smokeEffects = EEffects.SMOKE.getEmitters();
        
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Commands.SHOOT)) {
            showShootingEffects(shootEffects);
        } else if(evt.getPropertyName().equals(Commands.HIDE)){
                // Remove tank from world
                this.showShootingEffects(blownUpEffects);
                this.hideFromWorld();
        } else if (evt.getPropertyName().equals(Commands.SHOW)) {
            this.showInWorld();
        } else if (evt.getPropertyName().equals(Commands.CLEANUP)) {
            this.cleanup();
        }
        if (evt.getPropertyName().equals(Commands.SMOKE)) {
            showSmokeEffects(smokeEffects);
        }
        pcs.firePropertyChange(evt);
    }

    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /**
     *
     * @param vehicle
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
     *
     * @param pos
     */
    public void setPosition(Vector3f pos) {
        spatial.setLocalTranslation(pos);
    }

    private void showInWorld() {
        spatial.setLocalTranslation(armedVehicle.getPosition());
        TanksAppAdapter.INSTANCE.attachChildToRootNode(spatial);
    }

    private void hideFromWorld() {
        TanksAppAdapter.INSTANCE.detachChildFromRootNode(spatial);
    }

    private synchronized void showShootingEffects(Collection<ParticleEmitter> effects) {
         for (ParticleEmitter effect : effects) {
            if (effect != null) {
                effect.setLocalTranslation(armedVehicle.getFirePosition());
                TanksAppAdapter.INSTANCE.attachChildToRootNode(effect);
                effect.emitAllParticles();
            }
        }
    }
    
    private synchronized void showSmokeEffects(Collection<ParticleEmitter> effects) {
        if (spatial.getParent() != null) {
            for (ParticleEmitter effect : effects) {
                if (effect != null) {
                    effect.setLocalTranslation(armedVehicle.getExhaustPosition());
                    spatial.getParent().attachChild(effect);
                    effect.emitAllParticles();
                }
            }
        }
    }
}
