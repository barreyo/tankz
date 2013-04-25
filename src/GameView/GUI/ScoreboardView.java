
package GameView.GUI;

import App.TanksAppAdapter;
import GameModel.IArmedVehicle;
import GameModel.IArmedVehicle.VehicleState;
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
    private List<IPlayer> players;
    private BitmapText killsText, deathsText;
    
    /**
     * Instatiates a scoreboardview that can be displayed in the given viewport.
     * Displays deaths, kills and vehicle status in a table format.
     * 
     * @param vp ViewPort where the scoreboard should be shown.
     * @param players all the players that should be shown in the scoreboard.
     */
    public ScoreboardView(ViewPort vp, List<IPlayer> players) {
        this.players = players;
        
        playerNames = new ArrayList<BitmapText>();
        playerKills = new ArrayList<BitmapText>();
        playerDeaths = new ArrayList<BitmapText>();
        playerStatus = new ArrayList<BitmapText>();
        
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
        float picX = (vp.getCamera().getViewPortLeft() * screenWidth) + 
                ((screenWidth/players.size()) * (players.size() > 1 ? 0.2f : 0.3f));   
        // Calculate y-pos in relation to the screen size and player settings.
        float picY = (vp.getCamera().getViewPortBottom() * screenHeight) + 
                ((screenHeight/players.size()) * (players.size() > 1 ? 0.2f : 0.3f));
        // Set position
        picture.setPosition(picX, picY);
        
        BitmapFont font = assetManager.loadFont(EFonts.HELVETICABOLD.getPath());
        
        killsText = new BitmapText(font, false);
        deathsText = new BitmapText(font, false);
        killsText.setText("Score");
        deathsText.setText("Death");
        killsText.setLocalTranslation(picX + (picWidth * 0.65f), 
                (picY + picHeight) - ((picHeight * 0.1f)), 1);
        deathsText.setLocalTranslation(picX + (picWidth * 0.85f), 
                (picY + picHeight) - ((picHeight * 0.1f)), 1);
        
        for (int i = 0; i < players.size(); i++) {
            playerNames.add(new BitmapText(font, false));
            playerNames.get(i).setText(players.get(i).getName());
            playerNames.get(i).setSize(font.getCharSet().getRenderedSize());
            playerNames.get(i).setLocalTranslation(picX + (picWidth * 0.05f), 
                    (picY + picHeight) - ((picHeight * 0.1f) * (i+2.4f)) , 1);
            
            playerKills.add(new BitmapText(font, false));
            playerKills.get(i).setText("" + players.get(i).getKills());
            playerKills.get(i).setSize(font.getCharSet().getRenderedSize());
            playerKills.get(i).setLocalTranslation((picX + (picWidth * 0.65f)) + 
                    ((killsText.getLineWidth()/2) - (font.getLineWidth("9")/2)), 
                    (picY + picHeight) - ((picHeight * 0.1f) * (i+2.4f)) , 1);
        
            playerDeaths.add(new BitmapText(font, false));
            playerDeaths.get(i).setText("" + players.get(i).getDeaths());
            playerDeaths.get(i).setSize(font.getCharSet().getRenderedSize());
            playerDeaths.get(i).setLocalTranslation((picX + (picWidth * 0.85f)) + 
                    ((deathsText.getLineWidth()/2) - (font.getLineWidth("9")/2)), 
                    (picY + picHeight) - ((picHeight * 0.1f) * (i+2.4f)) , 1);
            
            playerStatus.add(new BitmapText(font, false));
            playerStatus.get(i).setText("Dead");
            playerStatus.get(i).setSize(font.getCharSet().getRenderedSize());
            playerStatus.get(i).setLocalTranslation((picX + (picWidth * 0.4f)) + 
                    (deathsText.getLineWidth()/2), (picY + picHeight) - 
                    ((picHeight * 0.1f) * (i+2.4f)) , 1);
            
            players.get(i).addObserver(this);
        }
    }
    
    /**
     * {@inheritdoc} 
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals("ScoreUpdate")) {
            updateText();
        }
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
        for (BitmapText bmt : playerDeaths) {
            guiNode.detachChild(bmt);
        }
        for (BitmapText bmt : playerKills) {
            guiNode.detachChild(bmt);
        }
        for (BitmapText bmt : playerStatus) {
            guiNode.detachChild(bmt);
        }
        guiNode.detachChild(killsText);
        guiNode.detachChild(deathsText);
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
        for (BitmapText bmt : playerKills) {
            guiNode.attachChild(bmt);
        }
        for (BitmapText bmt : playerDeaths) {
            guiNode.attachChild(bmt);
        }
        for (BitmapText bmt : playerStatus) {
            guiNode.attachChild(bmt);
        }
        guiNode.attachChild(killsText);
        guiNode.attachChild(deathsText);
    }
    
    private void updateText() {
        for (int i = 0; i < players.size(); i++) {
            
            playerKills.get(i).setText("" + players.get(i).getKills());
            playerDeaths.get(i).setText("" + players.get(i).getDeaths());
            
            if (players.get(i).getVehicle().getVehicleState() == VehicleState.DESTROYED) {
                playerStatus.get(i).setText("Dead");
            } else {
                playerStatus.get(i).setText("");
            }
        }
    }
}
