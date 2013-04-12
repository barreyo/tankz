
package GameView.gameEntity;

import GameView.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;
import com.jme3.math.Vector3f;
import java.io.IOException;


/**
 * A missile projectile.
 *
 * @author Daniel
 */
public class MissileProjectileEntity extends AGameEntity implements Savable {
    private Vector3f direction;

    public MissileProjectileEntity() {
        super(EGraphics.SHARK);
        // Add this instance as user data to the spatial
        spatial.setUserData("entity", this);
    }
    
    /**
     * @inheritdoc
     */
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }

    /**
     * @inheritdoc
     */
    @Override
    public void cleanup() {
        // LÖS
        /*projectileControl.cleanup();
        //bulletAppState.getPhysicsSpace().remove(vehicle);
        spatial.removeControl(projectileControl);*/
    }

    /**
     * Returns the direction this missile is facing.
     * 
     * @return The direction this missile is facing
     */
    public Vector3f getDirection() {
        return direction;
    }
    
    /**
     * Sets the direction of the missile.
     * 
     * @param direction sets the direcetion this missile is facing
     */
    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }

    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
