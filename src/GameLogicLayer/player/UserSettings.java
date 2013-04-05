
package GameLogicLayer.player;

/**
 *
 * @author Daniel
 */
public class UserSettings {
    private boolean muteSoundFX;
    private boolean muteMusic;

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
