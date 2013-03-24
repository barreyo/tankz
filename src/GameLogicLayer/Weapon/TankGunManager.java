package GameLogicLayer.Weapon;

import GameLogicLayer.Game.GameManager;
import GameLogicLayer.Projectile.TankProjectileManager;
import GameModelLayer.Projectile.IProjectile;
import GameModelLayer.Weapon.IWeapon;
import GameViewLayer.Projectile.IProjectileSpatial;
import GameViewLayer.Weapon.IWeaponSpatial;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;


/**
 *
 * @author Daniel
 */
public class TankGunManager extends AWeaponManager {

    private IWeaponSpatial weaponSpatial;
    private IWeapon weaponModel;
    private IProjectileSpatial projectileSpatial;
    private IProjectile projectileModel;
    
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
    public TankGunManager(IWeaponSpatial weaponSpatial, IWeapon weaponModel,
            IProjectileSpatial projectileSpatial, IProjectile projectileModel,
            GameManager app) {
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
                // Get a projectilespatial and translate it to weapon
                Spatial projectile = projectileSpatial.getProjectileSpatial();
                projectile.setLocalTranslation(weaponSpatial.getWeaponSpatial().getWorldTranslation());
                
                // Create a RigidBodyControl over the projectile collision shape
                RigidBodyControl projectileControl = new RigidBodyControl(
                        projectileSpatial.getProjectileCollisionShape(), projectileModel.getMass());
                projectileControl.setCcdMotionThreshold(0.1f);
                
                // TODO Solve direction of velocity, should be same as weapon direction
                projectileControl.setLinearVelocity(weaponSpatial.getAttackDirection().mult(200));
                projectile.addControl(projectileControl);
                
                // Attach to world and phsysicsSpace
                rootNode.attachChild(projectile);
                physicsSpace.add(projectileControl);
                
                // Create a manager of the projectile
                TankProjectileManager projectileManager = new TankProjectileManager(projectileModel,
                                                            projectileSpatial, physicsSpace);
            }
        }
    }

    @Override
    public void updateDirectionOfWeapon(Vector3f direction) {
        weaponSpatial.setAttackDirection(direction);
    }
}
