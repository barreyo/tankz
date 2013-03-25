
package GameViewLayer.gameEntity;

import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.Graphics.GraphicManager;
import GameLogicLayer.Graphics.Graphics;
import GameLogicLayer.Graphics.MaterialManager;
import GameLogicLayer.controls.ControlManager;
import GameLogicLayer.util.PreloadManager;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Daniel
 */
public abstract class GameEntity {
    protected TanksGame app = TanksGame.getApp();
    protected Node rootNode = app.getRootNode();
    protected Node guiNode = app.getGuiNode();
    protected BulletAppState bulletAppState = app.getBulletAppState();
    protected AssetManager assetManager = app.getAssetManager();
    protected PreloadManager preloadManager = app.getPreloadManager();
    protected GraphicManager graphicManager = app.getGraphicManager();
    protected AppStateManager stateManager = app.getStateManager();
    protected ControlManager controlManger = app.getControlManager();
    protected MaterialManager materialManager = app.getMaterialManager();
    
    protected Spatial spatial;
    //protected AnimComponent animComponent;

    public GameEntity(Graphics graphic) {
        spatial = graphicManager.createSpatial(graphic);
    }

    public GameEntity() {
    
    }

    public abstract CollisionShape getCollisionShape();

    protected abstract void addMaterial();

    protected abstract void addControl();

    public abstract void cleanup();

    public abstract void finalise();

    // TODO
    /*public AnimComponent getAnimComponent() {
        return animComponent;
    }*/

    protected void addPhysicsControl() {
        RigidBodyControl rigidBodyControl = new RigidBodyControl(getCollisionShape(), 1);
        rigidBodyControl.setKinematic(true);
        spatial.addControl(rigidBodyControl);
        bulletAppState.getPhysicsSpace().add(rigidBodyControl);
    }

    public Spatial getSpatial() {
        return spatial;
    }

    protected CollisionShape createNewBoxCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }

    protected CollisionShape createNewMeshCollisionShape() {
        return CollisionShapeFactory.createMeshShape(spatial);
    }

    protected CollisionShape createNewSphereCollisionShape() {
        return new SphereCollisionShape(getExtents().x);
    }

    protected Vector3f getExtents() {
        return ((BoundingBox) spatial.getWorldBound()).getExtent(null);
    }
}
