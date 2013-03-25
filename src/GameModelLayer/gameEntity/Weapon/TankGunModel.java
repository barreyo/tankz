package GameModelLayer.gameEntity.Weapon;

import GameModelLayer.gameEntity.Projectile.IProjectile;

/**
 * A model of a Tank Gun.
 * 
 * @author Daniel
 */
public class TankGunModel implements IProjectileWeapon{
    private IProjectile projectile;

    /**
     *
     * @param model
     */
    public TankGunModel(IProjectile model) {
        if (model == null) {
            throw new NullPointerException("Model should not be null");
        }
        projectile = model;
    }
    
    /**
     *
     * @return
     */
    @Override
    public IProjectile getProjectileModel() {
        return projectile;
    }

    /**
     *
     * @param model
     */
    @Override
    public void setProjectileModel(IProjectile model) {
        if (model == null) {
            throw new NullPointerException("Model should not be null");
        }
        projectile = model;
    }  
}
