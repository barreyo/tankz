
package GameView.GUI;

import application.TanksAppAdapter;
import model.ITanks;
import utilities.Commands;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeEvent;

/**
 * Graphical representation of the in game timer.
 *
 * @author Johan Backman
 */
public class TimerView extends AHudElement {
    
    private BitmapText bitmapText;
    private ITanks gameModel;
    
    /**
     * Instatiates a view component of the in game timer. Needs a gameModel to
     * listen to. Updates can be done in any way.
     * 
     * @param gameModel the GameModel this view will listen to.
     */
    public TimerView(ITanks gameModel) {
        this.gameModel = gameModel;
        
        float screenWidth = TanksAppAdapter.INSTANCE.getScreenWidth();
        float screenHeight = TanksAppAdapter.INSTANCE.getScreenHeight();
        
        picture = new Picture("GameTimer");
        picture.setImage(assetManager, "Interface/TimerBG.png", true);
        picture.setWidth(screenWidth/12);
        picture.setHeight(screenHeight/16);
        
        BitmapFont font = assetManager.loadFont(EFonts.HANDDRAWNSHAPES.getPath());
        bitmapText = new BitmapText(font, false);
        bitmapText.setSize(font.getCharSet().getRenderedSize() * ((screenWidth/screenHeight) * 0.65f));
        bitmapText.setText("99:99");
        
        float xText = (screenWidth/2) - (bitmapText.getLineWidth() * 0.5f);
        float yText = (screenHeight/2) + (bitmapText.getLineHeight() * 0.32f);
        float xPic = (screenWidth/2) - (screenWidth/12) * 0.5f;
        float yPic = (screenHeight/2) - (screenHeight/16) * 0.5f;
        if (gameModel.getPlayers().size() == 1) {
            xPic = screenWidth - ((screenWidth/12) * 1.3f);
            yPic = screenHeight - ((screenHeight/16) * 1.5f);
            xText = screenWidth - ((screenWidth/12) * 1.1f);
            yText = screenHeight - ((screenHeight/16) * 0.8f);
        }
        picture.setPosition(xPic, yPic);
        bitmapText.setLocalTranslation(xText, yText, 1);
        
        gameModel.addObserver(this);
    }

    /**
     * {@inheritdoc}
     * @param pce 
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(Commands.TIMER)) {
            String formattedTime;
            long gameTimeLeft = (Long)pce.getNewValue();
            if (gameTimeLeft <= 0) {
                formattedTime = "00:00";
            } else {
                int seconds = (int) (gameTimeLeft % 60);
                int minutes = (int) (gameTimeLeft / 60);

                if (minutes < 10) {
                    formattedTime = "0" + minutes + ":";
                } else {
                    formattedTime = minutes + ":";
                }
                if (seconds < 10) {
                    formattedTime += "0" + seconds;
                } else {
                    formattedTime += "" + seconds;
                }
            }
            bitmapText.setText(formattedTime);
        }
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
