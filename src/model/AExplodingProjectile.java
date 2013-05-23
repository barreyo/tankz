package model;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public abstract class AExplodingProjectile extends AExplodingObject implements IExplodingProjectile {
    
    Vector3f linearVelocity;
    
     AExplodingProjectile(float mass, int damage, int explosionEndMS, int lifeTimeMS) {
         super(mass, damage, explosionEndMS, lifeTimeMS);
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
