
package GameView.GUI;

import App.TanksAppAdapter;
import com.jme3.font.BitmapText;
import com.jme3.renderer.ViewPort;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeEvent;
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
    
    public ScoreboardView(ViewPort vp, int nbrOfPlayers) {
        
        
        float screenWidth = TanksAppAdapter.INSTANCE.getScreenWidth();
        float screenHeight = TanksAppAdapter.INSTANCE.getScreenHeight();
        
        picture = new Picture("ScoreboardBG");
        picture.setImage(assetManager, "Interface/scoreboardBG.png", true);
        picture.setWidth((screenWidth/(nbrOfPlayers > 1 ? 2 : 1)) * 
                (nbrOfPlayers > 1 ? 0.8f : 0.4f));
        picture.setHeight((screenHeight/(nbrOfPlayers > 1 ? 2 : 1)) * 
                (nbrOfPlayers > 1 ? 0.8f : 0.4f));
        
        picture.setPosition(vp.getCamera().getViewPortLeft() 
                + ((screenWidth/(nbrOfPlayers > 1 ? 2 : 1)) * 
                (nbrOfPlayers > 1 ? 0.1f : 0.3f)), vp.getCamera().getViewPortBottom() 
                + ((screenHeight/(nbrOfPlayers > 1 ? 2 : 1)) * 
                (nbrOfPlayers > 1 ? 0.1f : 0.3f)));
        
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
        // HIDE MORE STUFF
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void show() {
        super.show();
        // SHOW MORE STUFF
    }
}
