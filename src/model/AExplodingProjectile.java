package model;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 *
 * @author Daniel
 */
public abstract class AExplodingProjectile extends AExplodingObject implements IExplodingProjectile {
    
    Vector3f linearVelocity;

    /**
     *
     */
    public AExplodingProjectile() {
        this.linearVelocity = Vector3f.ZERO;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void launchProjectile(Vector3f initialPos, Vector3f initialVelocity, Quaternion initialRotation, IPlayer player) {
        this.initialPos = new Vector3f(initialPos);
        this.linearVelocity =  new Vector3f(initialVelocity);
        this.rotation =  new Quaternion(initialRotation);
        this.launcherPlayer = player;
        showInWorld();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3f getLinearVelocity() {
        return new Vector3f(linearVelocity);
    }
}
