package GameViewLayer.Map;

import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.entity.GameEntityManager;
import GameViewLayer.gameEntity.ETanksEntity;
import GameViewLayer.gameEntity.GameEntity;
import GameViewLayer.gameEntity.Tank;
import com.jme3.app.state.AbstractAppState;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import java.util.LinkedList;

;
/**
 *
 * @author Daniel
 */
public class GameMap1 implements GameMap {
    
    private TanksGame app;
    private GameEntityManager entityManager;
    
    private Node mapNode;
    private Node rootNode;
    
    public GameMap1() {
        app = TanksGame.getApp();
        rootNode = app.getRootNode();
        entityManager = app.getEntityManager();
    }

    /**
     *
     */
    public void load() {
        mapNode = (Node) app.getAssetManager().loadModel("Scenes/Map1/Map3.j3o");
        rootNode.attachChild(mapNode);
        
        //PhysicsTestHelper.createPhysicsTestWorld(rootNode, app.getAssetManager(), app.getBulletAppState().getPhysicsSpace());
        
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
        
        Tank mainTank = (Tank) entityManager.create(ETanksEntity.TANK);
        mainTank.getSpatial().move(10, 2, 10);
        rootNode.attachChild(mainTank.getSpatial());
        mainTank.finalise();
        mainTank.getTanksVehicleControl().setCamera(cam1);
 
        Tank mainTank2 = (Tank) entityManager.create(ETanksEntity.TANK);
        mainTank2.getSpatial().move(10, 2, 10);
        rootNode.attachChild(mainTank2.getSpatial());
        mainTank2.finalise();
        mainTank2.getTanksVehicleControl().setCamera(cam2);
        
        //app.getStateManager().detach(this);
    }

    /**
     *
     */
    public void cleanup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     *
     * @return
     */
    public LinkedList<GameEntity> getAllEntities() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
