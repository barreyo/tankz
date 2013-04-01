
package GameLogicLayer.controls;

import GameLogicLayer.Game.TanksGame;
import GameViewLayer.gameEntity.Tank;
import GameViewLayer.gameEntity.MissileProjectile;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 * WIll BE EDITED
 *
 * @author Daniel
 */
public class TankProjectileControl extends BaseControl implements PhysicsCollisionListener{
    private PhysicsSpace physicsSpace;
    private MissileProjectile projectile;
    
    
    public TankProjectileControl() {
        TanksGame app = TanksGame.getApp();
        PhysicsSpace physicsSpace = app.getBulletAppState().getPhysicsSpace();
        physicsSpace.addCollisionListener(this);
    }
    
     /**
     * @inheritdoc
     */
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);

        if (spatial != null) {
            // Get the visual representation of the tank
            projectile = spatial.getUserData("entity");
        }
    }

    /**
     *
     * @param event
     */
    @Override
    public void collision(PhysicsCollisionEvent event) {
        /*if (spatial != null) {
            app.getRootNode().detachChild(spatial);
            projectile.cleanup();
        }*/
    }

    @Override
    void controlUpdate(float tpf) {
        Vector3f oldLocation = spatial.getWorldTranslation();
        Vector3f newLocation = oldLocation.addLocal(projectile.getDirection().multLocal(1.1f));
        spatial.setLocalTranslation(newLocation);
    }
}
