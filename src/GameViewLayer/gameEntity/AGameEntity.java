package GameViewLayer.gameEntity;

import GameLogicLayer.logic.TanksGame;
import GameLogicLayer.logic.GraphicManager;
import GameLogicLayer.logic.MaterialManager;
import GameLogicLayer.entitycontrols.ControlFactory;
import GameLogicLayer.logic.PreloadManager;
import GameViewLayer.graphics.EGraphics;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * An abstract game entity.
 * 
 * @author Daniel
 */
public abstract class AGameEntity {
    TanksGame app;
    Node rootNode;
    Node guiNode;
    BulletAppState bulletAppState;
    AssetManager assetManager;
    AppStateManager stateManager;
    ControlFactory controlManager;
    
    Spatial spatial;
    //protected AnimComponent animComponent;

    /**
     * Creates a game enity with a loaded spatial.
     * 
     * @param graphic The graphical objekt to be loaded.
     */
    AGameEntity(EGraphics graphic) {
        this();
        spatial = GraphicManager.INSTANCE.createSpatial(graphic);
    }

    /**
     *  Creates a game entity with its needed managers.
     */
    AGameEntity() {
        app = TanksGame.getApp();
        rootNode = app.getRootNode();
        guiNode = app.getGuiNode();
        bulletAppState = app.getBulletAppState();
        assetManager = app.getAssetManager();
        stateManager = app.getStateManager();
        controlManager = ControlFactory.getInstance();
    }

    /**
     * Returns a collisionshape of this game entity.
     *
     * @return a collisionshape of this game entity
     */
    public abstract CollisionShape getCollisionShape();

    /**
     * Adds an material to the spatial.
     */
    abstract void addMaterial();

    /**
     * Adds an appropriate control to the spatial.
     */
    abstract void addControl();

    /**
     * Releases all occupied resources of this instance.
     */
    public abstract void cleanup();

    /**
     * Puts this entity in a controlled state.
     */
    public abstract void finalise();

    /**
     * Adds a physics control to the game entity.
     */
    void addPhysicsControl() {
        RigidBodyControl rigidBodyControl = new RigidBodyControl(getCollisionShape(), 1);
        rigidBodyControl.setKinematic(true);
        spatial.addControl(rigidBodyControl);
        bulletAppState.getPhysicsSpace().add(rigidBodyControl);
    }

    /**
     * Returns the spatial of this game entity.
     * 
     * @return The spatial of this game entity.
     */
    public Spatial getSpatial() {
        return spatial;
    }
    
    /**
     * Internal help metohd used to get the boundingbox of the spatial.
     */
    Vector3f getExtents() {
        return ((BoundingBox) spatial.getWorldBound()).getExtent(null);
    }
    
    // TODO
    /*public AnimComponent getAnimComponent() {
        return animComponent;
    }*/
}
