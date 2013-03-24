/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Game;

import GameLogicLayer.Animation.AAnimationManager;
import GameLogicLayer.AppStates.TanksAppStateManager;
import GameLogicLayer.Effects.EffectsManager;
import GameLogicLayer.GUI.GUIManager;
import GameLogicLayer.Input.ControlManager;
import GameLogicLayer.Physics.PhysicsManager;
import GameLogicLayer.Sounds.SoundManager;
import GameLogicLayer.Vehicle.AVehicleManager;
import GameLogicLayer.Vehicle.TankManager;
import GameLogicLayer.Weapon.AWeaponManager;
import GameModelLayer.Vehicle.IArmedVehicle;
import GameModelLayer.Vehicle.TankModel;
import GameViewLayer.Map.PhysicsTestHelper;
import GameViewLayer.Vehicle.IVehicleSpatial;
import GameViewLayer.Vehicle.TankSpatial;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;



/**
 * The main controller of the game TanksGame.
 *
 * @author Daniel, Per, Johan, Albin
 */
public class TanksGame extends SimpleApplication {
    private static TanksGame tanksApp;
    
    private TanksAppStateManager tanksAppStateManager;
    private GUIManager guiManager;
    private AVehicleManager vehicleManager;
    private AWeaponManager weaponManager;
    private AAnimationManager animManager;
    private ControlManager controlManager;
    private PhysicsManager physicsManager;
    private EffectsManager effectsManager;
    private SoundManager soundManager;
    
    private Node mapNode = new Node("Map");
    private CameraNode camNode;
    private BulletAppState bulletAppState;
    private PhysicsSpace physics;

    /**
     *  Initiates the application.
     */
    @Override
    public void simpleInitApp() {
        tanksApp = this;
        
        guiManager = new GUIManager();
        controlManager = new ControlManager();
        physicsManager = new PhysicsManager();
        effectsManager = new EffectsManager();
        soundManager = new SoundManager();
        
        
        tanksAppStateManager = new TanksAppStateManager();
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
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
     * Assigns values to bulletAppState and stateManager. Creates a physicsSpace
     * and a test-world to drive around in.
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
        IArmedVehicle vehicleModel = new TankModel();
        vehicleModel.setAccelerationForce(4000.0f);
        vehicleModel.setBrakeForce(100.0f);
        vehicleManager = new TankManager(vehicleModel, tankView, this);
        //buildPlayer();
        Node vehicleNode = tankView.getVehicleNode();
        rootNode.attachChild(vehicleNode);
        return vehicleNode;
    }

    /**
     * Initiates the map.
     */
    private void initMap() {
        rootNode.attachChild(mapNode);
    }

    /**
     *
     * @return
     */
    
    public PhysicsSpace getPhysicsSpace() {
        return physics;
    }
    
    public static TanksGame getApp() {
        return tanksApp;
    }

    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }
    
    public TanksAppStateManager getTanksAppStateManager(){
        return tanksAppStateManager;
    }
    
    public GUIManager getGUIManager() {
        return guiManager;
    }

    public AVehicleManager getVehicleManager() {
        return vehicleManager;
    }
    
    public AWeaponManager getWeaponManager(){
        return weaponManager;
    }
    
    public ControlManager getControlManager(){
        return controlManager;
    }
    
    public PhysicsManager getPhysicsManager(){
        return physicsManager;
    }
    
    public EffectsManager getEffectsManager(){
        return effectsManager;
    }
    public SoundManager getSoundManager(){
        return soundManager;
    }
    
    public AAnimationManager getAnimManager(){
        return animManager;
    }
}
