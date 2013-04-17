
package GameView.GUI;

import GameModel.Game.TanksGameModel;
import App.TanksAppAdapter;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeEvent;

/**
 * Graphical representation of the in game timer.
 *
 * @author Johan Backman
 */
public class TimerView extends AHudElement {
    
    private BitmapText bitmapText;
    private TanksGameModel gameModel;
    
    /**
     * Instatiates a view component of the in game timer. Needs a gameModel to
     * listen to. Updates can be done in any way.
     * 
     * @param gameModel the GameModel this view will listen to.
     */
    public TimerView(TanksGameModel gameModel) {
        this.gameModel = gameModel;
        
        float screenWidth = TanksAppAdapter.INSTANCE.getScreenWidth();
        float screenHeight = TanksAppAdapter.INSTANCE.getScreenHeight();
        
        picture = new Picture("GameTimer");
        picture.setImage(assetManager, "Interface/TimerBG.png", true);
        picture.setWidth(screenWidth/10);
        picture.setHeight(screenHeight/14);
        
        BitmapFont font = assetManager.loadFont(EFonts.HANDDRAWNSHAPES.getPath());
        bitmapText = new BitmapText(font, false);
        bitmapText.setSize(font.getCharSet().getRenderedSize() * ((screenHeight/screenWidth) + 0.4f));
        bitmapText.setText("99:99");
        
        float xText = (screenWidth/2) - (bitmapText.getLineWidth() * 0.5f);
        float yText = (screenHeight/2) + (bitmapText.getLineHeight() * 0.32f);
        float xPic = (screenWidth/2) - (screenWidth/10) * 0.5f;
        float yPic = (screenHeight/2) - (screenHeight/14) * 0.5f;
        if (gameModel.getPlayers().size() == 1) {
            xPic = screenWidth - ((screenWidth/10) * 1.3f);
            yPic = screenHeight - ((screenHeight/14) * 1.5f);
            xText = screenWidth - (bitmapText.getLineWidth() * 1.58f);
            yText = screenHeight - (bitmapText.getLineHeight() * 0.8f);
        }
        picture.setPosition(xPic, yPic);
        bitmapText.setLocalTranslation(xText, yText, 1);
    }

    /**
     * {@inheritdoc}
     */
    public void propertyChange(PropertyChangeEvent pce) {
        bitmapText.setText("13:37");
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void show() {
        super.show();
        guiNode.attachChild(bitmapText);
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void hide() {
        super.hide();
        guiNode.detachChild(bitmapText);
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
