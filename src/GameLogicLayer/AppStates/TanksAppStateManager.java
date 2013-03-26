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
 *
 * @author Daniel
 */
public class TanksAppStateManager implements Manager {

    private List<AbstractAppState> appStates = new ArrayList<AbstractAppState>(5);

    /**
     *
     * @param <T>
     * @param appStateClass
     * @return
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
     *
     * @return
     */
    protected List<AbstractAppState> getStates() {
        return appStates;
    }

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
