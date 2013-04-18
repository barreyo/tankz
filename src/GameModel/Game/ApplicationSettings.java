
package GameModel.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Has all the settings of the game, and handles accordingly.
 * 
 * @author Daniel
 */
public enum ApplicationSettings {
    INSTANCE;
    
    // Sound related
    private boolean muteSoundFX;
    private boolean muteMusic;

    /**
     * Sets the boolean mute, which represents enabled, or disabled FX sound.
     * @param mute enables or disables FX sound.
     */
    public void setMuteSoundFx(boolean mute) {
        muteSoundFX = mute;
    }

    /**
     * Returns a boolean which tells if the FX sounds are muted.
     * @return isSoundFXMuted
     */
    public boolean isSoundFXMuted() {
        return muteSoundFX;
    }

    /**
     * Sets a boolean mute, which represents enabled, or disabled music.
     * @param mute mutes or unmutes the music.
     */
    public void setMuteMusic(boolean mute) {
        muteMusic = mute;
    }

    /**
     * Returns a boolean which tells if the music is muted.
     * @return muteMusic
     */
    public boolean isMusicMuted() {
        return muteMusic;
    }
}
