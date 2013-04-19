package GameView.Sounds;

/**
 *
 * @author Daniel
 */
public enum ESounds {
    
    /**
     *
     */
    CLICK_SOUND("click.ogg", false, 0.5f),
    MISSILE_LAUNCH_SOUND("Explosion.ogg", false, 0.1f),
    MISSILI_COLLISION_SOUND("MissileCollision.ogg", false, 0.2f),
    MENU_SOUND("Menu.ogg", true, 0.5f),
    GAMEMUSIC_1("ItsLikeThat.ogg", true, 0.4f);
    
    private final String path;
    private final boolean music;
    private final float volume;

    ESounds(String filename, boolean isMusic, float volume) {
        path = "Sounds/" + filename;
        music = isMusic;
        this.volume = volume;
    }

    /**
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @return
     */
    public boolean isMusic() {
        return music;
    }
    
    public float getVolume() {
        return volume;
    }
}
