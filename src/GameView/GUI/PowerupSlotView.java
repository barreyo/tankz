
package GameView.GUI;

import GameModel.ApplicationSettings;
import GameModel.IPlayer;
import GameModel.HastePowerup;
import GameModel.IPowerup;
import GameModel.MissilePowerup;
import com.jme3.renderer.ViewPort;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeEvent;

/**
 * The graphical represenation of a Powerup. Each powerup is displayed
 * with a small icon in each viewport, this includes empty powerup.
 * 
 * @author Johan Backman
 */
public class PowerupSlotView extends AHudElement {
    
    private ViewPort vp;
    private IPlayer player;
    
    /**
     * Instantiate a gui representation of the powerups. 
     * 
     * @param player which player's powerup that should be shown.
     * @param vp the viewport of the player.
     */
    public PowerupSlotView(IPlayer player, ViewPort vp, int numberOfPlayers) {
        this.vp = vp;
        this.player = player;
        picture = new Picture("PowerupSlot");
        picture.setImage(assetManager, EPowerupIcons.EMPTY.getPath(), true);
        
        float screenHeight = vp.getCamera().getHeight();
        float screenWidth = vp.getCamera().getWidth();
        
        // Calculate the height of the picture relative to the ViewPort resolution.
        picture.setHeight((screenHeight/5.3f) / 
                numberOfPlayers * 
                (numberOfPlayers > 1 ? 2 : 1));
        // Calculate the height of the picture relative to the ViewPort resolution.
        picture.setWidth((screenWidth/9) / 
                numberOfPlayers * 
                (numberOfPlayers > 1 ? 2 : 1));
        // Sets position relative to ViewPort with offset by width * 0.1.
        picture.setPosition(vp.getCamera().getViewPortLeft() * 
                screenWidth + (screenWidth * 0.02f), vp.getCamera().getViewPortBottom() 
                * screenHeight + (screenWidth * 0.02f));
        
        player.addObserver(this);
    }

    /**
     * Update the gui element.
     * 
     * @param pce change event.
     */
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(IPlayer.POWERUP_CHANGED)) {
            IPowerup powerup = player.getPowerup();
            if (powerup instanceof HastePowerup) {
                picture.setImage(assetManager, EPowerupIcons.HASTE.getPath(), true);
            } else if (powerup instanceof MissilePowerup) {
                picture.setImage(assetManager, EPowerupIcons.HOMING.getPath(), true);
            } else if (powerup == null) {
                picture.setImage(assetManager, EPowerupIcons.EMPTY.getPath(), true);
            }
        }
            /*else if (player.getPowerup().equals(EPowerup.HOMING)) {
            picture.setImage(assetManager, EPowerupIcons.HOMING.getPath(), true);
        } else if (player.getPowerup().equals(EPowerup.BEER)) {
            picture.setImage(assetManager, EPowerupIcons.BEER.getPath(), true);
        } else if (player.getPowerup().equals(EPowerup.LANDMINE)) {
            picture.setImage(assetManager, EPowerupIcons.LANDMINE.getPath(), true);
        } else {
            picture.setImage(assetManager, EPowerupIcons.EMPTY.getPath(), true);
        } */
    }

    /**
     * Format: Pic: ######
     *         Player: ######
     *         VP: ######
     * 
     * @return summary of variables.
     */
    @Override
    public String toString() {
        return super.toString() + "\nPlayer: " + player.getName() + 
                "\nVP: " + vp.getName();
    }
}
