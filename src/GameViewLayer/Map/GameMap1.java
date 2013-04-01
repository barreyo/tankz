package GameViewLayer.Map;

import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.entity.GameEntityManager;
import GameViewLayer.gameEntity.EGameEntities;
import GameViewLayer.gameEntity.AGameEntity;
import GameViewLayer.gameEntity.Tank;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.List;

;
/**
 * First developed game map for tha game Tanks.
 *
 * @author Daniel
 */
public class GameMap1 implements IGameMap {
    
    private TanksGame app;
    private GameEntityManager entityManager;
    
    private Node mapNode;
    private Node rootNode;
    
    private List<AGameEntity> allGameEntities;
    
    /**
     * Creates a game map.
     */
    public GameMap1() {
        app = TanksGame.getApp();
        rootNode = app.getRootNode();
        entityManager = app.getEntityManager();
        
        allGameEntities = new ArrayList<AGameEntity>();
    }

    /**
     * @inheritdoc
     */
    public void load() {
        mapNode = (Node) app.getAssetManager().loadModel("Scenes/Map1/Map3.j3o");
        rootNode.attachChild(mapNode);
        
        app.getBulletAppState().getPhysicsSpace().addAll(mapNode);
        //app.getBulletAppState().getPhysicsSpace().enableDebug(app.getAssetManager());
        
        Camera cam1 = app.getCamera().clone();
        cam1.setViewPort(0f, 1f, 0f, 0.5f);
        
        ViewPort view1 = app.getRenderManager().createMainView("Bottom Left", cam1);
        view1.setClearFlags(true, true, true);
        view1.attachScene(app.getRootNode());
        
        Camera cam2 = app.getCamera().clone();
        cam2.setViewPort(0f, 1f, 0.5f, 1f);
                
        ViewPort view2 = app.getRenderManager().createMainView("Bottom Left", cam2);
        view2.setClearFlags(true, true, true);
        view2.attachScene(app.getRootNode());
        
        Tank tank1 = (Tank) entityManager.create(EGameEntities.TANK);
        tank1.getSpatial().move(10, 2, 10);
        rootNode.attachChild(tank1.getSpatial());
        tank1.finalise();
        tank1.getTanksVehicleControl().setCamera(cam1);
        allGameEntities.add(tank1);
        
 
        Tank tank2 = (Tank) entityManager.create(EGameEntities.TANK);
        tank2.getSpatial().move(10, 2, 10);
        rootNode.attachChild(tank2.getSpatial());
        tank2.finalise();
        tank2.getTanksVehicleControl().setCamera(cam2);
        allGameEntities.add(tank2);
    }

    /**
     * @inheritdoc
     */
    public void cleanup() {
        rootNode.detachChild(mapNode);
        for (AGameEntity gameEntity : allGameEntities) {
            gameEntity.cleanup(); // should remove all physics and controls
            gameEntity.getSpatial().removeFromParent(); // remove from scene graph
        }

        allGameEntities.clear();
        allGameEntities = null;
    }
    
    /**
     * @inheritdoc
     */
    public List<AGameEntity> getAllEntities() {
        return allGameEntities;
    }
}
