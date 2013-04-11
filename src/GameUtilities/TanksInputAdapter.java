package GameUtilities;

import GameControllers.logic.TanksGame;
import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.Trigger;

/**
 *
 * @author Daniel
 */
public enum TanksInputAdapter {
    INSTANCE;
    
    private final InputManager inputManager;
    
    private TanksInputAdapter() {
        inputManager = TanksGame.getApp().getInputManager();
    }
    
    public boolean hasMapping(String mapping) {
        return inputManager.hasMapping(mapping);
    }

    public void deleteMapping(String mapping) {
        inputManager.deleteMapping(mapping);
    }

    public void addMapping(String mapping, Trigger... triggers) {
        inputManager.addMapping(mapping, triggers);
    }

    public void addListener(ActionListener actionListener, String... mappingNames) {
        inputManager.addListener(actionListener, mappingNames);
    }

    public void removeListener(ActionListener actionListener) {
        inputManager.removeListener(actionListener);
    }
    
    public InputManager getInputManager() {
        return inputManager;
    }

    public void setCursorVisible(boolean bool) {
        inputManager.setCursorVisible(bool);
    }
}
