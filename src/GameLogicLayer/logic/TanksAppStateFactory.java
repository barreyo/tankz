package GameLogicLayer.logic;

import com.jme3.app.state.AbstractAppState;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the different appstates in game.
 *
 * @author Daniel
 */
public class TanksAppStateFactory {
    private static TanksAppStateFactory instance;

    private List<AbstractAppState> appStates = new ArrayList<AbstractAppState>(10);
    
    private TanksAppStateFactory() {}
    
    public static synchronized TanksAppStateFactory getInstance() {
        if (instance == null) {
            instance = new TanksAppStateFactory();
        }
        return instance;
    }

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
}
