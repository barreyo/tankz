package GameView.Map;

import GameControllers.logic.GraphicManager;
import GameControllers.entitycontrols.ControlFactory;
import GameModel.Game.TanksGameModel;
import GameModel.Player.IPlayer;
import GameUtilities.TankAppAdapter;
import GameView.graphics.EGraphics;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.util.SkyFactory;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * First developed game map for tha game Tanks.
 *
 * @author Daniel
 */
public class GameWorld1 implements IGameWorld, PropertyChangeListener {
    
    private TanksGameModel game;
    
    private Node mapNode;
    private Node mainScene;
    private DirectionalLight sun;
    
    private static final Vector3f LIGHT_DIR = new Vector3f(-4.9236743f, -1.27054665f, 5.896916f);
    
    /**
     * Creates a game map.
     */
    public GameWorld1(TanksGameModel game) {
        this.game = game;
    }

    /**
     * @inheritdoc
     */
    public void load() {
        mainScene = new Node("Main Scene");
        TankAppAdapter.INSTANCE.attachChildToRootNode(mainScene);
        
        sun = new DirectionalLight();
        sun.setDirection(LIGHT_DIR);
        sun.setColor(ColorRGBA.White.clone().multLocal(1.7f));
        TankAppAdapter.INSTANCE.addLightToRootNode(sun);
        
        Spatial sky = SkyFactory.createSky(TankAppAdapter.INSTANCE.getAssetManager(), 
                                        "Scenes/FullskiesSunset0068.dds", false);
        sky.setLocalScale(350);
        
        mainScene.attachChild(sky);
        
        // Load, attach map to root node, and add nodes and geoms in the map to physicsspace
        mapNode = (Node) GraphicManager.INSTANCE.createSpatial(EGraphics.MAP);
        TankAppAdapter.INSTANCE.attachChildToRootNode(mapNode);
        TankAppAdapter.INSTANCE.addAllToPhysicsSpace(mapNode);
        //TankAppAdapter.INSTANCE.getPhysicsSpace().enableDebug(TankAppAdapter.INSTANCE.getAssetManager());
        
        for (IPlayer player : game.getPlayers()) {
            // Create a tank for each player at startpos
            ControlFactory.createTank(player, new Vector3f(10, 2, 10));
        }
        
        ControlFactory.createNewPowerup(new Vector3f(20,1.5f,20));
    }

    /**
     * @inheritdoc
     */
    public void cleanup() {
        TankAppAdapter.INSTANCE.detachAllRootChildren();
        TankAppAdapter.INSTANCE.removeLightFromRootNode(sun);
        
        /*for (IGameEntity gameEntity : allGameEntities) {
            gameEntity.cleanup(); // should remove all physics and controls
            gameEntity.getSpatial().removeFromParent(); // remove from scene graph
        }
        TankAppAdapter.INSTANCE.detachChildFromRootNode(mapNode);

        allGameEntities.clear();
        allGameEntities = null;*/
    }

    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
