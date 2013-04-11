package GameView.Map;

import GameControllers.logic.GraphicManager;
import GameModel.Game.TanksFactory;
import App.TanksApp;
import GameView.gameEntity.GameEntityFactory;
import GameControllers.logic.ViewPortManager;
import GameModel.Game.TanksGameModel;
import GameModel.Player.Player;
import GameUtilities.TankAppAdapter;
import GameView.gameEntity.EGameEntities;
import GameView.gameEntity.AGameEntity;
import GameView.gameEntity.Tank;
import GameView.graphics.EGraphics;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.List;

/**
 * First developed game map for tha game Tanks.
 *
 * @author Daniel
 */
public class GameWorld1 implements IGameWorld {
    
    private TanksGameModel game;
    
    private Node mapNode;
    
    private List<AGameEntity> allGameEntities;
    
    /**
     * Creates a game map.
     */
    public GameWorld1(TanksGameModel game) {
        this.game = game;
        allGameEntities = new ArrayList<AGameEntity>();
    }

    /**
     * @inheritdoc
     */
    public void load() {
        // Load, attach map to root node, and add nodes and geoms in the map to physicsspace
        mapNode = (Node) GraphicManager.INSTANCE.createSpatial(EGraphics.MAP);
        TankAppAdapter.INSTANCE.attachChildToRootNode(mapNode);
        TankAppAdapter.INSTANCE.addAllToPhysicsSpace(mapNode);
//        app.getBulletAppState().getPhysicsSpace().enableDebug(app.getAssetManager());
        
        for (Player player : game.getPlayers()) {
            // Create a tank for each player
            Tank tank1 = (Tank) GameEntityFactory.create(EGameEntities.TANK);
            // Attach to root node at startpos
            tank1.getSpatial().move(10, 2, 10);
            TankAppAdapter.INSTANCE.attachChildToRootNode(tank1.getSpatial());
            // Add controls to tank
            tank1.finalise();
            
            // Get the right viewport for the player and enable it
            ViewPort viewPort = ViewPortManager.INSTANCE.getViewportForPlayer(player);
            viewPort.setEnabled(true);
            // Give the tank a refernce to the camera of the viewport
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
        TankAppAdapter.INSTANCE.detachChildFromRootNode(mapNode);

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
