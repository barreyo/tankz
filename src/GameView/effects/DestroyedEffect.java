/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView.effects;

import App.TanksAppAdapter;
import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Per
 */
public class DestroyedEffect implements IEffect {

    private List<ParticleEmitter> emitters = new ArrayList<ParticleEmitter>();
    
    public DestroyedEffect(){
        AssetManager assetManager = TanksAppAdapter.INSTANCE.getAssetManager();
    }
    
    @Override
    public Collection<ParticleEmitter> getParticleEmitters() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
