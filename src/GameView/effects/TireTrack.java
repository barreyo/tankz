
package GameView.effects;

import App.TanksAppAdapter;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

/**
 *
 * @author Johan Backman
 */
public class TireTrack extends Mesh {
    
    /**
     *
     */
    public TireTrack() {
        Vector3f[] vertices = new Vector3f[4];
        int[] indexes = { 2,0,1, 1,3,2 };
        Vector2f[] texCoord = new Vector2f[4];
        vertices[0] = new Vector3f(0,0,0);
        vertices[1] = new Vector3f(3,0,0);
        vertices[2] = new Vector3f(0,3,0);
        vertices[3] = new Vector3f(3,3,0);
        texCoord[0] = new Vector2f(0,0);
        texCoord[1] = new Vector2f(1,0);
        texCoord[2] = new Vector2f(0,1);
        texCoord[3] = new Vector2f(1,1);
        addToBuffer(vertices, texCoord, indexes);
    }
    
    private void addToBuffer(Vector3f[] vertices, Vector2f[] texCoords, int[] indexes) {
        this.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        this.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoords));
        this.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(indexes));
        this.updateBound();
    }
}

