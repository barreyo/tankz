package view.effects;

import application.TanksAppAdapter;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The flashing effect that appears when a tank shoots.
 * Could of course be used elsewhere.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
final class ShootEffect extends AEffect implements IEffect{
    
    /**
     * Create a shooting effect.
     */
    ShootEffect() {
        createSpark();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<ParticleEmitter> getParticleEmitters() {
        List<ParticleEmitter> emit = new ArrayList<ParticleEmitter>();
        for (ParticleEmitter emitter : emitters) {
            emit.add(emitter.clone());
        }
        return emit;
    }

    private void createSpark() {
        ParticleEmitter flash = new ParticleEmitter("Flash", ParticleMesh.Type.Point, 24);
        flash.setSelectRandomImage(true);
        flash.setStartColor(new ColorRGBA(1f, 0.8f, 0.36f, 1f));
        flash.setEndColor(new ColorRGBA(1f, 0.8f, 0.36f, 0f));
        flash.setStartSize(.1f);
        flash.setEndSize(1.0f);
        flash.setShape(new EmitterSphereShape(Vector3f.ZERO, .05f));
        flash.setParticlesPerSec(0);
        flash.setGravity(0, 0, 0);
        flash.setLowLife(.2f);
        flash.setHighLife(.2f);
        flash.setInitialVelocity(new Vector3f(0, 5f, 0));
        flash.setVelocityVariation(1);
        flash.setImagesX(2);
        flash.setImagesY(2);
        Material mat = new Material(TanksAppAdapter.INSTANCE.getAssetManager(), "Common/MatDefs/Misc/Particle.j3md");
        mat.setTexture("Texture", TanksAppAdapter.INSTANCE.getAssetManager().loadTexture("Effects/Explosion/flash.png"));
        mat.setBoolean("PointSprite", true);
        flash.setMaterial(mat);
        emitters.add(flash);
    }
}
