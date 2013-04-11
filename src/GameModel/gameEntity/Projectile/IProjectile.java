package GameModel.gameEntity.Projectile;

/**
 * Interface for a basic projectile.
 * 
 * @author Daniel
 */
public interface IProjectile {
    /**
     * Returns the damage done on impact.
     *
     * @return the damage done on impact
     */
    public int getDamageOnImpact();

    /**
     * Returns the mass of the projcetile in kg.
     *
     * @return the mass of the projectile in kg
     */
    public float getMass();
}
