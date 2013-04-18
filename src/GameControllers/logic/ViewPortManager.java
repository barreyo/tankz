package GameControllers.logic;

import GameModel.ApplicationSettings;
import GameModel.IPlayer;
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
    
    private HashMap<String, EViewPorts> views = new HashMap<String, EViewPorts>();

    private void initViews() {
        List<String> playerNames = MenuAppState.getInstance().getPlayerNames();
        switch(playerNames.size()) {
            case 0:   
            case 1:
                views.put(playerNames.get(0), EViewPorts.CENTER);
                break;
            case 2:
                views.put(playerNames.get(0), EViewPorts.TOP);
                views.put(playerNames.get(1), EViewPorts.BOTTOM);
                break;
            case 3:
                views.put(playerNames.get(0),EViewPorts.TOP_LEFT);
                views.put(playerNames.get(1), EViewPorts.TOP_RIGHT);
                views.put(playerNames.get(2), EViewPorts.BOTTOM);
                break;
            default:
                views.put(playerNames.get(0), EViewPorts.TOP_LEFT);
                views.put(playerNames.get(1), EViewPorts.TOP_RIGHT);
                views.put(playerNames.get(2), EViewPorts.BOTTOM_Left);
                views.put(playerNames.get(3), EViewPorts.BOTTOM_RIGHT);
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
    public ViewPort getViewportForPlayer(String name) {
        return views.get(name).getViewPort();
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
