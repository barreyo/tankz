
package GameView.GUI;

import App.TanksAppAdapter;
import GameModel.IPlayer;
import GameModel.Player;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.renderer.ViewPort;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The graphical summary of all the players kills and deaths in the game. All 
 * players can display this summary in each player's viewport.
 * 
 * @author Johan Backman
 */
public class ScoreboardView extends AHudElement {
    
    private List<BitmapText> playerNames, playerKills, playerDeaths, playerStatus;
    private boolean enabled = true;
    private List<IPlayer> players;
    
    public ScoreboardView(ViewPort vp, List<IPlayer> players) {
        this.players = players;
        
        playerNames = new ArrayList<BitmapText>();
        
        float screenWidth = TanksAppAdapter.INSTANCE.getScreenWidth();
        float screenHeight = TanksAppAdapter.INSTANCE.getScreenHeight();
        
        picture = new Picture("ScoreboardBG");
        picture.setImage(assetManager, "Interface/scoreboardBG.png", true);
        float picWidth = (screenWidth/(players.size() > 1 ? 2 : 1)) * 
                (players.size() > 1 ? 0.8f : 0.4f);
        picture.setWidth(picWidth);
        float picHeight = (screenHeight/(players.size() > 1 ? 2 : 1)) * 
                (players.size() > 1 ? 0.8f : 0.4f);
        picture.setHeight(picHeight);
        
        // Calculate x-pos in relation to the screen size and player settings.
        float picX = vp.getCamera().getViewPortLeft() + 
                (screenWidth/(players.size() > 1 ? 2 : 1)) * 
                (players.size() > 1 ? 0.1f : 0.3f);
        // Calculate y-pos in relation to the screen size and player settings.
        float picY = vp.getCamera().getViewPortBottom() 
                + ((screenHeight/(players.size() > 1 ? 2 : 1)) * 
                (players.size() > 1 ? 0.1f : 0.3f));
        // Set position
        picture.setPosition(picX, picY);
        
        BitmapFont font = assetManager.loadFont(EFonts.HELVETICABOLD.getPath());
        
        for (int i = 0; i < players.size(); i++) {
            playerNames.add(new BitmapText(font, false));
            playerNames.get(i).setText(players.get(i).getName());
            playerNames.get(i).setSize(font.getCharSet().getRenderedSize());
            playerNames.get(i).setLocalTranslation(picX + (picWidth * 0.05f), (picY + picHeight) - ((picHeight * 0.1f) * (i+2)) , 1);
        }
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
    public void hide() {
        super.hide();
        for (BitmapText bmt : playerNames) {
            guiNode.detachChild(bmt);
        }
        // HIDE MORE STUFF
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void show() {
        super.show();
        for (BitmapText bmt : playerNames) {
            guiNode.attachChild(bmt);
        }
    }
}
