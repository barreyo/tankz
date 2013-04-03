package GameViewLayer.Map;

import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.entity.GameEntityManager;
import GameLogicLayer.viewPort.EViewPorts;
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
        
        ViewPort view1 = EViewPorts.VIEW1.getViewPort();
        view1.attachScene(app.getRootNode());
        
        ViewPort view2 = EViewPorts.VIEW2.getViewPort();
        view2.attachScene(app.getRootNode());
        
        Tank tank1 = (Tank) entityManager.create(EGameEntities.TANK);
        tank1.getSpatial().move(10, 2, 10);
        rootNode.attachChild(tank1.getSpatial());
        tank1.finalise();
        tank1.getTanksVehicleControl().setCamera(view1.getCamera());
        allGameEntities.add(tank1);
 
        Tank tank2 = (Tank) entityManager.create(EGameEntities.TANK);
        tank2.getSpatial().move(10, 2, 10);
        rootNode.attachChild(tank2.getSpatial());
        tank2.finalise();
        tank2.getTanksVehicleControl().setCamera(view2.getCamera());
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
