/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.Weapon;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Daniel
 */
public class GunSpatial implements IWeaponSpatial {
    private Spatial gun;

    /**
     * Create a default tank spatial.
     *
     * @param assetManager AssetManager used for loading assets
     */
    public GunSpatial(Spatial gun, float scale) {
        this.gun = gun;
        gun.scale(scale);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Spatial getWeaponSpatial() {
        return gun;
    }
}
