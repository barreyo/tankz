
package GameView.GUI;

import GameModel.Game.TanksGameModel;
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
    private TanksGameModel gameModel;
    
    /**
     * Instatiates a view component of the in game timer.
     */
    public TimerView(TanksGameModel gameModel) {
        this.gameModel = gameModel;
        
        float screenWidth = TankAppAdapter.INSTANCE.getScreenWidth();
        float screenHeight = TankAppAdapter.INSTANCE.getScreenHeight();
        
        picture = new Picture("GameTimer");
        picture.setImage(assetManager, "Interface/TimerBG.png", true);
        picture.setWidth(screenWidth/10);
        picture.setHeight(screenHeight/14);
        
        BitmapFont font = assetManager.loadFont("Interface/Fonts/HandDrawnShapes.fnt");
        bitmapText = new BitmapText(font, false);
        bitmapText.setSize(font.getCharSet().getRenderedSize() * ((screenHeight/screenWidth) + 0.4f));
        bitmapText.setText("99:99");
        
        float xText = (screenWidth/2) - (bitmapText.getLineWidth() * 0.5f);
        float yText = (screenHeight/2) + (bitmapText.getLineHeight() * 0.35f);
        float xPic = (screenWidth/2) - (screenWidth/10) * 0.5f;
        float yPic = (screenHeight/2) - (screenHeight/14) * 0.5f;
        if (gameModel.getPlayers().size() == 1) {
            yPic = screenHeight - (screenHeight/14);
            yText = screenHeight - (bitmapText.getLineHeight() * 0.35f);
        }
        picture.setPosition(xPic, yPic);
        bitmapText.setLocalTranslation(xText, yText, 1);
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
