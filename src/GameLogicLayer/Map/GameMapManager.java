/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Map;

import GameLogicLayer.Animation.AnimationManager;
import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.Graphics.GraphicManager;
import GameLogicLayer.Graphics.MaterialManager;
import GameLogicLayer.Physics.PhysicsManager;
import GameLogicLayer.Sounds.SoundManager;
import GameViewLayer.gameEntity.Vehicle.MainTank;
import GameLogicLayer.controls.ControlManager;
import GameLogicLayer.util.Manager;
import GameLogicLayer.util.PreloadManager;
import GameViewLayer.Map.GameMap;
import GameViewLayer.Map.GameMap1;
import com.jme3.app.state.AbstractAppState;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Daniel
 */
public class GameMapManager extends AbstractAppState implements Manager {

    private TanksGame app;
    private Node rootNode;
    private ControlManager controlManager;
    //private EntityManager entityManager;
    private MaterialManager materialManager;
    private PhysicsManager physicsManager;
    private SoundManager soundManager;
    private GraphicManager graphicsManager;
    private PreloadManager preloadManager;
    private boolean stateInitialised;
    private AnimationManager animManager;
    
    private GameMap currentGameMap;
    private int currentIntGameMap;
    private static final int NUMBER_OF_MAPS = 1; 
            
    private Node mapNode;
    //private CinematicComposition cc;
    private MainTank mainTank;

    public GameMapManager() {
        app = TanksGame.getApp();
        rootNode = app.getRootNode();
        controlManager = app.getControlManager();
        //entityManager = app.getEntityManager();
        materialManager = app.getMaterialManager();
        physicsManager = app.getPhysicsManager();
        soundManager = app.getSoundManager();
        preloadManager = app.getPreloadManager();
        graphicsManager = app.getGraphicManager();
        animManager = app.getAnimManager();
        stateInitialised = false;
        currentIntGameMap = 1;
    }

    public int getCurrentIntMap() {
        return currentIntGameMap;
    }

    public void setCurrentIntGameMap(int gameMap) {
        this.currentIntGameMap = gameMap;
    }

    public GameMap getCurrentMap() {
        return currentGameMap;
    }

