
package GameLogicLayer.controls;

import GameLogicLayer.Game.TanksGame;
import GameViewLayer.gameEntity.MissileProjectile;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
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
    private RigidBodyControl physicsControl;
    
    
    private ParticleEmitter effect;
    
    
    public TankProjectileControl() {
        TanksGame app = TanksGame.getApp();
        physicsSpace = app.getBulletAppState().getPhysicsSpace();
        physicsSpace.addCollisionListener(this);
        prepareEffects();
    }
    
     /**
     * @inheritdoc
     */
    @Override
    public synchronized void setSpatial(Spatial spatial) {
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
    public synchronized void collision(PhysicsCollisionEvent event) {
        if (physicsSpace == null || spatial == null) {
            return;
        }
        physicsControl = projectile.getPhysicsControl();
        if (event.getObjectA() == physicsControl || event.getObjectB() == physicsControl) {
            if (effect != null && spatial.getParent() != null) {
                effect.setLocalTranslation(spatial.getLocalTranslation());
                spatial.getParent().attachChild(effect);
                effect.emitAllParticles();
            }
            physicsSpace.remove(physicsControl);
            spatial.removeFromParent();
            //physicsSpace.removeCollisionListener(this);
            projectile.cleanup();
        }
    }

    @Override
    synchronized void controlUpdate(float tpf) {
        Vector3f oldLocation = spatial.getWorldTranslation();
        Vector3f newLocation = oldLocation.addLocal(projectile.getDirection());
        spatial.setLocalTranslation(newLocation);
    }

    private synchronized void prepareEffects() {
        int COUNT_FACTOR = 1;
        float COUNT_FACTOR_F = 1f;
        effect = new ParticleEmitter("Flame", ParticleMesh.Type.Triangle, 32 * COUNT_FACTOR);
        effect.setSelectRandomImage(true);
        effect.setStartColor(new ColorRGBA(1f, 0.4f, 0.05f, (float) (1f / COUNT_FACTOR_F)));
        effect.setEndColor(new ColorRGBA(.4f, .22f, .12f, 0f));
        effect.setStartSize(1.3f);
        effect.setEndSize(2f);
        effect.setShape(new EmitterSphereShape(Vector3f.ZERO, 1f));
        effect.setParticlesPerSec(0);
        effect.setGravity(0, -5f, 0);
        effect.setLowLife(.4f);
        effect.setHighLife(.5f);
        effect.setInitialVelocity(new Vector3f(0, 7, 0));
        effect.setVelocityVariation(1f);
        effect.setImagesX(2);
        effect.setImagesY(2);
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Particle.j3md");
        mat.setTexture("Texture", app.getAssetManager().loadTexture("Effects/Explosion/flame.png"));
        effect.setMaterial(mat);
    }
}
