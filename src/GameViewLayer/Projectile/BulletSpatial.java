/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.Projectile;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author Daniel
 */
public class BulletSpatial implements IProjectileSpatial{
        
    private AssetManager assetManager;
    
    private Material matBullet;
    private Sphere bullet;
    private SphereCollisionShape bulletCollisionShape;
    
    public BulletSpatial(AssetManager assetManager) {
        this.assetManager = assetManager;
        prepareBullet();
    }
    
    private void prepareBullet() {
        bullet = new Sphere(32, 32, 0.4f, true, false);
        bullet.setTextureMode(Sphere.TextureMode.Projected);
        bulletCollisionShape = new SphereCollisionShape(0.4f);
        matBullet = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matBullet.setColor("Color", ColorRGBA.Green);
        matBullet.setColor("GlowColor", ColorRGBA.Green);
        // osäker angående clone
        bulletGeom = new Geometry("bullet", bullet);
        bulletGeom.setMaterial(matBullet);
        bulletGeom.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
    }
    
    private Geometry bulletGeom;

    public Spatial getProjectileSpatial() {
        return bulletGeom.clone();
    }

    public SphereCollisionShape getProjectileCollisionShape() {
        return bulletCollisionShape;
    }
    
}
