package GameView.gameEntity;

import GameControllers.logic.GraphicManager;
import GameView.graphics.EGraphics;
import com.jme3.bounding.BoundingBox;
import com.jme3.effect.ParticleEmitter;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.util.Collection;

/**
 * An abstract game entity.
 * 
 * @author Daniel
 */
public abstract class AGameEntity implements IGameEntity {
    Spatial spatial;
    //protected AnimComponent animComponent;

    /**
     * Creates a game enity with a loaded spatial.
     * 
     * @param graphic The graphical objekt to be loaded.
     */
    AGameEntity(EGraphics graphic) {
        spatial = GraphicManager.INSTANCE.createSpatial(graphic);
        spatial.setShadowMode(RenderQueue.ShadowMode.Cast);
    }

    /**
     * Adds an material to the spatial.
     * @param mat 
     */
    @Override
    public void setMaterial(Material mat) {
        spatial.setMaterial(mat);
    }

    /**
     * Adds an appropriate control to the spatial.
     * @param control 
     */
    @Override
    public void addControl(Control control) {
        spatial.addControl(control);
    }
    
    /**
     * Adds an appropriate control to the spatial.
     */
    @Override
    public void removeControl(Control control) {
        spatial.removeControl(control);
    }

    /**
     * Returns the spatial of this game entity.
     * 
     * @return The spatial of this game entity.
     */
    @Override
    public Spatial getSpatial() {
        return spatial;
    }
    
    /**
     * Help metohd used to get the boundingbox of the spatial.
     * @return 
     */
    @Override
    public Vector3f getExtents() {
        return ((BoundingBox) spatial.getWorldBound()).getExtent(null);
    }
    
    /**
     * Shows the effects that this methods gets in, at the position given.
     * 
     * @param effects The effects that gets shown
     * @param position The position at which the effects get shown
     */
    protected synchronized void showEffects(Collection<ParticleEmitter> effects, Vector3f position) {
        if (spatial.getParent() != null) {
            for (ParticleEmitter effect : effects) {
                if (effect != null) {
                    effect.setLocalTranslation(position);
                    spatial.getParent().attachChild(effect);
                    effect.emitAllParticles();
                }
            }
        }
    }
    
    /**
     * Hides the given effects.
     * 
     * @param effects The effects to be hidden
     */
    protected synchronized void hideEffects(Collection<ParticleEmitter> effects) {
        if (spatial.getParent() != null) {
            for (ParticleEmitter effect : effects) {
                if (effect != null) {
                    spatial.getParent().detachChild(effect);
                }
            }
        }
    }

    public abstract void impact();
}
