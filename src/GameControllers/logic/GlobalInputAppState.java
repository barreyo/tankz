
package GameControllers.logic;

import App.TanksAppAdapter;
import GameModel.EGlobalInputs;
import GameView.Sounds.ESounds;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;

/**
 * Inputs not specific to a player, listens in both menus 
 * and while playing.
 * 
 * @author Johan Backman
 */
public class GlobalInputAppState extends AbstractAppState implements ActionListener {
    
    private static GlobalInputAppState instance;
    
    private String musicToggle;
    private String fxToggle;
    
    private EGlobalInputs soundInputs;
   
    /**
     * Instance of GlobalInputAppState.
     * 
     * @return instance.
     */
    public static synchronized GlobalInputAppState getInstance() {
        if (instance == null) {
            instance = new GlobalInputAppState();
        }
        return instance;
    }
    
    private void addInputMappings() {
        soundInputs = EGlobalInputs.SoundInput;
        
        int musicToggleInt = soundInputs.getToggleMusicButton();
        int fxToggleInt = soundInputs.getToggleFXButton();
        
        musicToggle = "" + musicToggleInt;
        fxToggle = "" + fxToggleInt;
        
        TanksAppAdapter.INSTANCE.addInputMapping(musicToggle, new KeyTrigger(musicToggleInt));
        TanksAppAdapter.INSTANCE.addInputMapping(fxToggle, new KeyTrigger(fxToggleInt));
        
        TanksAppAdapter.INSTANCE.addInputListener(this, musicToggle, fxToggle);
    }
    
    private void removeInputMappings() {
        TanksAppAdapter.INSTANCE.deleteInputMapping(fxToggle);
        TanksAppAdapter.INSTANCE.deleteInputMapping(musicToggle);
        TanksAppAdapter.INSTANCE.removeInputListener(this);
    }
    
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals(musicToggle)) {
            if (isPressed) {
                SoundManager.INSTANCE.toggleMusic();
                MenuAppState.getInstance().updateSettingsOptions();
            }
        }
        if (name.equals(fxToggle)) {
            if (isPressed) {
                SoundManager.INSTANCE.toggleFX();
                MenuAppState.getInstance().updateSettingsOptions();
            }
        } 
    }
    
    @Override
    public void stateAttached(AppStateManager stateManager) {
        addInputMappings();
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager){
        removeInputMappings();
    }
}
