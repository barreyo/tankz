
package view.effects;

import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

/**
 * Big fire effect for air call powerup missiles.
 *  
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class NapalmEffect extends AEffect implements IEffect {
    
    /**
     * Create a napalm effect.
     */
    NapalmEffect (){
        super();
        createShockwave(assetManager);
        createFlame(assetManager);
        createSpark(assetManager);
        //createSmoke(assetManager);
    }
    
    private void createShockwave(AssetManager assetManager){
        ParticleEmitter shockwave = new ParticleEmitter("Shockwave", ParticleMesh.Type.Triangle, 1);
        shockwave.setFaceNormal(Vector3f.UNIT_Y);
        shockwave.setStartColor(new ColorRGBA(.4f, 0.1f, 0.01f, 1f));
        shockwave.setEndColor(new ColorRGBA(.48f, 0.17f, 0.01f, 0f));
        
        shockwave.setStartSize(2f);
        shockwave.setEndSize(20f);
        shockwave.setParticlesPerSec(0);
        shockwave.setLowLife(0.5f);
        shockwave.setHighLife(2f);
        shockwave.setInitialVelocity(new Vector3f(0, 0, 0));
        shockwave.setVelocityVariation(0f);
        shockwave.setImagesX(1);
        shockwave.setImagesY(1);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/shockwave.png"));
        shockwave.setMaterial(mat);
        emitters.add(shockwave);
    }
    
    private void createFlame(AssetManager assetManager) {
        ParticleEmitter flame = new ParticleEmitter("Flame", ParticleMesh.Type.Point, 32);
        flame.setSelectRandomImage(true);
        flame.setStartColor(new ColorRGBA(1f, 0.4f, 0.05f, 1f));
        flame.setEndColor(new ColorRGBA(.4f, .22f, .12f, 0f));
        flame.setStartSize(5f);
        flame.setEndSize(20f);
        flame.setShape(new EmitterSphereShape(Vector3f.ZERO, 1f));
        flame.setParticlesPerSec(0);
        flame.setGravity(0, -2, 0);
        flame.setLowLife(.2f);
        flame.setHighLife(4f);
        flame.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 5, 0));
        flame.getParticleInfluencer().setVelocityVariation(1f);
        flame.setImagesX(2);
        flame.setImagesY(2);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flame.png"));
        mat.setBoolean("PointSprite", true);
        flame.setMaterial(mat);
        emitters.add(flame);
    }
    
    private void createSpark(AssetManager assetManager) {
        ParticleEmitter spark = new ParticleEmitter("Spark", ParticleMesh.Type.Triangle, 32);
        spark.setStartColor(new ColorRGBA(1f, 0.8f, 0.36f, 1f));
        spark.setEndColor(new ColorRGBA(1f, 0.8f, 0.36f, 0f));
        spark.setStartSize(.5f);
        spark.setEndSize(.5f);
        spark.setFacingVelocity(true);
        spark.setParticlesPerSec(0);
        spark.setGravity(0, 10, 0);
        spark.setLowLife(0.2f);
        spark.setHighLife(1.5f);
        spark.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 20, 0));
        spark.getParticleInfluencer().setVelocityVariation(1);
        spark.setImagesX(1);
        spark.setImagesY(1);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/spark.png"));
        spark.setMaterial(mat);
        emitters.add(spark);
    }
}
