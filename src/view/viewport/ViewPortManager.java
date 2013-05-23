package view.viewport;

import application.TanksAppAdapter;
import com.jme3.renderer.ViewPort;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Manager for the game Tanks that handles viewports.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum ViewPortManager {
    
    /**
     * Gain access to this manager.
     */
    INSTANCE;
    
    private HashMap<String, EViewPorts> views = new HashMap<String, EViewPorts>();

    private void initViews(List<String> names) {
        List<String> playerNames = names;
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
                views.put(playerNames.get(0), EViewPorts.TOP_LEFT);
                views.put(playerNames.get(1), EViewPorts.TOP_RIGHT);
                views.put(playerNames.get(2), EViewPorts.BOTTOM);
                break;
            default:
                views.put(playerNames.get(0), EViewPorts.TOP_LEFT);
                views.put(playerNames.get(1), EViewPorts.TOP_RIGHT);
                views.put(playerNames.get(2), EViewPorts.BOTTOM_Left);
                views.put(playerNames.get(3), EViewPorts.BOTTOM_RIGHT);
                break;
        }
    }
    
    /**
     * Loads the right viewports determined by number of players.
     */
    public void load(List<String> playerNames) {
        initViews(playerNames);
        for (EViewPorts view : views.values()) {
            view.getViewPort().attachScene(TanksAppAdapter.INSTANCE.getRootNode());
        }
    };
    
    /**
     * Returns the viewport for the specified player.
     * 
     * @param name player name.
     * @return the viewport for the specified player.
     */
    public ViewPort getViewportForPlayer(String name) {
        return views.get(name).getViewPort();
    }
    
    /**
     * Get all viewports.
     * 
     * @return list of viewports.
     */
    public Collection<EViewPorts> getViews() {
        return Collections.unmodifiableCollection(views.values());
    }

    /**
     * Releases the resources contained by this manager.
     */
    public void cleanup() {
        for (EViewPorts view : views.values()) {
            view.getViewPort().detachScene(TanksAppAdapter.INSTANCE.getRootNode());
            view.getViewPort().setEnabled(false);
            view.cleanup();
        }
        views.clear();
    }
}
