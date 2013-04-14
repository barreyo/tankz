package GameView.Map;

import GameControllers.logic.GraphicManager;
import GameControllers.entitycontrols.ControlFactory;
import GameControllers.entitycontrols.TanksVehicleControl;
import GameModel.Game.TanksGameModel;
import GameModel.Player.IPlayer;
import GameUtilities.TankAppAdapter;
import GameView.gameEntity.AGameEntity;
import GameView.gameEntity.TankEntity;
import GameView.graphics.EGraphics;
import com.jme3.scene.Node;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * First developed game map for tha game Tanks.
 *
 * @author Daniel
 */
public class GameWorld1 implements IGameWorld, PropertyChangeListener {
    
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
        //TankAppAdapter.INSTANCE.getPhysicsSpace().enableDebug(TankAppAdapter.INSTANCE.getAssetManager());
        
        for (IPlayer player : game.getPlayers()) {
            // Create a tank for each player
            TankEntity tank1 = new TankEntity();
            // Attach to map node at startpos
            tank1.getSpatial().move(10, 2, 10);
            mapNode.attachChild(tank1.getSpatial());
            
            // Add controls to tank
            TanksVehicleControl tanksVehicleControl = ControlFactory.getTankControl(tank1, player);
            
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

    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
