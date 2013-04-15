/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView.GUI;

import GameModel.Game.UserSettings;
import GameModel.Player.IPlayer;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.ViewPort;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeEvent;

/**
 *
 * @author backman
 */
public class HealthView extends AHudElement {

    private Picture mask;
    private BitmapText text;
    private IPlayer player;
    private float elementWidth;
    
    public HealthView(IPlayer player, ViewPort vp) {
        this.player = player;
        
        float screenHeight = vp.getCamera().getHeight();
        float screenWidth = vp.getCamera().getWidth();
        
        picture = new Picture("HealthBG");
        picture.setImage(assetManager, "Interface/healthBG.png", true);
        mask = new Picture("HealthMask");
        mask.setImage(assetManager, "Interface/healthOverlay.png", true);
        
        float elementHeight = (screenHeight/15) / UserSettings.INSTANCE.getPlayers().size() *
                (UserSettings.INSTANCE.getPlayers().size() > 1 ? 2 : 1);
        elementWidth = (screenWidth/3) / UserSettings.INSTANCE.getPlayers().size() *
                (UserSettings.INSTANCE.getPlayers().size() > 1 ? 2 : 1);
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
        text.setColor(new ColorRGBA(53/255,53/255,53/255, 1));
        text.setSize(font.getCharSet().getRenderedSize() * 0.7f);
        text.setLocalTranslation(elementX, elementY - 5.0f, 0);
        
        player.getVehicle().addObserver(this);
    }
    
    /**
     * {@inheritdoc}
     */
    public void propertyChange(PropertyChangeEvent pce) {
        mask.setWidth(elementWidth * (player.getVehicle().getHealth() * 0.01f));
        text.setText("" + player.getVehicle().getHealth());
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
}
