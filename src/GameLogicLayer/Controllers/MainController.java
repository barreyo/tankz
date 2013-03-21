/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Controllers;

import GameLogicLayer.Vehicle.AVehicle;
import GameLogicLayer.Vehicle.Tank;
import GameViewLayer.Maps.ITankMap;
import GameViewLayer.Maps.PhysicsTestHelper;
import GameViewLayer.Maps.TanksDefaultMap;
import GameViewLayer.Vehicle.DefaultTankView;
import GameViewLayer.Vehicle.ITankSpatial;
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
 *
 * @author Daniel
 */
public class MainController extends SimpleApplication {
    // Map related fields
    private Node mapNode = new Node("Map");
    private CameraNode camNode;
    private ITankMap map;
    private BulletAppState bulletAppState;
    
    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        //bulletAppState.getPhysicsSpace().enableDebug(assetManager);
        PhysicsSpace physicsSpace = bulletAppState.getPhysicsSpace();
        PhysicsTestHelper.createPhysicsTestWorld(rootNode, assetManager, physicsSpace);
        
        ITankSpatial tankView = new DefaultTankView(assetManager);
        AVehicle vehicleController = new Tank(inputManager, tankView, physicsSpace);
        vehicleController.setAccelerationForce(4000.0f);
        vehicleController.setBrakeForce(100.0f);
        //buildPlayer();
        
        
        Node vehicleNode = tankView.getVehicleNode();
        rootNode.attachChild(vehicleNode);
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
        
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

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void initMap() {
        map.initMap();
        rootNode.attachChild(mapNode);
    }
}

