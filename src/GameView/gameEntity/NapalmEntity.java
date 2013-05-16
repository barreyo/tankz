
package GameView.gameEntity;

import App.TanksAppAdapter;
import GameModel.IExplodingProjectile;
import GameUtilities.Commands;
import GameView.effects.EEffects;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.effect.ParticleEmitter;
import com.jme3.math.Quaternion;
import com.jme3.scene.Spatial;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;

/**
 * The graphical representation of the Nuke powerups projectile, when it is shot.
 * 
 * Connects the visual representation with its effects as well handling
 *  collision shapes.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class NapalmEntity extends AGameEntity {
    
    private IExplodingProjectile projectile;
    private final Collection<ParticleEmitter> effects;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Instantiates the object.
     * 
     * @param proj The model of the object
     */
    public NapalmEntity(IExplodingProjectile proj) {
        super(EGraphics.NUKE);
        effects = EEffects.NUKE.getEmitters();
        
        spatial.setUserData("Model", proj);
        projectile = proj;
        
        proj.addObserver(this);
        hideFromWorld();
        TanksAppAdapter.INSTANCE.attachChildToRootNode(spatial);
    }
    
    /**
     * @inheritdoc
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }

    /**
     * @inheritdoc
     */
    @Override
    public void cleanup() {
        TanksAppAdapter.INSTANCE.detachChildFromRootNode(spatial);
        projectile.removeObserver(this);
    }

    /**
     * @inheritdoc 
     */
    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Commands.SHOW)) {
            showInWorld();
            updatePosition();
            updateRotation();
        } else if (evt.getPropertyName().equals(Commands.ROTATE)) {
            spatial.setLocalRotation((Quaternion)evt.getNewValue());
        } else if (evt.getPropertyName().equals(Commands.CLEANUP)) {
            // Clean up
            cleanup();
        } else if (evt.getPropertyName().equals(Commands.EXPLOSION_FINISHED)) {
            for (ParticleEmitter effect : effects) {
                if (effect.getParent() != null) {
                    // Remove effect from world
                    effect.killAllParticles();
                    effect.removeFromParent();
                }
            }
        } else if (evt.getPropertyName().equals(Commands.HIDE)) {
            hideFromWorld();
        }
        pcs.firePropertyChange(evt);
    }
    
    /**
     * Tells the camera to shake, removes the nuke from the world
     *  and shows an effect.
     */
    public void impact() {
        pcs.firePropertyChange(Commands.CAMERA_SHAKE, null, null);
        hideFromWorld();
        showEffects(effects, spatial.getWorldTranslation());
    }

    private void updatePosition() {
        spatial.setLocalTranslation(projectile.getInitialPosition());
    }
    
    private void updateRotation() {
        spatial.setLocalRotation(projectile.getRotation());
    }
  
    private void showInWorld() {
        spatial.setCullHint(Spatial.CullHint.Dynamic);
    }

    /**
     *
     */
    public void hideFromWorld() {
        spatial.setCullHint(Spatial.CullHint.Always);
    }

    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
}
