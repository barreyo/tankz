/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.gameEntity.Weapon;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * A visual representation of a weapon.
 *
 * @author Daniel
 */
public class GunSpatial implements IWeaponSpatial {
    private Spatial gun;
    private Vector3f direction;
    private Vector3f position;

    /**
     * Create a default tank spatial.
     *
     * @param gun
     * @param scale  
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

    public Vector3f getAttackDirection() {
        return direction;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setAttackDirection(Vector3f direction) {
        this.direction = direction;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }
}
