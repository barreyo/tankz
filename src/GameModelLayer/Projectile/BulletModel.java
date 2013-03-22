/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModelLayer.Projectile;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author Daniel
 */
public class BulletModel implements IProjectileModel {
    private int damage;
    
    public BulletModel(int damage) {
        this.damage = damage;
    }

    public int getDamageOnImpact() {
        return damage;
    }
}
