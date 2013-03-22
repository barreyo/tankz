package GameModelLayer.Projectile;

/**
 * Models a basic projectile that can damage at impact.
 * 
 * @author Daniel
 */
public class BulletModel implements IProjectileModel {
    private int damage;
    private float mass;
    
    /**
     *
     * @param damage
     * @param mass
     */
    public BulletModel(int damage, float mass) {
        this.damage = damage;
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
