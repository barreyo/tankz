/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 *
 * @author perthoresson
 */
public class LandmineModel implements IWorldObject {
    private static final int DAMAGE = 30;
    private static final float MASS = 100f;
    
    private Vector3f position;
    private Vector3f velocity;
    private Quaternion rotation;
    
    /**
     * Constructor for LandmineModel
     * 
     * @param initialPos The initial position of the landmine
     * @param initialVelocity The initial velocity of the landmine
     * @param rotation The rotation of the landmine.
     */
    public LandmineModel(Vector3f initialPos, Vector3f initialVelocity, Quaternion rotation){
        this.position = initialPos.clone();
        this.velocity = initialVelocity.clone();
        this.rotation = rotation.clone();
    }

    /**
     * 
     * @return The position of the landmine.
     */
    public Vector3f getPosition() {
        return position.clone();
    }

    /**
     * 
     * @return The velocity of the landmine.
     */
    public Vector3f getVelocity() {
        return velocity.clone();
    }

    /**
     * 
     * @return The velocity of the landmine. 
     */
    public Quaternion getRotation() {
        return rotation.clone();
    }
    
    /**
     *
     * @param pos
     */
    public void updatePosition(Vector3f pos) {
        this.position = pos.clone();
    }
    
    /**
     *
     * @param rotation
     */
    public void updateRotation(Quaternion rotation) {
        this.rotation = rotation.clone();
    }
    
    /**
     *
     * @param velocity
     */
    public void updateLinearVelocity(Vector3f velocity) {
        this.velocity = velocity.clone();
    }
    
    /**
     * 
     * @return the damage the missile does.
     */
    public int getDamageOnImpact() {
        return DAMAGE;
    }

    /**
     * 
     * @return the mass of the missile
     */
    public float getMass() {
        return MASS;
    }

    @Override
    public void showInWorld() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void hideFromWorld() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isShownInWorld() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param tpf
     */
    @Override
    public void update(float tpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     */
    @Override
    public void cleanup() {
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

    /**
     *
     * @param ex
     * @throws IOException
     */
    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param im
     * @throws IOException
     */
    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
