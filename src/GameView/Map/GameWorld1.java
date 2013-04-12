package GameView.Map;

import GameControllers.logic.GraphicManager;
import GameModel.Game.TanksFactory;
import App.TanksApp;
import GameControllers.entitycontrols.ControlFactory;
import GameControllers.entitycontrols.EControls;
import GameControllers.entitycontrols.TanksVehicleControl;
import GameView.gameEntity.GameEntityFactory;
import GameControllers.logic.ViewPortManager;
import GameModel.Game.TanksGameModel;
import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import GameUtilities.TankAppAdapter;
import GameView.gameEntity.EGameEntities;
import GameView.gameEntity.AGameEntity;
import GameView.gameEntity.Tank;
import GameView.graphics.EGraphics;
import com.jme3.renderer.ViewPort;
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
//        app.getBulletAppState().getPhysicsSpace().enableDebug(app.getAssetManager());
        
        for (IPlayer player : game.getPlayers()) {
            // Create a tank for each player
            Tank tank1 = (Tank) GameEntityFactory.create(EGameEntities.TANK);
            // Attach to root node at startpos
            tank1.getSpatial().move(10, 2, 10);
            TankAppAdapter.INSTANCE.attachChildToRootNode(tank1.getSpatial());
            // Add controls to tank
            TanksVehicleControl tanksVehicleControl = (TanksVehicleControl)ControlFactory.getControl(EControls.VEHICLE_CONTROL);
            tank1.addControl(tanksVehicleControl);
            
            // Get the right viewport for the player and enable it
            ViewPort viewPort = ViewPortManager.INSTANCE.getViewportForPlayer(player);
            viewPort.setEnabled(true);
            // Give the tank a refernce to the camera of the viewport
            tanksVehicleControl.setCamera(viewPort.getCamera());
            
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
