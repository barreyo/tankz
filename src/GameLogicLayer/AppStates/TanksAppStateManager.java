/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.AppStates;

import com.jme3.app.state.AbstractAppState;
import GameLogicLayer.util.Manager;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the different appstates in game.
 *
 * @author Daniel
 */
public class TanksAppStateManager implements Manager {

    private List<AbstractAppState> appStates = new ArrayList<AbstractAppState>(10);

    /**
     * Returns an app state of the specified class.
     * If there exists an app state instance of the given class that instance 
     * will be returned.
     * 
     * @param <T> The app state type, extends AbstractAppState
     * @param appStateClass The specified class that extends AbstractAppState
     * @return The appstate instance of the specified class.
     */
    public <T extends AbstractAppState> AbstractAppState getAppState(Class<T> appStateClass) {
        for (AbstractAppState state : appStates) {
            if (appStateClass.isAssignableFrom(state.getClass())) {
                return (T) state;
            }
        }
        // return a new instance
        try {
            AbstractAppState state = appStateClass.newInstance();
            appStates.add(state);
            return (T) state;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    /**
     * Returns a list with all app states.
     * 
     * @return a list with all app states
     */
    protected List<AbstractAppState> getStates() {
        return appStates;
    }

    // TODO ta bort implements Manager?
    /**
     *
     * @param level
     */
    public void load(int level) {
    }

    /**
     *
     */
    public void cleanup() {
    }
}
