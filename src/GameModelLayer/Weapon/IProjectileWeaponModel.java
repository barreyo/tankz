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
public interface IProjectileWeaponModel extends IWeaponModel{
    IProjectileModel getProjectileModel();
    void setProjectileModel(IProjectileModel model);
}
