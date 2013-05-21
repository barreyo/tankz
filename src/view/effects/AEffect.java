/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.effects;

import application.TanksAppAdapter;
import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author perthoresson
 */
public class AEffect {
        protected List<ParticleEmitter> emitters = new ArrayList<ParticleEmitter>();
        protected AssetManager assetManager;
        
        AEffect(){
            assetManager = TanksAppAdapter.INSTANCE.getAssetManager();
        }
    
        public Collection<ParticleEmitter> getParticleEmitters() {
        List<ParticleEmitter> emit = new ArrayList<ParticleEmitter>();
        for (ParticleEmitter emitter : emitters) {
            emit.add(emitter.clone());
        }
        return emit;
    }
    
}
