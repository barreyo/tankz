package view.effects;

import application.TanksAppAdapter;
import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstraction of all effects.
 * Contains a list of particle emitters and an asset manager
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class AEffect {
    /**
     * A list of particle emitters.
     */
    protected List<ParticleEmitter> emitters = new ArrayList<ParticleEmitter>();
    
    /**
     * The asset manager.
     */
    protected AssetManager assetManager;

    AEffect(){
        assetManager = TanksAppAdapter.INSTANCE.getAssetManager();
    }

    /**
     * Returns the particle emitter.
     * 
     * @return The particle emitter
     */
    public Collection<ParticleEmitter> getParticleEmitters() {
        List<ParticleEmitter> emit = new ArrayList<ParticleEmitter>();
        for (ParticleEmitter emitter : emitters) {
            emit.add(emitter.clone());
        }
        return emit;
    }
    
}
