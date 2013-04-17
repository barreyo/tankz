package GameControllers.logic;

import GameModel.Game.UserSettings;
import GameModel.Player.IPlayer;
import GameView.viewPort.EViewPorts;
import App.TanksAppAdapter;
import com.jme3.renderer.ViewPort;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Manager for the game Tanks that handles viewports.
 *
 * @author Daniel
 */
public enum ViewPortManager {
    INSTANCE;
    
    private HashMap<IPlayer, EViewPorts> views = new HashMap<IPlayer, EViewPorts>();

    private void initViews() {
        List<IPlayer> players = UserSettings.INSTANCE.getPlayers();
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
    
    /**
     * Loads the right viewports determined by number of players.
     */
    public void load() {
        initViews();
        for (EViewPorts view : views.values()) {
            view.getViewPort().attachScene(TanksAppAdapter.INSTANCE.getRootNode());
        }
    };
    
    /**
     * Returns the viewport for the specified player.
     * 
     * @param p The player which you want the viewport for
     * @return the viewport for the specified player
     */
    public ViewPort getViewportForPlayer(IPlayer p) {
        return views.get(p).getViewPort();
    }
    
    /**
     * Remove this later --> dont share the reference to the collection
     * @return 
     */
    public Collection<EViewPorts> getViews() {
        return views.values();
    }

    /**
     * Releases the resources contained by this manager.
     */
    public void cleanup() {
        views.clear();
    }
}
