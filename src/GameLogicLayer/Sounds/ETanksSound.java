/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Sounds;

/**
 *
 * @author Daniel
 */
public enum ETanksSound {
    
        TEST_MUSIC("click.ogg", true),
        TEST_SOUND("click.ogg", false);
        

        private final String path;
        private final boolean music;

        ETanksSound(String filename, boolean isMusic) {
            path = "Sounds/" + filename;
            music = isMusic;
        }

        public String path() {
            return path;
        }

        public boolean isMusic() {
            return music;
        }
}
