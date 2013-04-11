/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameControllers.Sounds;

/**
 *
 * @author Daniel
 */
public enum ESounds {
    
        /**
     *
     */
    TEST_MUSIC("click.ogg", true),
        /**
     *
     */
    TEST_SOUND("click.ogg", false);
        

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
