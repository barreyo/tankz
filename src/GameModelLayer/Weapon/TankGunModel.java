package GameModelLayer.Weapon;

import GameModelLayer.Projectile.IProjectileModel;

/**
 * A model of a Tank Gun.
 * 
 * @author Daniel
 */
public class TankGunModel implements IProjectileWeaponModel{
    private IProjectileModel projectile;

    /**
     *
     * @param model
     */
    public TankGunModel(IProjectileModel model) {
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
    public IProjectileModel getProjectileModel() {
        return projectile;
    }

    /**
     *
     * @param model
     */
    @Override
    public void setProjectileModel(IProjectileModel model) {
        if (model == null) {
            throw new NullPointerException("Model should not be null");
        }
        projectile = model;
    }  
}
