/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Game;

import GameLogicLayer.Vehicle.AVehicleController;
import GameLogicLayer.Vehicle.TankController;
import GameModelLayer.Vehicle.IVehicleModel;
import GameModelLayer.Vehicle.TankModel;
import GameViewLayer.Map.ITankMap;
import GameViewLayer.Map.PhysicsTestHelper;
import GameViewLayer.Map.TanksDefaultMap;
import GameViewLayer.Vehicle.TankSpatial;
import GameViewLayer.Vehicle.IVehicleSpatial;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;

/**
 * The main controller of the game Tanks.
 *
 * @author Daniel, Per, Johan, Albin
 */
public class GameController extends SimpleApplication {
    private Node mapNode = new Node("Map");
    private CameraNode camNode;
    private ITankMap map;
    private BulletAppState bulletAppState;
    private PhysicsSpace physics;
    
    private AVehicleController vehicleManager;
    
    /**
     *  Initiates the application.
     */
    @Override
    public void simpleInitApp() {
        physics = this.createPhysics();
        Node vehicleNode = this.createVehicle();
        this.initLighting();
        this.initCam(vehicleNode);
    }

    /**
     *
     * @param tpf
     */
    @Override
    public void simpleUpdate(float tpf) {
       vehicleManager.simpleUpdate(tpf);
        //TODO: add update code
    }

    /**
     *
     * @param rm
     */
    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    /**
     * Initiates the third person camera that follows the vehicle.
     * 
     * @param vehicleNode 
     */
    private void initCam(Node vehicleNode) {
        // Disable the default flyby cam
        flyCam.setEnabled(false);
        //create the camera Node
        camNode = new CameraNode("Camera Node", cam);
        //This mode means that camera copies the movements of the target:
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        //Attach the camNode to the target:
        vehicleNode.attachChild(camNode);
        //Move camNode, e.g. behind and above the target:
        camNode.setLocalTranslation(new Vector3f(0, 2, -5));
        //Rotate the camNode to look at the target:
        camNode.lookAt(vehicleNode.getLocalTranslation(), Vector3f.UNIT_Y);

    }
    
    /**
     * Initiates the lighting.
     */
    private void initLighting() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
    }
    
    /**
     * Assigns values to bulletAppState and stateManager.
     * Creates a physicsSpace and a test-world to drive around in.
     * 
     * @return physicsSpace.
     */
    private PhysicsSpace createPhysics() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        //bulletAppState.getPhysicsSpace().enableDebug(assetManager);
        PhysicsSpace physicsSpace = bulletAppState.getPhysicsSpace();
        PhysicsTestHelper.createPhysicsTestWorld(rootNode, assetManager, physicsSpace);
        return physicsSpace;
    }
    
    /**
     * Creates a vehicle, adds physics and returns the according Node.
     * 
     * @param physicsSpace
     * @return vehicleNode.
     */
    private Node createVehicle() {
        IVehicleSpatial tankView = new TankSpatial(assetManager.loadModel("Models/tanken/tanken.j3o"), 3f);
        IVehicleModel vehicleModel = new TankModel();
        vehicleModel.setAccelerationForce(4000.0f);
        vehicleModel.setBrakeForce(100.0f);
        vehicleManager = new TankController(vehicleModel, tankView, this);
        //buildPlayer();
        Node vehicleNode = tankView.getVehicleNode();
        rootNode.attachChild(vehicleNode);
        return vehicleNode;
    }
    
    /**
     * Initiates the map.
     */
    private void initMap() {
        map.initMap();
        rootNode.attachChild(mapNode);
    }
    
    /**
     *
     * @return
     */
    public PhysicsSpace getPhysicsSpace() {
        return physics;
    }
}

