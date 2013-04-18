package GameControllers.entitycontrols;

import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.RigidBodyControl;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Daniel
 */
public class BombControl extends RigidBodyControl implements PhysicsCollisionListener, PropertyChangeListener {

    @Override
    public void collision(PhysicsCollisionEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
       @Override
    public void update(float tpf) {
        super.update(tpf);
        if (enabled) {
 
        } 
    }
}
