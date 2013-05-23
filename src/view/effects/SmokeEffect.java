
package view.effects;

import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

/**
 * The smoke which comes out of the back of the tank at all times.
 * Could of course be used in other places.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class SmokeEffect extends AEffect implements IEffect {
    
    /**
     * Instantiates the object.
     * Creates the smoke
     */
    SmokeEffect() {
        super();
        createSmokeTrail(assetManager);
    }
    
    private void createSmokeTrail(AssetManager assetManager) {
        ParticleEmitter smoketrail = new ParticleEmitter("SmokeTrail", ParticleMesh.Type.Triangle, 30);
        smoketrail.setNumParticles(80);
        smoketrail.setParticlesPerSec(10);
        smoketrail.setStartColor(new ColorRGBA(.1f, .1f, .1f, 0.45f));
        smoketrail.setEndColor(new ColorRGBA(.1f, .1f, .1f, 0));
        smoketrail.setStartSize(0.1f);
        smoketrail.setEndSize(0.7f);
        smoketrail.getParticleInfluencer().setInitialVelocity(new Vector3f(1f,1f,1f));
        smoketrail.setFacingVelocity(true);
        smoketrail.setHighLife(1f);
        smoketrail.setLowLife(0.2f);
        smoketrail.setRotateSpeed(0);
        smoketrail.setRandomAngle(false);
        smoketrail.setGravity(new Vector3f(0f,-1.5f,0f));
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flame.png"));
        smoketrail.setMaterial(mat);
        emitters.add(smoketrail);
    }
}
