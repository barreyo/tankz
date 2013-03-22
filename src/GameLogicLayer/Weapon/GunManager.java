package GameLogicLayer.Weapon;

import GameLogicLayer.Game.GameController;
import GameLogicLayer.Projectile.BulletControl;
import GameModelLayer.Projectile.IProjectileModel;
import GameModelLayer.Weapon.IWeaponModel;
import GameViewLayer.Projectile.IProjectileSpatial;
import GameViewLayer.Weapon.IWeaponSpatial;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Daniel
 */
public class GunManager extends AWeaponManager {

    private IWeaponSpatial weaponSpatial;
    private IWeaponModel weaponModel;
    private IProjectileSpatial projectileSpatial;
    private IProjectileModel projectileModel;
    
    private Camera cam;
    private PhysicsSpace physicsSpace;
    private Node rootNode;
    

    /**
     *
     * @param weaponSpatial
     * @param weaponModel
     * @param projectileSpatial
     * @param projectileModel
     * @param app
     */
    public GunManager(IWeaponSpatial weaponSpatial, IWeaponModel weaponModel,
            IProjectileSpatial projectileSpatial, IProjectileModel projectileModel,
            GameController app) {
        super(app.getInputManager());
        this.weaponSpatial = weaponSpatial;
        this.weaponModel = weaponModel;
        this.projectileSpatial = projectileSpatial;
        this.projectileModel = projectileModel;
        cam = app.getCamera();
        rootNode = app.getRootNode();
        physicsSpace = app.getPhysicsSpace();
    }

    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("Space")) {
            if (!isPressed) {
                Spatial bullet = projectileSpatial.getProjectileSpatial();
                bullet.setLocalTranslation(weaponSpatial.getWeaponSpatial().getWorldTranslation());
                
                RigidBodyControl projectileControl = new RigidBodyControl(
                        projectileSpatial.getProjectileCollisionShape(), 0.001f);
                projectileControl.setCcdMotionThreshold(0.1f);
                // MÅSTE LÖSA
                projectileControl.setLinearVelocity(cam.getDirection().mult(200));
                bullet.addControl(projectileControl);
                
                rootNode.attachChild(bullet);
                physicsSpace.add(projectileControl);
                
                BulletControl bulletControl = new BulletControl(projectileModel,
                                              projectileSpatial, physicsSpace);
            }
        }
    }
}
