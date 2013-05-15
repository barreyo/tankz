package GameControllers.logic;

import App.TanksAppAdapter;
import GameView.Sounds.ESounds;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioSource;
import java.util.EnumMap;

/**
 *
 * @author Per
 */
public enum SoundManager {

    /**
     *
     */
    INSTANCE;
    private EnumMap<ESounds, AudioNode> soundMap;
    private boolean muteSoundFX = false;
    private boolean muteMusic = false;

    /**
     *
     */
    private SoundManager() {
        soundMap = new EnumMap<ESounds, AudioNode>(ESounds.class);
    }

    public void load() {
        loadSound(new ESounds[]{ESounds.CLICK_SOUND,
                    ESounds.MISSILE_LAUNCH_SOUND, ESounds.MISSILI_COLLISION_SOUND,
                    ESounds.GAMEMUSIC_1});
    }

    /**
     *
     */
    public void preLoad() {
        loadSound(new ESounds[]{ESounds.MENU_SOUND});
    }

    // loads all sound effects which will be needed for that map
    private void loadSound(ESounds[] sounds) {
        for (ESounds s : sounds) {
            AudioNode soundNode;
            if (s.isMusic()) {
                soundNode = new AudioNode(TanksAppAdapter.INSTANCE.getAssetManager(), s.getPath(), true);
            } else {
                soundNode = new AudioNode(TanksAppAdapter.INSTANCE.getAssetManager(), s.getPath());
            }
            soundNode.setVolume(s.getVolume());
            soundNode.setPositional(false);
            soundMap.put(s, soundNode);
        }
    }

    /**
     *
     */
    private void removeAllMusic() {
        stopAllSounds();
        soundMap.clear();
    }

    /**
     *
     * @param sound
     */
    public void play(ESounds sound) {
        if (muteMusic) {
            return;
        }
        AudioNode audio = soundMap.get(sound);
        if (audio != null) {
            if (sound.isMusic()) {
                audio.play();
            } else {
                audio.playInstance();
            }
        }
    }

    // pause the music
    /**
     *
     * @param sound
     */
    public void pause(ESounds sound) {
        AudioNode audio = soundMap.get(sound);

        if (audio != null) {
            TanksAppAdapter.INSTANCE.pauseAudioSource(audio);
        }
    }

    // if paused it will play, if playing it will be paused
    /**
     *
     * @param sound
     */
    public void togglePlayPause(ESounds sound) {
        AudioNode toToggle = soundMap.get(sound);

        if (toToggle != null) {
            if (toToggle.getStatus() == AudioSource.Status.Paused
                    || toToggle.getStatus() == AudioSource.Status.Stopped) {
                play(sound);
            } else {
                pause(sound);
            }
        }
    }

    // tries to stop a sound, will probably only work for streaming music though
    /**
     *
     * @param sound
     */
    public void stop(ESounds sound) {
        AudioNode toStop = soundMap.get(sound);
        if (toStop != null) {
            toStop.stop();
        }
    }

    /**
     *
     */
    public void cleanup() {
        removeAllMusic();
        soundMap.clear();
    }

    void stopAllSounds() {
        for (AudioNode audio : soundMap.values()) {
            audio.stop();
        }
    }

    /**
     * Sets the boolean mute, which represents enabled, or disabled FX sound.
     *
     * @param mute enables or disables FX sound.
     */
    public void setMuteSoundFx(boolean mute) {
        muteSoundFX = mute;
    }

    /**
     * Returns a boolean which tells if the FX sounds are muted.
     *
     * @return isSoundFXMuted
     */
    public boolean isSoundFXMuted() {
        return muteSoundFX;
    }

    /**
     * Sets a boolean mute, which represents enabled, or disabled music.
     *
     * @param mute mutes or unmutes the music.
     */
    public void setMuteMusic(boolean mute) {
        muteMusic = mute;
    }

    /**
     * Returns a boolean which tells if the music is muted.
     *
     * @return muteMusic
     */
    public boolean isMusicMuted() {
        return muteMusic;
    }
    
    public void toggleMusic() {
        muteMusic = !muteMusic;
        togglePlayPause(ESounds.MENU_SOUND);
        togglePlayPause(ESounds.GAMEMUSIC_1);
    }
    
    public void toggleFX() {
        muteSoundFX = !muteSoundFX;
    }
}
