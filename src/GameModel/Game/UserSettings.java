
package GameModel.Game;

import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import GameModel.gameEntity.Vehicle.IArmedVehicle;
import java.util.ArrayList;
import java.util.List;

/**
 * Not in use yet, will be used to handle settings.
 * 
 * @author Daniel
 */
public enum UserSettings {
    INSTANCE;
    
    private final List<IPlayer> players = new ArrayList<IPlayer>();
    
    public List<IPlayer> getPlayers() {
        return players;
    }
    
    public void addPlayer(String name, IArmedVehicle vehicle) {
        Player player = new Player(name, vehicle);
        players.add(player);
    }
    
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
