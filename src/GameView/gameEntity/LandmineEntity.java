/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView.gameEntity;

import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.CollisionShape;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author perthoresson
 */
public class LandmineEntity extends AGameEntity {
    
    /**
     *
     */
    public LandmineEntity(){
        super(EGraphics.LANDMINE);
    }

    @Override
    public CollisionShape getCollisionShape() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cleanup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addObserver(PropertyChangeListener l) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeObserver(PropertyChangeListener l) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
