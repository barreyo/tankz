/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.effects;

import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

/**
 * The fiery effect that appears behind a tank when it uses haste powerup.
 * Could of course be used elsewhere.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class FlameEffect extends AEffect implements IEffect {
    
    /**
     * Instantiates the object.
     * Creates the flame
     */
    FlameEffect() {
        super();
        createFlame(assetManager);
    }
    
    private void createFlame(AssetManager assetManager) {
        ParticleEmitter flame = new ParticleEmitter("Flame", ParticleMesh.Type.Triangle, 30);
        flame.setNumParticles(80);
        flame.setParticlesPerSec(10);
        flame.setStartColor(new ColorRGBA(1f, 0.4f, 0.05f, 1f));
        flame.setEndColor(new ColorRGBA(.4f, .22f, .12f, 0f));
        flame.setStartSize(0.1f);
        flame.setEndSize(0.7f);
        flame.getParticleInfluencer().setInitialVelocity(new Vector3f(1f,1f,1f));
        flame.setFacingVelocity(true);
        flame.setHighLife(0.15f);
        flame.setLowLife(0.05f);
        flame.setRotateSpeed(0);
        flame.setRandomAngle(false);
        flame.setGravity(new Vector3f(0f,0f,1f));
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flame.png"));
        flame.setMaterial(mat);
        emitters.add(flame);
    }
}
