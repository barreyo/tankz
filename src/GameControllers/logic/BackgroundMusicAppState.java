package GameControllers.logic;

import App.TanksAppAdapter;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;

/**
 * An app state playing background music.
 *
 * @author Daniel
 */
public class BackgroundMusicAppState extends AbstractAppState {
    private static BackgroundMusicAppState instance;
    
    private boolean isActive;
    private AudioNode music;

    /**
     * Creates a new app state for background music.
     * 
     */
    private BackgroundMusicAppState() {
        music = new AudioNode(TanksAppAdapter.INSTANCE.getAssetManager(), "Sounds/Music/bg1.ogg", true);
        music.setLooping(false);
        music.setVolume(0.5f);
        music.setPositional(false);
        music.setDirectional(false);
    }
    
    public static synchronized BackgroundMusicAppState getInstance() {
        if (instance == null) {
            instance = new BackgroundMusicAppState();
        }
        return instance;
    }

    /**
     * Called when appstate is attached to statemanager.
     * 
     * @param stateManager The statemanager that this is attached to.
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        isActive = true;
    }

    /**
     * Called when appstate is detached from statemanager.
     * 
     * @param stateManager
     */
    @Override
    public void stateDetached(AppStateManager stateManager) {
        isActive = false;
        music.stop();
    }

    /**
     * Called as long as appstate is attached to statemanager.
     * 
     * @param tpf 
     */
    @Override
    public void update(float tpf) {
        if (isActive) {
            if (music.getStatus() == AudioNode.Status.Stopped) {
                music.play();
            }
        }
    }
}
