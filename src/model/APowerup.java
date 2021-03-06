
package model;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import utilities.Commands;

/**
 * Abstraction of a powerup. Holds position, rotation, if the object is in the
 * world, mass etc.
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public abstract class APowerup implements IPowerup {

    private Vector3f position;
    private Quaternion rotation;
    /**
     * True if a player holds this powerup.
     */
    boolean isHeldByPlayer;
    private boolean isInWorld;
    private static final float MASS = 10f;
    /**
     * Property change support. xd
     */
    final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Simplest form of a powerup. Vector zero as position and z direction.
     */
    public APowerup() {
        position = Vector3f.ZERO;
        rotation = Quaternion.DIRECTION_Z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showInWorld() {
        boolean wasInWorld = isInWorld;
        isInWorld = true;
        pcs.firePropertyChange(Commands.SHOW, wasInWorld, isInWorld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideFromWorld() {
        boolean wasInWorld = isInWorld;
        isInWorld = false;
        pcs.firePropertyChange(Commands.HIDE, wasInWorld, isInWorld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void powerupWasPickedUp() {
        this.setHeldByPlayer(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getMass() {
        return MASS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3f getPosition() {
        return new Vector3f(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Vector3f position) {
        this.position = new Vector3f(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHeldByPlayer() {
        return isHeldByPlayer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShownInWorld() {
        return isInWorld;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHeldByPlayer(boolean held) {
        isHeldByPlayer = held;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanup() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void usePowerup(IPlayer player) {
        setHeldByPlayer(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quaternion getRotation() {
        return new Quaternion(rotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
