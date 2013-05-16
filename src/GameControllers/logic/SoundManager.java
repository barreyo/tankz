package GameControllers.logic;

import App.TanksAppAdapter;
import GameView.Sounds.ESounds;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioSource;
import java.util.EnumMap;

/**
 * Manager managing sounds in the game.
 *  
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum SoundManager {

    /**
     * Gain access to this singleton.
     */
    INSTANCE;
    
    private EnumMap<ESounds, AudioNode> soundMap;
    private boolean muteSoundFX = false;
    private boolean muteMusic = false;

    private SoundManager() {
        soundMap = new EnumMap<ESounds, AudioNode>(ESounds.class);
    }

    /**
     * Load all sounds in to the buffer.
     */
    public void load() {
        loadSound(new ESounds[]{ESounds.CLICK_SOUND,
                    ESounds.MISSILE_LAUNCH_SOUND, ESounds.MISSILI_COLLISION_SOUND,
                    ESounds.GAMEMUSIC_1});
    }

    /**
     * Preload sounds for instant usage.
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
     * Remove all music from the buffer.
     */
    private void removeAllMusic() {
        stopAllSounds();
        soundMap.clear();
    }

    /**
     * Play a specific sound.
     * 
     * @param sound sound from enum.
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

    /**
     * Pause a specific sound
     * 
     * @param sound sound from enum.
     */
    public void pause(ESounds sound) {
        AudioNode audio = soundMap.get(sound);

        if (audio != null) {
            TanksAppAdapter.INSTANCE.pauseAudioSource(audio);
        }
    }

    /**
     * Toggle a sound between playing or pausing. If a sound is paused it will
     * start playing and the other way around if playing.
     * 
     * @param sound sound to toggle.
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

    /**
     * Stop a sound completely. 
     * 
     * @param sound sound to stop.
     */
    public void stop(ESounds sound) {
        AudioNode toStop = soundMap.get(sound);
        if (toStop != null) {
            toStop.stop();
        }
    }

    /**
     * Clear all music from the buffer.
     */
    public void cleanup() {
        removeAllMusic();
        soundMap.clear();
    }

    /**
     * Stop all sounds on the Audio node.
     */
    public void stopAllSounds() {
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
    
    /**
     * Toggle all music playing/paused.
     */
    public void toggleMusic() {
        muteMusic = !muteMusic;
        togglePlayPause(ESounds.MENU_SOUND);
        togglePlayPause(ESounds.GAMEMUSIC_1);
    }
    
    /**
     * Toggle all FX playing/paused.
     */
    public void toggleFX() {
        muteSoundFX = !muteSoundFX;
    }
}
