
package model;

import utilities.Commands;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

/**
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public abstract class AExplodingObject implements IExplodingObject{
    
    Vector3f initialPos;
    Vector3f position;
    Quaternion rotation;
    boolean isInWorld;
    boolean exploding;
    
    private long lifeTimerStart;
    private long explodingTimerStart;
    
    IPlayer launcherPlayer;
    
    final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Constructor for LandmineModel
     */
    public AExplodingObject(){
        this.initialPos = Vector3f.ZERO;
        this.position = Vector3f.ZERO;
        this.rotation = Quaternion.ZERO;
        isInWorld = false;
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
    public Vector3f getInitialPosition() {
        return new Vector3f(initialPos);
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
    public void showInWorld() {
        exploding = false;
        isInWorld = true;
        lifeTimerStart = System.currentTimeMillis();
        pcs.firePropertyChange(Commands.SHOW, null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideFromWorld() {
        isInWorld = false;
        pcs.firePropertyChange(Commands.HIDE, null, null);
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
    public void update(float tpf) {
        if (isInWorld) {
            if (exploding) {
                if (System.currentTimeMillis() - explodingTimerStart >= this.getExplosionEndTime()) {
                    exploding = false;
                    pcs.firePropertyChange(Commands.EXPLOSION_FINISHED, null, null);
                }
            } else {
                if (System.currentTimeMillis() - lifeTimerStart >= this.getLifeTime()) {
                    hideFromWorld();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanup() {
        pcs.firePropertyChange(Commands.CLEANUP, null, null);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Vector3f pos) {
        this.position = new Vector3f(pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void impact() {
        exploding = true;
        explodingTimerStart = System.currentTimeMillis();
        hideFromWorld();
    }
}
