
package GameView.GUI;

import GameUtilities.TankAppAdapter;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeEvent;

/**
 * Graphical representation of the in game timer.
 *
 * @author backman
 */
public class TimerView extends AHudElement {
    
    private BitmapText bitmapText;
    
    /**
     * Instatiates a view component of the in game timer.
     */
    public TimerView() {
        picture = new Picture("GameTimer");
        picture.setImage(assetManager, "Interface/TimerBG.png", true);
        float screenWidth = TankAppAdapter.INSTANCE.getScreenWidth();
        float screenHeight = TankAppAdapter.INSTANCE.getScreenHeight();
        picture.setWidth(screenWidth/10);
        picture.setHeight(screenHeight/14);
        float x = (screenWidth/2) - (screenWidth/10) * 0.5f;
        float y = (screenHeight/2) - (screenHeight/14) * 0.5f;
        picture.setPosition(x, y);
        
        // Fixing background in front of text.
        //picture.move(x, y, 1);
        
        BitmapFont font = assetManager.loadFont("Interface/Fonts/HandDrawnShapes.fnt");
        bitmapText = new BitmapText(font, false);
        bitmapText.setSize(font.getCharSet().getRenderedSize());
        bitmapText.setText("99:99");
        x = (screenWidth/2) - font.getCharSet().getWidth();
        y = (screenHeight/2) + font.getCharSet().getHeight();
        bitmapText.setLocalTranslation(x, y, 1);
    }

    /**
     * {@inheritdoc}
     */
    public void propertyChange(PropertyChangeEvent pce) {
        
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void show() {
        TankAppAdapter.INSTANCE.getGUINode().attachChild(picture);
        TankAppAdapter.INSTANCE.getGUINode().attachChild(bitmapText);
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void hide() {
        TankAppAdapter.INSTANCE.getGUINode().detachChild(picture);
        TankAppAdapter.INSTANCE.getGUINode().detachChild(bitmapText);
    }

    /**
     * Format: Pic: ######
     *         Text: ######
     * 
     * @return summary of variables.
     */
    @Override
    public String toString() {
        return super.toString() + "\nText: " + bitmapText.getText();
    }
}