    // only call this once during the first ever level
    public void initialiseGameStatesOnce() {
        app.getCamera().setLocation(new Vector3f(-231.00694f, 269.15887f, 319.6499f));
        app.getCamera().lookAt(new Vector3f(0f, 0f, 0f), Vector3f.UNIT_Y);

        // Load LevelCommon

        // Load Island
        mapNode = (Node) app.getAssetManager().loadModel("Models/testFloor.j3o");
        rootNode.attachChild(mapNode);

        app.getBulletAppState().getPhysicsSpace().addAll(mapNode);   
    
        //mapNode.getChild("SpawningPoints").setCullHint(Spatial.CullHint.Always);

        /*
        //Spawning enemies
        Node level2enemie = (Node) ((Node) ((Node) island.getChild("SpawningPoints")).getChild("2")).getChild("Enemy");
        Node level3enemie = (Node) ((Node) ((Node) island.getChild("SpawningPoints")).getChild("3")).getChild("Enemy");
        Node level4enemie = (Node) ((Node) ((Node) island.getChild("SpawningPoints")).getChild("4")).getChild("Enemy");

        //Blobs
        for (Spatial point : level2enemie.getChildren()) {
            addEnemy((Enemy) Entity.ENEMY_BLOB.createEntity(), 2f, point.getWorldTranslation().add(0f, -15.4556f, 0f));
        }
        //Armadilos
        for (Spatial point : level3enemie.getChildren()) {
            addEnemy((Enemy) Entity.ENEMY_ARMADILO.createEntity(), 4f, point.getWorldTranslation().add(0f, -15.4556f, 0f));
        }
        //Elephants
        for (Spatial point : level4enemie.getChildren()) {
            addEnemy((Enemy) Entity.ENEMY_ELEPHANT.createEntity(), 6f, point.getWorldTranslation().add(0f, -15.4556f, 0f));
        }

        cc = new GameStartCinematic(myApp);
        cc.attach();

        myApp.getStateManager().detach(this);
        myApp.getCamera().setRotation(Quaternion.IDENTITY);
        mainCharacter = (MainCharacter) entityManager.create(Entity.MAIN_CHARACTER);
        mainCharacter.getSpatial().move(-130, 60, -60);
        rootNode.attachChild(mainCharacter.getSpatial());
        //currentLevel.getAllEntities().add(mainCharacter);
        

        myApp.getStateManager().attach(this);
        
    }

    private void addEnemy(Enemy enemy, float enemySize, Vector3f spawn) {
        float areaSizeX = 20f;
        float areaSizeZ = 20f;
        WalkableArea wa = new WalkableRectangle(spawn.getX() - (areaSizeX / 2f), spawn.getZ() - (areaSizeZ / 2f), 20f, 20f);
        enemy.getSpatial().addControl(new MoveRandomControl(enemy, wa));
        enemy.getSpatial().addControl(new AggroControl(enemy, 25f, enemySize,
                PhysicsCollisionObject.COLLISION_GROUP_02,
                PhysicsCollisionObject.COLLISION_GROUP_03,
                new AggroBehaviorChase(wa, 6f),
                new AggroBehaviorFight(1f, 2f)));
        enemy.finalise();
        enemy.getSpatial().setLocalTranslation(wa.getRandomPointInside(spawn.getY()));
        rootNode.attachChild(enemy.getSpatial());
    }

    public void initialiseEachLevel() {
        Node level1Food = (Node) ((Node) ((Node) island.getChild("SpawningPoints")).getChild("1")).getChild("Food");
        //Node level2Food = (Node) ((Node) ((Node) island.getChild("SpawningPoints")).getChild("2")).getChild("Food");
        //Node level3Food = (Node) ((Node) ((Node) island.getChild("SpawningPoints")).getChild("3")).getChild("Food");
        //Node level4Food = (Node) ((Node) ((Node) island.getChild("SpawningPoints")).getChild("4")).getChild("Food");

        Node[] foodSpawnLocations = new Node[]{level1Food}; //level2Food, level3Food, level4Food};

        for (Node node : foodSpawnLocations) {
            for (Spatial point : node.getChildren()) {
                Vector3f worldTranslation = point.getWorldTranslation();

                Apple apple = (Apple) entityManager.create(Entity.APPLE);
                apple.getSpatial().setLocalTranslation(worldTranslation.add(0, -15f, 0));
                apple.finalise();
                rootNode.attachChild(apple.getSpatial());
                currentLevel.getAllEntities().add(apple);
            }
        }
        */
    }
    private float time = 0;
    private boolean run = true;

    @Override
    public void update(float tpf) {
        super.update(tpf);
        /*time += tpf;
        if (run && time > 2f) {
            cc.play();
            run = false;
        } else if (!run && !cc.isRunning()) {
            mainCharacter.finalise();
            myApp.getStateManager().detach(this);

        }*/
    }

    public Node getGameMap() {
        return mapNode;
    }

    public void load(int gameMap) {

        if (!stateInitialised) {
            initialiseGameStatesOnce();
        }

        materialManager.load(gameMap);
        physicsManager.load(gameMap);
        soundManager.load(gameMap);
        graphicsManager.load(gameMap);

        switch (gameMap) {
            case 1:
                currentGameMap = new GameMap1();
                break;
        }

        //initialiseEachLevel();
    }

    /*
    public void restartLevel() {

        mainCharacter.getCharacterControl().setPhysicsLocation(new Vector3f(-130, 40, -60));

        // cleanup();

        //this calls currentLevel.load() inside
        //  myApp.getStateManager().attach(myApp.getMonkeyAppStateManager().getAppState(LoadingScreenAppState.class));
    }

    public void loadNextLevel() {
        if (currentIntLevel == NUM_LEVELS) {
            currentIntLevel = 1;
        } else {
            currentIntLevel++;
        }

        cleanup();
        myApp.getStateManager().attach(myApp.getMonkeyAppStateManager().getAppState(LoadingScreenAppState.class));
    }

    public void loadPreviousLevel() {
        if (currentIntLevel == 1) {
            currentIntLevel = NUM_LEVELS;
        } else {
            currentIntLevel--;
        }

        cleanup();
        myApp.getStateManager().attach(myApp.getMonkeyAppStateManager().getAppState(LoadingScreenAppState.class));
    }*/

    @Override
    public void cleanup() {
        materialManager.cleanup();
        physicsManager.cleanup();
        soundManager.cleanup();
        graphicsManager.cleanup();
        //currentLevel.cleanup();
        // animManager.cleanup();
    }
    
}
