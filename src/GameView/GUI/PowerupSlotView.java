
package GameView.GUI;

import GameModel.Player.IPlayer;
import GameModel.Player.Player;
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
    
    public PowerupSlotView(IPlayer player, ViewPort vp) {
        this.vp = vp;
        this.player = player;
        powerupIcon = new Picture("PowerupSlot");
        powerupIcon.setImage(assetManager, "Interface/PowerupIcons/Empty.png", true);
        powerupIcon.setHeight(vp.getCamera().getHeight()/6);
        powerupIcon.setWidth(vp.getCamera().getWidth()/9);
        powerupIcon.setPosition(vp.getCamera().getWidth() * 0.03f, vp.getCamera().getHeight() * 0.05f);
        System.out.println(powerupIcon.toString());
    }
  
    
    public void propertyChange(PropertyChangeEvent pce) {
    /*    if (pce.getSource() instanceof IPlayer) {
            IPlayer tempPlayer = (IPlayer) pce.getSource();
            if (tempPlayer.getPowerup().equals(EPowerup.HASTE)) {
                powerupIcon.setImage(assetManager, EPowerupIcons.HASTE.getPath(), true);
            } else if (tempPlayer.getPowerup().equals(EPowerup.HOMING)) {
                powerupIcon.setImage(assetManager, EPowerupIcons.HOMING.getPath(), true);
            } else if (tempPlayer.getPowerup().equals(EPowerup.BEER)) {
                powerupIcon.setImage(assetManager, EPowerupIcons.BEER.getPath(), true);
            } else if (tempPlayer.getPowerup().equals(EPowerup.LANDMINE)) {
                powerupIcon.setImage(assetManager, EPowerupIcons.LANDMINE.getPath(), true);
            } else {
                powerupIcon.setImage(assetManager, EPowerupIcons.EMPTY.getPath(), true);
            }
        } */
    }
    
    public void show() {
        TankAppAdapter.INSTANCE.getGUINode().attachChild(powerupIcon);
    }
    
    public void hide() {
        TankAppAdapter.INSTANCE.getGUINode().detachChild(powerupIcon);
    }
}
