package GameControllers.logic;

import GameControllers.Sounds.ESounds;
import GameControllers.logic.IMapRelatedManager;
import GameModel.Game.UserSettings;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioRenderer;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 *
 * @author Per
 */
public enum SoundManager implements IMapRelatedManager {
    INSTANCE;
    
    private EnumMap<ESounds, AudioNode> soundMap;
    private AudioRenderer audioRenderer;
    private AssetManager assetManager;

    /**
     *
     */
    private SoundManager() {
        TanksGame app = TanksGame.getApp();
        audioRenderer = app.getAudioRenderer();
        assetManager = app.getAssetManager();

        soundMap = new EnumMap<ESounds, AudioNode>(ESounds.class);
    }

    /**
     *
     * @param map
     */
    @Override
    public void load(int map) {
        if (map == 1) {
            loadSoundEffects(new ESounds[]{ESounds.TEST_SOUND});
            loadMusic(new ESounds[]{ESounds.TEST_MUSIC});
        }
    }

    // loads all sound effects which will be needed for that map
    private void loadSoundEffects(ESounds[] sounds) {
        for (ESounds s : sounds) {
            AudioNode soundNode = new AudioNode(assetManager, s.path());
            soundMap.put(s, soundNode);
        }
    }

    // load all music which will be streamed
    /**
     *
     * @param music
     */
    public void loadMusic(ESounds[] music) {
        for (ESounds s : music) {
            if (s != null) {
                AudioNode musicNode = new AudioNode(assetManager, s.path(), true);
                musicNode.setLooping(true);
                musicNode.setPositional(false);
                musicNode.setDirectional(false);

                soundMap.put(s, musicNode);
            }
        }
    }

    /**
     *
     * @param music
     */
    public void removeMusic(ESounds[] music) {
        for (ESounds s : music) {
            soundMap.remove(s);
        }
    }

    /**
     *
     */
    public void removeAllMusic() {
        ArrayList<ESounds> musicList = new ArrayList<ESounds>();

        for (ESounds s : soundMap.keySet()) {
            if (soundMap.get(s).isLooping()) {
                musicList.add(s);
            }
        }

        for (ESounds tanksSound : musicList) {
            soundMap.get(tanksSound).stop();
            soundMap.remove(tanksSound);
        }
    }

    /**
     *
     * @param sound
     */
    public void play(ESounds sound) {
        AudioNode audio = soundMap.get(sound);

        if (audio != null) {
            
            if (sound.isMusic()) {
                if (UserSettings.INSTANCE.isMusicMuted()) {
                    return;
                }
                audio.play();
            } else {
                if (UserSettings.INSTANCE.isSoundFXMuted()) {
                    return;
                }
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
            audioRenderer.pauseSource(audio);
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
    void stop(ESounds sound) {
        AudioNode toStop = soundMap.get(sound);
        toStop.stop();
    }

    /**
     *
     */
    @Override
    public void cleanup() {
        removeAllMusic();
        soundMap.clear();
    }
}
