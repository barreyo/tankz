package GameModel;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Daniel
 */
public class BombModel implements IExplodingProjectile {

    @Override
    public int getDamageOnImpact() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getMass() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(float tpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector3f getPosition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Quaternion getRotation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void impact() {
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

    @Override
    public void showInWorld() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void hideFromWorld() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cleanup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
