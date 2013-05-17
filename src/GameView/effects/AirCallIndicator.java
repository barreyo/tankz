/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView.effects;

import App.TanksAppAdapter;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;

/**
 *
 * @author backman
 */
public class AirCallIndicator {
    
    private Quad quad;
    
    public AirCallIndicator() {
        quad = new Quad(30, 30);
        Geometry g = new Geometry("quad", quad);
        g.setLocalTranslation(73.6f, 19.5f, 98);
        g.rotate(-FastMath.HALF_PI, 0, 0);
        g.setMaterial(TanksAppAdapter.INSTANCE.getAssetManager().loadMaterial("Common/Materials/RedColor.j3m"));
        TanksAppAdapter.INSTANCE.attachChildToRootNode(g);
    }
}
