package view.sounds;

/**
 * Enum holding the different kind of sounds used in Tanks.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum ESounds {
    
    /**
     * Sound played when an element in the menus is hovered with the mouse.
     */
    CLICK_SOUND("click.ogg", false, 0.5f),
    
    /**
     * The sound played when a shot is launched.
     */
    MISSILE_LAUNCH_SOUND("Explosion.ogg", false, 0.1f),
    
    /**
     * The sound played when a shot hits something in the world.
     */
    MISSILE_COLLISION_SOUND("MissileCollision.ogg", false, 0.2f),
    
    /**
     * The music played in the menu.
     */
    MENU_SOUND("Menu.ogg", true, 0.5f),
    
    /**
     * The music played ingame.
     * 
     * Song number one
     */
    GAMEMUSIC_1("ItsLikeThat.ogg", true, 0.4f);
    
    private final String path;
    private final boolean music;
    private final float volume;

    /**
     * Instantiates the object.
     * 
     * @param filename The name of the file
     * @param isMusic True if it is music, false if it is a simple sound
     * @param volume Level of volume
     */
    ESounds(String filename, boolean isMusic, float volume) {
        path = "Sounds/" + filename;
        music = isMusic;
        this.volume = volume;
    }

    /**
     * Returns the file path
     * 
     * @return The file path
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns true if it is music, false if not.
     * 
     * @return True if it is music
     */
    public boolean isMusic() {
        return music;
    }
    
    /**
     * Returns the level of the volume.
     * 
     * @return The volume level
     */
    public float getVolume() {
        return volume;
    }
}
