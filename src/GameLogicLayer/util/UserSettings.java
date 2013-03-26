/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.util;

import GameLogicLayer.Game.TanksGame;

/**
 *
 * @author Daniel
 */
public class UserSettings {
    private TanksGame app;
    private boolean muteSoundFX;
    private boolean muteMusic;

    /**
     *
     */
    public UserSettings() {
        app = TanksGame.getApp();
    }

    /**
     *
     * @param mute
     */
    public void setMuteSoundFx(boolean mute) {
        muteSoundFX = mute;
    }

    /**
     *
     * @return
     */
    public boolean isSoundFXMuted() {
        return muteSoundFX;
    }

    /**
     *
     * @param mute
     */
    public void setMuteMusic(boolean mute) {
        muteMusic = mute;
    }

    /**
     *
     * @return
     */
    public boolean isMusicMuted() {
        return muteMusic;
    }
}
