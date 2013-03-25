/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModelLayer.gameEntity.Weapon;

import GameModelLayer.gameEntity.Projectile.IProjectile;

/**
 * 
 *
 * @author Daniel
 */
public interface IProjectileWeapon extends IWeapon{
    
    /**
     *
     * @return
     */
    IProjectile getProjectileModel();
    /**
     *
     * @param model
     */
    void setProjectileModel(IProjectile model);
}
