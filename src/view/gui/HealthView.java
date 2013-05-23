
package view.gui;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.ViewPort;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeEvent;
import model.IArmedVehicle;
import utilities.Commands;

/**
 * The graphical representation of vehicle health.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class HealthView extends AHudElement {

    private Picture mask;
    private BitmapText text;
    private IArmedVehicle vehicle;
    private float elementWidth;
    private ViewPort vp;
    
    /**
     * Instatiates a graphical representation of the vehicle health at the
     * position supplied in the ViewPort.
     * 
     * @param vehicle vehicle health to display.
     * @param vp viewport of the vehicle.
     * @param numberOfPlayers  
     */
    public HealthView(IArmedVehicle vehicle, ViewPort vp, int numberOfPlayers) {
        this.vehicle = vehicle;
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
        
        elementWidth *= 0.97f;
        elementHeight *= 0.75f;
        mask.setHeight(elementHeight);
        mask.setWidth(elementWidth);
        mask.setPosition(elementX + elementWidth * 0.0132f, elementY + (elementHeight * 0.17f));
        
        BitmapFont font = assetManager.loadFont(EFonts.HANDDRAWNSHAPES.getPath());
        text = new BitmapText(font, false);
        text.setColor(ColorRGBA.White);
        text.setSize(font.getCharSet().getRenderedSize() * 0.85f);
        text.setLocalTranslation(elementX, elementY - 5.0f, 0);
        
        vehicle.addObserver(this);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getSource() == vehicle && pce.getPropertyName().equals(Commands.HEALTH)) {
            int health = (Integer)pce.getNewValue();
            mask.setWidth(elementWidth * (health * 0.01f));
            text.setText("" + health);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        super.show();
        guiNode.attachChild(mask);
        guiNode.attachChild(text);
    }
    
    /**
     * {@inheritDoc}
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
        return super.toString() + "\nVP: " + vp.getName() + "\nText: " + text.getText();
    }
}
