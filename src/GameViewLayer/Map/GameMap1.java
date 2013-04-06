package GameViewLayer.Map;

import GameLogicLayer.Game.GameManager;
import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.entity.GameEntityManager;
import GameLogicLayer.viewPort.ViewPortManager;
import GameModelLayer.Player.Player;
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
    private ViewPortManager viewPortManager;
    private GameManager gameManager;
    
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
        viewPortManager = app.getViewPortManager();
        gameManager = app.getGameManager();
        
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
        
        for (Player player : gameManager.getPlayers()) {
            Tank tank1 = (Tank) entityManager.create(EGameEntities.TANK);
            tank1.getSpatial().move(10, 2, 10);
            rootNode.attachChild(tank1.getSpatial());
            tank1.finalise();
            ViewPort viewPort = viewPortManager.getViewportForPlayer(player);
            viewPort.setEnabled(true);
            tank1.getTanksVehicleControl().setCamera(viewPort.getCamera());
            allGameEntities.add(tank1);
        }
    }

    /**
     * @inheritdoc
     */
    public void cleanup() {
        for (AGameEntity gameEntity : allGameEntities) {
            gameEntity.cleanup(); // should remove all physics and controls
            gameEntity.getSpatial().removeFromParent(); // remove from scene graph
        }
        rootNode.detachChild(mapNode);

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
