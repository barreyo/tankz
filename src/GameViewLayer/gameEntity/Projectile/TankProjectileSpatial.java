/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.gameEntity.Projectile;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;

/**
 * Visual representation of a tankprojectile in the 3d room.
 * 
 * @author Daniel, Per, Johan, Albin
 */
public class TankProjectileSpatial implements IProjectileSpatial{
    
    private SphereCollisionShape bulletCollisionShape;
    private Geometry bulletGeom;
    
    /**
     * Creates a tankprojectile.
     *
     * @param assetManager Used for managing assets
     * @param radius  
     */
    public TankProjectileSpatial(AssetManager assetManager, float radius) {
        prepareTankProjectile(assetManager, radius);
    }
    
    private void prepareTankProjectile(AssetManager assetManager, float radius) {
        // Create a sphere mesh and a matching collisionshape
        Sphere bullet = new Sphere(32, 32, radius, true, false);
        bullet.setTextureMode(Sphere.TextureMode.Projected);
        bulletCollisionShape = new SphereCollisionShape(radius);
        
        // Create a geometry spatial of the mesh and apply a glowing material to it
        bulletGeom = new Geometry("bullet", bullet);
        Material matBullet = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matBullet.setColor("Color", ColorRGBA.Green);
        matBullet.setColor("GlowColor", ColorRGBA.Green);
        bulletGeom.setMaterial(matBullet);
        bulletGeom.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Spatial getProjectileSpatial() {
        return bulletGeom.clone();
    }

    /**
     * @inheritdoc
     */
    @Override
    public CollisionShape getProjectileCollisionShape() {
        return bulletCollisionShape;
    }
    
}
