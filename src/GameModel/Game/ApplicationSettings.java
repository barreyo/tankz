
package GameModel.Game;

import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import GameModel.gameEntity.Vehicle.IArmedVehicle;
import java.util.ArrayList;
import java.util.List;

/**
 * Has all the settings of the game, and handles accordingly.
 * 
 * @author Daniel
 */
public enum ApplicationSettings {
    INSTANCE;
    private final List<IPlayer> players = new ArrayList<IPlayer>();
    
    // Sound related
    private boolean muteSoundFX;
    private boolean muteMusic;
    
    /**
     * Returns a list of all the IPlayers that should be in the game.
     * @return a list of all the IPlayers.
     */
    public List<IPlayer> getPlayers() {
        return players;
    }
    
    /**
     * Adds an IPlayer to the game, with a corresponding IVehicle.
     * 
     * @param name name of the player
     * @param vehicle vehicle the player has
     */
    public void addPlayer(String name, IArmedVehicle vehicle) {
        Player player = new Player(name, vehicle);
        players.add(player);
    }

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
