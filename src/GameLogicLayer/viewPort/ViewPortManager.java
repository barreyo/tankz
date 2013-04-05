package GameLogicLayer.viewPort;

import GameLogicLayer.Game.GameManager;
import GameLogicLayer.Game.TanksGame;
import GameModelLayer.Player.Player;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Daniel
 */
public class ViewPortManager {
    
    private HashMap<Player, EViewPorts> views = new HashMap<Player, EViewPorts>();
    private Node rootNode;
    private GameManager gameManager;
    
    public ViewPortManager() {
        TanksGame app = TanksGame.getApp();
        this.gameManager = app.getGameManager();
        this.rootNode = app.getRootNode();
    }

    private void initViews() {
        List<Player> players = gameManager.getPlayers();
        switch(players.size()) {
            case 0:   
            case 1:
                views.put(players.get(0), EViewPorts.CENTER);
                break;
            case 2:
                views.put(players.get(0), EViewPorts.TOP);
                views.put(players.get(1), EViewPorts.BOTTOM);
                break;
            case 3:
                views.put(players.get(0),EViewPorts.TOP_LEFT);
                views.put(players.get(1), EViewPorts.TOP_RIGHT);
                views.put(players.get(2), EViewPorts.BOTTOM);
                break;
            default:
                views.put(players.get(0), EViewPorts.TOP_LEFT);
                views.put(players.get(1), EViewPorts.TOP_RIGHT);
                views.put(players.get(2), EViewPorts.BOTTOM_Left);
                views.put(players.get(3), EViewPorts.BOTTOM_RIGHT);
                break;
        };
    }
    
    public void load() {
        initViews();
        for (EViewPorts view : views.values()) {
            view.getViewPort().attachScene(rootNode);
        }
    };
    
    public ViewPort getViewportForPlayer(Player p) {
        return views.get(p).getViewPort();
    }
    
    public Collection<EViewPorts> getViews() {
        return views.values();
    }

    public void cleanup() {
        views.clear();
    }
}
