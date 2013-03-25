package GameModelLayer.gameEntity.Projectile;

/**
 * Models a basic projectile that can damage at impact.
 * 
 * @author Daniel
 */
public class ProjectileModel implements IProjectile {
    private int damage;
    private float mass;
    
    /**
     *
     * @param damage
     * @param mass
     */
    public ProjectileModel(int damage, float mass) {
        this.damage = damage;
        this.mass = mass;
    }

    /**
     *
     * @return
     */
    public int getDamageOnImpact() {
        return damage;
    }

    /**
     *
     * @return
     */
    public float getMass() {
        return mass;
    }
}
