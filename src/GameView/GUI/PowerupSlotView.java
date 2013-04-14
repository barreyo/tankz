
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
public class PowerupSlotView implements PropertyChangeListener {
    
    private ViewPort vp;
    private IPlayer player;
    private Picture powerupIcon;
    private AssetManager assetManager = TankAppAdapter.INSTANCE.getAssetManager();
    
    /**
     * Instantiate a gui representation of the powerups. 
     * 
     * @param player which player's powerup that should be shown.
     * @param vp the viewport of the player.
     */
    public PowerupSlotView(IPlayer player, ViewPort vp) {
        this.vp = vp;
        this.player = player;
        powerupIcon = new Picture("PowerupSlot");
        powerupIcon.setImage(assetManager, EPowerupIcons.EMPTY.getPath(), true);
        
        // Calculate the height of the picture relative to the ViewPort resolution.
        powerupIcon.setHeight((vp.getCamera().getHeight()/5.2f) / 
                UserSettings.INSTANCE.getPlayers().size() * 
                (UserSettings.INSTANCE.getPlayers().size() > 1 ? 2 : 1));
        // Calculate the height of the picture relative to the ViewPort resolution.
        powerupIcon.setWidth((vp.getCamera().getWidth()/9) / 
                UserSettings.INSTANCE.getPlayers().size() * 
                (UserSettings.INSTANCE.getPlayers().size() > 1 ? 2 : 1));
        // Sets position relative to ViewPort with offset by 20.
        powerupIcon.setPosition(vp.getCamera().getViewPortLeft() * 
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
            powerupIcon.setImage(assetManager, EPowerupIcons.HASTE.getPath(), true);
        } else if (player.getPowerup().equals(EPowerup.HOMING)) {
            powerupIcon.setImage(assetManager, EPowerupIcons.HOMING.getPath(), true);
        } else if (player.getPowerup().equals(EPowerup.BEER)) {
            powerupIcon.setImage(assetManager, EPowerupIcons.BEER.getPath(), true);
        } else if (player.getPowerup().equals(EPowerup.LANDMINE)) {
            powerupIcon.setImage(assetManager, EPowerupIcons.LANDMINE.getPath(), true);
        } else {
            powerupIcon.setImage(assetManager, EPowerupIcons.EMPTY.getPath(), true);
        } 
    }
    
    /**
     * Make the element visible in the GUI.
     */
    public void show() {
        TankAppAdapter.INSTANCE.getGUINode().attachChild(powerupIcon);
    }
    
    /**
     * Remove the element from the GUI.
     */
    public void hide() {
        TankAppAdapter.INSTANCE.getGUINode().detachChild(powerupIcon);
    }
}
