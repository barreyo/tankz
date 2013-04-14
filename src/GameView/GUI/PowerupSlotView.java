
package GameView.GUI;

import GameModel.Game.UserSettings;
import GameModel.Player.IPlayer;
import GameModel.gameEntity.Powerup.EPowerup;
import GameUtilities.TankAppAdapter;
import com.jme3.asset.AssetManager;
import com.jme3.renderer.ViewPort;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The graphical represenation of a Powerup. Each powerup is displayed
 * with a small icon in each viewport, this includes empty powerup.
 * 
 * @author Daniel
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
    public PowerupSlotView(IPlayer player, ViewPort vp) {
        this.vp = vp;
        this.player = player;
        picture = new Picture("PowerupSlot");
        picture.setImage(assetManager, EPowerupIcons.EMPTY.getPath(), true);
        
        // Calculate the height of the picture relative to the ViewPort resolution.
        picture.setHeight((vp.getCamera().getHeight()/5.2f) / 
                UserSettings.INSTANCE.getPlayers().size() * 
                (UserSettings.INSTANCE.getPlayers().size() > 1 ? 2 : 1));
        // Calculate the height of the picture relative to the ViewPort resolution.
        picture.setWidth((vp.getCamera().getWidth()/9) / 
                UserSettings.INSTANCE.getPlayers().size() * 
                (UserSettings.INSTANCE.getPlayers().size() > 1 ? 2 : 1));
        // Sets position relative to ViewPort with offset by 20.
        picture.setPosition(vp.getCamera().getViewPortLeft() * 
                vp.getCamera().getWidth() + 20, vp.getCamera().getViewPortBottom() 
                * vp.getCamera().getHeight() + 20);
        
        player.addObserver(this);
    }

    /**
     * Update the gui element.
     * 
     * @param pce change event.
     */
    public void propertyChange(PropertyChangeEvent pce) {
        if (player.getPowerup().equals(EPowerup.HASTE)) {
            picture.setImage(assetManager, EPowerupIcons.HASTE.getPath(), true);
        } else if (player.getPowerup().equals(EPowerup.HOMING)) {
            picture.setImage(assetManager, EPowerupIcons.HOMING.getPath(), true);
        } else if (player.getPowerup().equals(EPowerup.BEER)) {
            picture.setImage(assetManager, EPowerupIcons.BEER.getPath(), true);
        } else if (player.getPowerup().equals(EPowerup.LANDMINE)) {
            picture.setImage(assetManager, EPowerupIcons.LANDMINE.getPath(), true);
        } else {
            picture.setImage(assetManager, EPowerupIcons.EMPTY.getPath(), true);
        } 
    }
    

}
