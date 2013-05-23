
package model;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * An exploding projectile adds linear velocity to an exploding object.
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public abstract class AExplodingProjectile extends AExplodingObject implements IExplodingProjectile {

    /**
     * The velocity of the projectile.
     */
    Vector3f linearVelocity;

    /**
     * Creates a exploding projectile.
     * 
     * @param mass mass of the object. Used for physics.
     * @param damage damage of the projectile.
     * @param explosionEndMS how long the explosion will last.
     * @param lifeTimeMS how long this object will stay in the world.
     */
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
        this.linearVelocity = new Vector3f(initialVelocity);
        this.rotation = new Quaternion(initialRotation);
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
