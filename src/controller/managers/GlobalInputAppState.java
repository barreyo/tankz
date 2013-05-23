
package controller.managers;

import application.TanksAppAdapter;
import model.EGlobalInputs;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;

/**
 * Inputs not specific to a player, listens in both menus 
 * and while playing.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class GlobalInputAppState extends AbstractAppState implements ActionListener {
    
    private static GlobalInputAppState instance;
    
    private String musicToggle, fxToggle, changeMap;
    
    private EGlobalInputs globalInputs;
   
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
        globalInputs = EGlobalInputs.GlobalInput;
        
        int musicToggleInt = globalInputs.getToggleMusicButton();
        int fxToggleInt = globalInputs.getToggleFXButton();
        int changeMapInt = globalInputs.getChangeMapButton();
        
        musicToggle = "" + musicToggleInt;
        fxToggle = "" + fxToggleInt;
        changeMap = "" + changeMapInt;
        
        
        TanksAppAdapter.INSTANCE.addInputMapping(musicToggle, new KeyTrigger(musicToggleInt));
        TanksAppAdapter.INSTANCE.addInputMapping(fxToggle, new KeyTrigger(fxToggleInt));
        TanksAppAdapter.INSTANCE.addInputMapping(changeMap, new KeyTrigger(changeMapInt));
        
        TanksAppAdapter.INSTANCE.addInputListener(this, musicToggle, fxToggle, changeMap);
    }
    
    private void removeInputMappings() {
        TanksAppAdapter.INSTANCE.deleteInputMapping(fxToggle);
        TanksAppAdapter.INSTANCE.deleteInputMapping(musicToggle);
        TanksAppAdapter.INSTANCE.deleteInputMapping(changeMap);
        TanksAppAdapter.INSTANCE.removeInputListener(this);
    }
    
    /**
     * {@inheritDoc}
     */
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
        if (name.equals(changeMap)){
            if(isPressed){
                GameMapManager.INSTANCE.loadNextMap();
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */    
    @Override
    public void stateAttached(AppStateManager stateManager) {
        addInputMappings();
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public void stateDetached(AppStateManager stateManager){
        removeInputMappings();
    }
}
