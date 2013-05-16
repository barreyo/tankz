
package GameView.gameEntity;

import App.TanksAppAdapter;
import GameModel.IPowerup;
import GameUtilities.Commands;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.scene.Spatial.CullHint;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The graphical representation of the powerups, when out in the world.
 * 
 * Connects the visual representation with its effects as well handling
 *  collision shapes.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class PowerupEntity extends AGameEntity {
    
    private final IPowerup powerup;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Instantiates the object.
     * 
     * @param pow The powerup representated
     */
    public PowerupEntity(IPowerup pow) {
        super(EGraphics.POWERUP);
        
        spatial.setUserData("Model", pow);
        powerup = pow;
        powerup.addObserver(this);
        
        // Always start out hidden
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
        powerup.removeObserver(this);
    }

    /**
     * @inheritdoc 
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(Commands.SHOW)) {
            showInWorld();
        } else if (pce.getPropertyName().equals(Commands.CLEANUP)) {
            cleanup();
        }
        pcs.firePropertyChange(pce);
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
     * Shows the powerup in the world.
     */
    private void showInWorld() {
        spatial.setLocalTranslation(powerup.getPosition());
        spatial.setCullHint(CullHint.Dynamic);
    }

    /**
     * Hides the powerup from the world.
     */
    public void hideFromWorld() {
        spatial.setCullHint(CullHint.Always);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void impact() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
