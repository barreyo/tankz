
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
 * The fundamentals of an exploding projectile. Hold position, damange, mass
 * etc. Implements IExplodingObject.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public abstract class AExplodingObject implements IExplodingObject{
    
    /**
     * Starting position of the object.
     */
    Vector3f initialPos;
    
    /**
     * Position of the object.
     */
    Vector3f position;
    
    /**
     * The rotation of the object.
     */
    Quaternion rotation;
    
    /**
     * True if the object is currently displayed in the world.
     */
    boolean isInWorld;
    
    /**
     * Is the object currently exploding.
     */
    boolean exploding;
    
    private boolean hasDoneDamage;
    private final float mass;
    private final int damage;
    private final int explosionEndMS;
    private final int lifeTimeMS;
    
    private long lifeTimerStart;
    
    private long explodingTimerStart;
    
    /**
     * The player launching or placing the object, ie the player who will get a
     * kill if somebody dies from this object. 
     */
    IPlayer launcherPlayer;
    
    final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    /**
     * Create an exploding object.
     */
    public AExplodingObject(float mass, int damage, int explosionEndMS, int lifeTimeMS){
        this.mass = mass;
        this.damage = damage;
        this.explosionEndMS = explosionEndMS;
        this.lifeTimeMS = lifeTimeMS;
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
        hasDoneDamage = false;
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
                if (System.currentTimeMillis() - explodingTimerStart >= explosionEndMS) {
                    exploding = false;
                    pcs.firePropertyChange(Commands.EXPLOSION_FINISHED, null, null);
                }
            } else {
                if (System.currentTimeMillis() - lifeTimerStart >= lifeTimeMS) {
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doDamageOn(IDamageableObject damageableObject) {
        if (!hasDoneDamage) {
            if (damageableObject.applyDamageToKill(damage)) {
                if (launcherPlayer != null && damageableObject != launcherPlayer.getVehicle()) {
                    launcherPlayer.incrementKills();
                }
            }
            hasDoneDamage = true;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public float getMass() {
        return mass;
    }
}
