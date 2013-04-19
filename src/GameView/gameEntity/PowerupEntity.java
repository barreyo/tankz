
package GameView.gameEntity;

import App.TanksAppAdapter;
import GameModel.IPowerup;
import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Garpetun
 */
public class PowerupEntity extends AGameEntity {

    private IPowerup powerup;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public PowerupEntity(IPowerup pow) {
        super(EGraphics.POWERUP);
        setModel(pow);
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
        if (spatial.getParent() != null) {
            // Remove ourself from world
            spatial.removeFromParent();
        }
        powerup.removeObserver(this);
    }

    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(IPowerup.SHOW)) {
            showInWorld();
        } else if (pce.getPropertyName().equals(IPowerup.HIDE)) {
            hideFromWorld();
        }
        pcs.firePropertyChange(pce);
    }

    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
    
    public void setModel(IPowerup pow) {
        if (powerup != null) {
            this.cleanup();
        }
        powerup = pow;
        if (powerup != null) {
            powerup.addObserver(this);
        }
    }
        
    private void showInWorld() {
        spatial.setLocalTranslation(powerup.getPosition());
        TanksAppAdapter.INSTANCE.attachChildToRootNode(spatial);
    }
    
   private void updatePosition() {
        spatial.setLocalTranslation(powerup.getPosition());
    }

    private void hideFromWorld() {
        if (spatial.getParent() != null) {
            spatial.removeFromParent();
        }
    }
}
