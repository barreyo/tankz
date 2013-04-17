/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView.gameEntity;

import GameModel.gameEntity.Powerup.IPowerupBox;
import App.TanksAppAdapter;
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

    private IPowerupBox powerup;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public PowerupEntity(IPowerupBox pow) {
        super(EGraphics.POWERUP);
        setModel(pow);
        spatial.setLocalTranslation(powerup.getPosition());
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
        pcs.firePropertyChange(pce);
        this.cleanup();
    }

    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
    
    public void setModel(IPowerupBox pow) {
        if (powerup != null) {
            this.cleanup();
        }
        powerup = pow;
        if (powerup != null) {
            powerup.addObserver(this);
        }
        attachToRootNode();
    }
        
    private void attachToRootNode() {
        TanksAppAdapter.INSTANCE.attachChildToRootNode(spatial);
    }
    
   private void updatePosition() {
        spatial.setLocalTranslation(powerup.getPosition());
    }
    
}
