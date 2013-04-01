
package GameViewLayer.gameEntity;

import GameLogicLayer.controls.TankProjectileControl;
import GameLogicLayer.controls.TanksControl;
import GameViewLayer.graphics.EGraphics;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;
import com.jme3.math.Vector3f;
import java.io.IOException;


/**
 *
 * @author Daniel
 */
public class MissileProjectile extends GameEntity implements Savable {
    
    private TankProjectileControl projectileControl;
    private Vector3f direction;

    public MissileProjectile() {
        super(EGraphics.SHARK);
        
        spatial.setUserData("entity", this);
    }
    
    @Override
    public CollisionShape getCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }

    @Override
    void addMaterial() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void addControl() {
        this.projectileControl = (TankProjectileControl)controlManager.getControl(TanksControl.PROJECTILE_CONTROL);
        spatial.addControl(this.projectileControl);
    }

    @Override
    public void cleanup() {
        projectileControl.cleanup();
        //bulletAppState.getPhysicsSpace().remove(vehicle);
        spatial.removeControl(projectileControl);
        spatial.setName(null);
        projectileControl = null;
    }

    @Override
    public void finalise() {
        addControl();
    }

    public Vector3f getDirection() {
        return direction;
    }
    
    
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
