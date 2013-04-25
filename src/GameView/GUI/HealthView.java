
package GameView.GUI;

import GameModel.ApplicationSettings;
import GameModel.IPlayer;
import GameModel.IArmedVehicle;
import GameUtilities.Commands;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.ViewPort;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeEvent;

/**
 * The graphical representation of player health.
 * 
 * @author Johan Backman
 */
public class HealthView extends AHudElement {

    private Picture mask;
    private BitmapText text;
    private IPlayer player;
    private float elementWidth;
    private ViewPort vp;
    
    /**
     * Instatiates a graphical representation of the player health at the
     * position supplied in the ViewPort.
     * 
     * @param player player health to display.
     * @param vp viewport of the player.
     */
    public HealthView(IPlayer player, ViewPort vp, int numberOfPlayers) {
        this.player = player;
        this.vp = vp;
        
        float screenHeight = vp.getCamera().getHeight();
        float screenWidth = vp.getCamera().getWidth();
        
        picture = new Picture("HealthBG");
        picture.setImage(assetManager, "Interface/healthBG.png", true);
        mask = new Picture("HealthMask");
        mask.setImage(assetManager, "Interface/healthOverlay.png", true);
        
        float elementHeight = (screenHeight/15) / numberOfPlayers * (numberOfPlayers > 1 ? 2 : 1);
        elementWidth = (screenWidth/3) / numberOfPlayers * (numberOfPlayers > 1 ? 2 : 1);
        float elementX = vp.getCamera().getViewPortLeft() * screenWidth + (screenWidth * 0.02f);
        float elementY = vp.getCamera().getViewPortTop() * screenHeight - (1.8f * elementHeight);
        
        picture.setHeight(elementHeight);
        picture.setWidth(elementWidth);
        picture.setPosition(elementX, elementY);
        
        mask.setHeight(elementHeight);
        mask.setWidth(elementWidth);
        mask.setPosition(elementX, elementY);
        
        BitmapFont font = assetManager.loadFont(EFonts.HANDDRAWNSHAPES.getPath());
        text = new BitmapText(font, false);
        text.setText("" + player.getVehicle().getHealth());
        text.setColor(ColorRGBA.White);
        text.setSize(font.getCharSet().getRenderedSize() * 1.1f);
        text.setLocalTranslation(elementX, elementY - 5.0f, 0);
        
        player.getVehicle().addObserver(this);
    }
    
    /**
     * {@inheritdoc}
     */
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(Commands.HEALTH)) {
            mask.setWidth(elementWidth * (player.getVehicle().getHealth() * 0.01f));
            text.setText("" + player.getVehicle().getHealth());
        }
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void show() {
        super.show();
        guiNode.attachChild(mask);
        guiNode.attachChild(text);
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void hide() {
        super.hide();
        guiNode.detachChild(mask);
        guiNode.detachChild(text);
    }
    
    /**
     * Format: Pic: ######
     *         Player: ######
     *         VP: ######
     *         Text: ######
     * 
     * @return summary of variables.
     */
    @Override
    public String toString() {
        return super.toString() + "\nPlayer: " + player.getName() + 
                "\nVP: " + vp.getName() + "\nText: " + text.getText();
    }
}
