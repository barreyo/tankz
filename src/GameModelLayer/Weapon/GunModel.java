/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModelLayer.Weapon;

import GameModelLayer.Projectile.IProjectileModel;

/**
 *
 * @author Daniel
 */
public class GunModel implements IProjectileWeaponModel{
    private IProjectileModel projectile;

    public GunModel(IProjectileModel model) {
        setProjectileModel(model);
    }
    
    @Override
    public IProjectileModel getProjectileModel() {
        return projectile;
    }

    @Override
    public void setProjectileModel(IProjectileModel model) {
        if (model == null) {
            throw new NullPointerException("Model should not be null");
        }
        projectile = model;
    }  
}
