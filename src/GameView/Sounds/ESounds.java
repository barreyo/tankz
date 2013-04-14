/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView.Sounds;

/**
 *
 * @author Daniel
 */
public enum ESounds {
    
        /**
     *
     */
    CLICK_SOUND("click.ogg", false),
    
    MISSILE_LAUNCH_SOUND("Explosion.ogg", false),
    
    MISSILI_COLLISION_SOUND("MissileCollision.ogg", false),
    
    MENU_SOUND("Menu.ogg", true),
    
    GAMEMUSIC_1("ItsLikeThat.ogg", false);
        
        private final String path;
        private final boolean music;

        ESounds(String filename, boolean isMusic) {
            path = "Sounds/" + filename;
            music = isMusic;
        }

        /**
     *
     * @return
     */
    public String path() {
            return path;
        }

        /**
     *
     * @return
     */
    public boolean isMusic() {
            return music;
        }
}
