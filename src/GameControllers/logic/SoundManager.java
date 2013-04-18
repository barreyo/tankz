package GameControllers.logic;

import GameView.Sounds.ESounds;
import GameModel.Game.ApplicationSettings;
import App.TanksAppAdapter;
import com.jme3.audio.AudioNode;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 *
 * @author Per
 */
public enum SoundManager implements IMapRelatedManager{
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
            loadSoundEffects(new ESounds[]{ESounds.CLICK_SOUND,
                ESounds.MISSILE_LAUNCH_SOUND, ESounds.MISSILI_COLLISION_SOUND,
             ESounds.GAMEMUSIC_1});
            /*
              In-game sounds needs to be effects for now. Otherwise it tries to instansiate
              it several times, which you cant with a streamed audio.
              loadMusic(new ESounds[]{ESounds.GAMEMUSIC_1});
             */
        }
    }
    
    public void preLoad(){
        loadMusic(new ESounds[]{ESounds.MENU_SOUND});
    }

    // loads all sound effects which will be needed for that map
    private void loadSoundEffects(ESounds[] sounds) {
        for (ESounds s : sounds) {
            AudioNode soundNode = new AudioNode(TanksAppAdapter.INSTANCE.getAssetManager(), s.path());
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
                AudioNode musicNode = new AudioNode(TanksAppAdapter.INSTANCE.getAssetManager(), s.path(), true);
                musicNode.setPositional(false);
                musicNode.setDirectional(false);
                musicNode.setVolume(0.5f);

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
                if (ApplicationSettings.INSTANCE.isMusicMuted()) {
                    return;
                }
                audio.play();
            } else {
                if (ApplicationSettings.INSTANCE.isSoundFXMuted()) {
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
