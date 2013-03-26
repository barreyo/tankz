/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Sounds;

import GameLogicLayer.Game.TanksGame;
import GameLogicLayer.util.Manager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioRenderer;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 *
 * @author Per
 */
public class SoundManager implements Manager {
    
    private EnumMap<ETanksSound, AudioNode> soundMap;
    private TanksGame app;
    private AudioRenderer audioRenderer;
    private AssetManager assetManager;

    public SoundManager() {
        app = TanksGame.getApp();
        audioRenderer = app.getAudioRenderer();
        assetManager = app.getAssetManager();

        soundMap = new EnumMap<ETanksSound, AudioNode>(ETanksSound.class);
    }

    @Override
    public void load(int map) {
        if (map == 1) {
            loadSoundEffects(new ETanksSound[]{ETanksSound.TEST_SOUND});
            loadMusic(new ETanksSound[]{ETanksSound.TEST_MUSIC});
        }
    }

    // loads all sound effects which will be needed for that map
    private void loadSoundEffects(ETanksSound[] sounds) {
        for (ETanksSound s : sounds) {
            AudioNode soundNode = new AudioNode(assetManager, s.path());
            soundMap.put(s, soundNode);
        }
    }

    // load all music which will be streamed
    public void loadMusic(ETanksSound[] music) {
        for (ETanksSound s : music) {
            if (s != null) {
                AudioNode musicNode = new AudioNode(assetManager, s.path(), true);
                musicNode.setLooping(true);
                musicNode.setPositional(false);
                musicNode.setDirectional(false);

                soundMap.put(s, musicNode);
            }
        }
    }

    public void removeMusic(ETanksSound[] music) {
        for (ETanksSound s : music) {
            soundMap.remove(s);
        }
    }

    public void removeAllMusic() {
        ArrayList<ETanksSound> musicList = new ArrayList<ETanksSound>();

        for (ETanksSound s : soundMap.keySet()) {
            if (soundMap.get(s).isLooping()) {
                musicList.add(s);
            }
        }

        for (ETanksSound tanksSound : musicList) {
            soundMap.get(tanksSound).stop();
            soundMap.remove(tanksSound);
        }
    }

    public void play(ETanksSound sound) {
        AudioNode audio = soundMap.get(sound);

        if (audio != null) {
            
            if (sound.isMusic()) {
                if (app.getUserSettings().isMusicMuted()) {
                    return;
                }
                audio.play();
            } else {
                if (app.getUserSettings().isSoundFXMuted()) {
                    return;
                }
                audio.playInstance();
            }
        }
    }

    // pause the music
    public void pause(ETanksSound sound) {
        AudioNode audio = soundMap.get(sound);

        if (audio != null) {
            audioRenderer.pauseSource(audio);
        }
    }

    // if paused it will play, if playing it will be paused
    public void togglePlayPause(ETanksSound sound) {
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
    void stop(ETanksSound sound) {
        AudioNode toStop = soundMap.get(sound);
        toStop.stop();
    }

    @Override
    public void cleanup() {
        removeAllMusic();
        soundMap.clear();
    }
}
