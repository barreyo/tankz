/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.AppStates;

import GameLogicLayer.Game.TanksGame;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;

/**
 *
 * @author Daniel
 */
public class BackgroundMusicAppState extends AbstractAppState {
    
    private boolean isActive;
    private AudioNode music;

    public BackgroundMusicAppState(TanksGame app) {
        music = new AudioNode(app.getAssetManager(), "Sounds/Music/bg1.ogg", true);
        music.setLooping(false);
        music.setVolume(0.5f);
        music.setPositional(false);
        music.setDirectional(false);
    }

    @Override
    public void stateAttached(AppStateManager stateManager) {
        isActive = true;
    }

    @Override
    public void stateDetached(AppStateManager stateManager) {
        isActive = false;
        music.stop();
    }

    @Override
    public void update(float tpf) {
        if (isActive) {
            if (music.getStatus() == AudioNode.Status.Stopped) {
                music.play();
            }
        }
    }
}
