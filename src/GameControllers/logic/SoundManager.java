package GameControllers.logic;

import App.TanksAppAdapter;
import GameModel.ApplicationSettings;
import GameView.Sounds.ESounds;
import com.jme3.audio.AudioNode;
import java.util.EnumMap;

/**
 *
 * @author Per
 */
public enum SoundManager implements IMapRelatedManager {
    /**
     *
     */
    INSTANCE;
    private EnumMap<ESounds, AudioNode> soundMap;

    /**
     *
     */
    private SoundManager() {
        soundMap = new EnumMap<ESounds, AudioNode>(ESounds.class);
    }

    /**
     *
     * @param map
     */
    @Override
    public void load(int map) {
        if (map == 1) {
            loadSound(new ESounds[]{ESounds.CLICK_SOUND,
                        ESounds.MISSILE_LAUNCH_SOUND, ESounds.MISSILI_COLLISION_SOUND,
                        ESounds.GAMEMUSIC_1});
        }
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
        if (ApplicationSettings.INSTANCE.isMusicMuted()) {
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
            if (toToggle.getStatus() == AudioNode.Status.Paused
                    || toToggle.getStatus() == AudioNode.Status.Stopped) {
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
    @Override
    public void cleanup() {
        removeAllMusic();
        soundMap.clear();
    }

    void stopAllSounds() {
        for (AudioNode audio : soundMap.values()) {
            audio.stop();
        }
    }
}
