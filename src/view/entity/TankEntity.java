package view.entity;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.effect.ParticleEmitter;
import java.beans.PropertyChangeEvent;
import java.util.Collection;
import model.IArmedVehicle;
import utilities.Commands;
import view.effects.EEffects;

/**
 * The visual game entity class of a tank.
 *
 * Connects the visual representation with its effects as well handling
 * collision shapes.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class TankEntity extends AGameEntity {

    private final IArmedVehicle armedVehicle;
    private final Collection<ParticleEmitter> shootEffects;
    private final Collection<ParticleEmitter> blownUpEffects;
    private final Collection<ParticleEmitter> smokeEffects;
    private final Collection<ParticleEmitter> flameEffects;

    /**
     * Creates a tank game entity.
     *
     * @param armedVehicle
     */
    public TankEntity(IArmedVehicle armedVehicle) {
        super(armedVehicle, EGraphics.TANK);
        this.armedVehicle = armedVehicle;
        shootEffects = EEffects.SHOOT.getEmitters();
        blownUpEffects = EEffects.TANK_BLOWN_UP.getEmitters();
        smokeEffects = EEffects.SMOKE.getEmitters();
        flameEffects = EEffects.FLAME.getEmitters();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionShape getCollisionShape() {
        return ECollisionShapes.VEHICLE.createCollisionShape();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanup() {
        super.cleanup();
        shootEffects.clear();
        blownUpEffects.clear();
        smokeEffects.clear();
        flameEffects.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Commands.SHOOT)) {
            showEffects(shootEffects, armedVehicle.getFirePosition().add(armedVehicle.getDirection().mult(1.5f)));
        } else if (evt.getPropertyName().equals(Commands.HIDE)) {
            // Remove tank from world
            this.showEffects(blownUpEffects, armedVehicle.getFirePosition());
            this.hideFromWorld();
        }  else if (evt.getPropertyName().equals(Commands.SHOW_SMOKE)) {
            showEffects(smokeEffects, armedVehicle.getExhaustPosition());
        } else if (evt.getPropertyName().equals(Commands.HIDE_SMOKE)) {
            hideEffects(smokeEffects);
        } else if (evt.getPropertyName().equals(Commands.SHOW_FLAME)) {
            showEffects(flameEffects, armedVehicle.getExhaustPosition());
        } else if (evt.getPropertyName().equals(Commands.HIDE_FLAME)) {
            hideEffects(flameEffects);
        }
        super.propertyChange(evt);
    }
}
