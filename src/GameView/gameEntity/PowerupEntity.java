
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
 *
 * @author Garpetun
 */
public class PowerupEntity extends AGameEntity {
    private final IPowerup powerup;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     *
     * @param pow
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

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(Commands.SHOW)) {
            showInWorld();
        } else if (pce.getPropertyName().equals(Commands.CLEANUP)) {
            cleanup();
        }
        pcs.firePropertyChange(pce);
    }

    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    private void showInWorld() {
        spatial.setLocalTranslation(powerup.getPosition());
        spatial.setCullHint(CullHint.Dynamic);
    }

    /**
     *
     */
    public void hideFromWorld() {
        spatial.setCullHint(CullHint.Always);
    }
}
