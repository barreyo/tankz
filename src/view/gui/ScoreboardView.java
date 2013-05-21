
package view.gui;

import application.TanksAppAdapter;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.renderer.ViewPort;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.IArmedVehicle;
import model.IArmedVehicle.VehicleState;
import model.IPlayer;
import model.PlayerComparator;
import utilities.Commands;

/**
 * The graphical summary of all the players kills and deaths in the game. All 
 * players can display this summary in each player's viewport.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class ScoreboardView extends AHudElement {
    
    private List<BitmapText> playerNames, playerKills, playerDeaths, playerStatus;
    private List<IPlayer> players;
    private BitmapText killsText, deathsText, respawnTimerText;
    private IPlayer player;
    private PlayerComparator pCompare;
    
    /**
     * Instatiates a scoreboardview that can be displayed in the given viewport.
     * Displays deaths, kills and vehicle status in a table format.
     * 
     * @param vp ViewPort where the scoreboard should be shown.
     * @param players all the players that should be shown in the scoreboard.
     * @param player the player that the scoreboard is connected to.
     */
    public ScoreboardView(ViewPort vp, List<IPlayer> players, IPlayer player) {
        this.players = players;
        this.player = player;
        
        playerNames = new ArrayList<BitmapText>();
        playerKills = new ArrayList<BitmapText>();
        playerDeaths = new ArrayList<BitmapText>();
        playerStatus = new ArrayList<BitmapText>();
        
        pCompare = new PlayerComparator();
        
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
        respawnTimerText = new BitmapText(font, false);
        killsText.setText("Score");
        deathsText.setText("Death");
        respawnTimerText.setText("");
        killsText.setLocalTranslation(picX + (picWidth * 0.65f), 
                (picY + picHeight) - ((picHeight * 0.13f)), 1);
        deathsText.setLocalTranslation(picX + (picWidth * 0.85f), 
                (picY + picHeight) - ((picHeight * 0.13f)), 1);
        respawnTimerText.setLocalTranslation(picX + (picWidth * 0.05f), 
                (picY + picHeight) - ((picHeight * 0.1f) * (7.4f)), 1);
        
        int i = 0;
        int size = font.getCharSet().getRenderedSize();
        for (IPlayer p : players) {
            BitmapText name = new BitmapText(font, false);
            name.setText(p.getName());  
            name.setSize(size);
            name.setLocalTranslation(picX + (picWidth * 0.05f), 
                    (picY + picHeight) - ((picHeight * 0.1f) * (i+2.4f)) , 1);
            playerNames.add(name);
            
            BitmapText kills = new BitmapText(font, false);
            kills.setSize(size);
            kills.setLocalTranslation((picX + (picWidth * 0.65f)) + 
                    ((killsText.getLineWidth()/2) - (font.getLineWidth("9")/2)), 
                    (picY + picHeight) - ((picHeight * 0.1f) * (i+2.4f)) , 1);
            playerKills.add(kills);
        
            BitmapText deaths = new BitmapText(font, false);
            deaths.setSize(size);
            deaths.setLocalTranslation((picX + (picWidth * 0.85f)) + 
                    ((deathsText.getLineWidth()/2) - (font.getLineWidth("9")/2)), 
                    (picY + picHeight) - ((picHeight * 0.1f) * (i+2.4f)) , 1);
            playerDeaths.add(deaths);
            
            BitmapText status = new BitmapText(font, false);
            status.setSize(size);
            status.setLocalTranslation((picX + (picWidth * 0.4f)) + 
                    (deathsText.getLineWidth()/2), (picY + picHeight) - 
                    ((picHeight * 0.1f) * (i+2.4f)) , 1);
            playerStatus.add(status);
            
            p.addObserver(this);
            p.getVehicle().addObserver(this);
            i++;
        }
        updateText();
    }
    
    /**
     * {@inheritdoc} 
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        String propertyName = pce.getPropertyName();
        Object source = pce.getSource();
        if ((source instanceof IPlayer || source instanceof IArmedVehicle) 
                && (propertyName.equals(Commands.SCORE_UPDATE) 
                || propertyName.equals(Commands.SHOW))) {
            updateText();
        } else if (source == player) {
            if (propertyName.equals(Commands.SHOW_SCOREBOARD)) {
                show();
            } else if (propertyName.equals(Commands.HIDE_SCOREBOARD)) {
                hide();
            } else if (propertyName.equals(Commands.SCORE_RESPAWN_UPDATE)) {
                respawnTimerText.setText("Respawn in: " + player.getDeathTime());
                if (player.getDeathTime() <= 0) {
                    respawnTimerText.setText("");
                }
            }
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
        guiNode.detachChild(respawnTimerText);
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
        guiNode.attachChild(respawnTimerText);
        guiNode.attachChild(killsText);
        guiNode.attachChild(deathsText);
    }
    
    // Updates text values and positions in the table.
    private void updateText() {
        List<IPlayer> playersClone = new ArrayList<IPlayer>();
        
        // Cloning players list just in case since we mess with the order.
        for(IPlayer p : players) {
            playersClone.add(p);
        }
        
        // sort after kills and deaths
        Collections.sort(playersClone, pCompare);
        
        for (int i = 0; i < playersClone.size(); i++) {
            playerNames.get(i).setText(playersClone.get(i).getName());
            playerKills.get(i).setText("" + playersClone.get(i).getKills());
            playerDeaths.get(i).setText("" + playersClone.get(i).getDeaths());
            
            if (playersClone.get(i).getVehicle().getVehicleState() == VehicleState.DESTROYED) {
                playerStatus.get(i).setText("Dead");
            } else {
                playerStatus.get(i).setText("");
            }
        }
    }
}
