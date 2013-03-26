
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
    TanksGame app;
    Node rootNode;
    Node guiNode;
    BulletAppState bulletAppState;
    AssetManager assetManager;
    PreloadManager preloadManager;
    GraphicManager graphicManager;
    AppStateManager stateManager;
    ControlManager controlManager;
    MaterialManager materialManager;
    
    Spatial spatial;
    //protected AnimComponent animComponent;

    GameEntity(Graphics graphic) {
        this();
        spatial = graphicManager.createSpatial(graphic);
    }

    public GameEntity() {
        app = TanksGame.getApp();
        rootNode = app.getRootNode();
        guiNode = app.getGuiNode();
        bulletAppState = app.getBulletAppState();
        assetManager = app.getAssetManager();
        preloadManager = app.getPreloadManager();
        graphicManager = app.getGraphicManager();
        stateManager = app.getStateManager();
        controlManager = app.getControlManager();
        materialManager = app.getMaterialManager();
    }

    public abstract CollisionShape getCollisionShape();

    abstract void addMaterial();

    abstract void addControl();

    public abstract void cleanup();

    public abstract void finalise();

    // TODO
    /*public AnimComponent getAnimComponent() {
        return animComponent;
    }*/

    void addPhysicsControl() {
        RigidBodyControl rigidBodyControl = new RigidBodyControl(getCollisionShape(), 1);
        rigidBodyControl.setKinematic(true);
        spatial.addControl(rigidBodyControl);
        bulletAppState.getPhysicsSpace().add(rigidBodyControl);
    }

    public Spatial getSpatial() {
        return spatial;
    }

    CollisionShape createNewBoxCollisionShape() {
        return new BoxCollisionShape(getExtents());
    }

    CollisionShape createNewMeshCollisionShape() {
        return CollisionShapeFactory.createMeshShape(spatial);
    }

    CollisionShape createNewSphereCollisionShape() {
        return new SphereCollisionShape(getExtents().x);
    }

    Vector3f getExtents() {
        return ((BoundingBox) spatial.getWorldBound()).getExtent(null);
    }
}
