/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView.gameEntity;

import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;

/**
 *
 * @author backman
 */
public class TireTracks extends Mesh {
    private Vector3f [] vertices;

    public TireTracks() {
        vertices = new Vector3f[4];
        vertices[0] = new Vector3f(0,0,0);
        vertices[1] = new Vector3f(3,0,0);
        vertices[2] = new Vector3f(0,3,0);
        vertices[3] = new Vector3f(3,3,0);
    }
}
