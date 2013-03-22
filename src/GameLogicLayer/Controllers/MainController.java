/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Controllers;

import GameLogicLayer.Vehicle.AVehicleController;
import GameLogicLayer.Vehicle.TankController;
import GameViewLayer.Maps.ITankMap;
import GameViewLayer.Maps.PhysicsTestHelper;
import GameViewLayer.Maps.TanksDefaultMap;
import GameViewLayer.Vehicle.DefaultTankSpatial;
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
public class MainController extends SimpleApplication {
    private Node mapNode = new Node("Map");
    private CameraNode camNode;
    private ITankMap map;
    private BulletAppState bulletAppState;
    
    /**
     *  Initiates the application.
     */
    @Override
    public void simpleInitApp() {
        PhysicsSpace physics = this.createPhysics();
        Node vehicleNode = this.createVehicle(physics);
        this.initLighting();
        this.initCam(vehicleNode);
    }

    /**
     *
     * @param tpf
     */
    @Override
    public void simpleUpdate(float tpf) {
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
    private Node createVehicle(PhysicsSpace physicsSpace) {
        IVehicleSpatial tankView = new DefaultTankSpatial(assetManager);
        AVehicleController vehicleController = new TankController(inputManager, tankView, physicsSpace);
        vehicleController.setAccelerationForce(4000.0f);
        vehicleController.setBrakeForce(100.0f);
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
}

