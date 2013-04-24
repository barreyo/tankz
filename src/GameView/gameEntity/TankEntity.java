
package GameView.gameEntity;

import GameModel.IArmedVehicle;
import App.TanksAppAdapter;
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
        if (evt.getPropertyName().equals(IArmedVehicle.SHOOT)) {
            showShootingEffects(shootEffects);
        } else if(evt.getPropertyName().equals(IArmedVehicle.HIDE)){
                // Remove tank from world
                this.showShootingEffects(blownUpEffects);
                this.hideFromWorld();
        } else if (evt.getPropertyName().equals(IArmedVehicle.SHOW)) {
            this.showInWorld();
        } else if (evt.getPropertyName().equals(IArmedVehicle.CLEANUP)) {
            this.cleanup();
        }
        if (evt.getPropertyName().equals(IArmedVehicle.SMOKE)) {
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

    private void showInWorld() {
        spatial.setLocalTranslation(armedVehicle.getPosition());
        TanksAppAdapter.INSTANCE.attachChildToRootNode(spatial);
    }
    
    private void hideFromWorld() {
        if (spatial.getParent() != null) {
            spatial.removeFromParent();
        }
    }

    private synchronized void showShootingEffects(Collection<ParticleEmitter> effects) {
        if (spatial.getParent() != null) {
            for (ParticleEmitter effect : effects) {
                if (effect != null) {
                    effect.setLocalTranslation(armedVehicle.getFirePosition());
                    spatial.getParent().attachChild(effect);
                    effect.emitAllParticles();
                }
            }
        }
    }
    
    private synchronized void showSmokeEffects(Collection<ParticleEmitter> effects) {
        if (spatial.getParent() != null) {
            for (ParticleEmitter effect : effects) {
                if (effect != null) {
                    effect.setLocalTranslation(armedVehicle.getSmokePosition());
                    spatial.getParent().attachChild(effect);
                    effect.emitAllParticles();
                }
            }
        }
    }
}
