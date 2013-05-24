
package controller.managers;

import application.TanksAppAdapter;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMouseMovedEvent;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.spi.sound.SoundHandle;
import de.lessvoid.nifty.tools.Color;
import java.util.ArrayList;
import java.util.List;
import model.EApplicationState;
import view.sounds.ESounds;

/**
 * An app state representing the main menu.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class MenuAppState extends AbstractAppState implements ScreenController {
    
    private static MenuAppState instance;
    
    private Nifty nifty;
    private Element currentElement, musicToggle, fxToggle, killsToWinElement, 
            gameTimeElement, powerupRespawnElement, fullscreenToggle;
    private SoundHandle sound;
    
    private int killsToWin, gameTimeMinutes, powerupRespawnSeconds, playerCount;
    private boolean fullscreen;
    
    private final List<String> playerNames = new ArrayList<String>();

    /**
     *  Create a new main menu app state.
     */
    private MenuAppState() {
        nifty = GUIManager.INSTANCE.getNifty();
        nifty.fromXml("Interface/Nifty/MainMenu.xml", "start", this);
        
        nifty.getSoundSystem().addSound("hooverSound", "Sounds/click.ogg");

        sound = nifty.getSoundSystem().getSound("hooverSound");
        sound.setVolume(1.0f);
        
        fullscreen = TanksAppAdapter.INSTANCE.isFullscreen();
        
        killsToWin = 0;
        gameTimeMinutes = 5;
        powerupRespawnSeconds = 10;
    }
    
    /**
     * Get the instance to the current singleton.
     * 
     * @return instance.
     */
    public static synchronized MenuAppState getInstance() {
        if (instance == null) {
            instance = new MenuAppState();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        SoundManager.INSTANCE.loadSound();
        TanksAppAdapter.INSTANCE.setCursorVisible(true);
        goToMainMenu();
        EApplicationState.setGameState(EApplicationState.MAIN_MENU);
        playerNames.clear();
        TanksAppAdapter.INSTANCE.attachAppState(GlobalInputAppState.getInstance());
        if (!SoundManager.INSTANCE.isMusicMuted()) {
            SoundManager.INSTANCE.play(ESounds.MENU_SOUND);
        }
        musicToggle = nifty.getScreen("settings").findElementByName("main_music_toggle");
        fxToggle = nifty.getScreen("settings").findElementByName("main_fx_toggle");
        killsToWinElement = nifty.getScreen("gameSettings").findElementByName("main_killsToWin");
        gameTimeElement = nifty.getScreen("gameSettings").findElementByName("main_time");
        powerupRespawnElement = nifty.getScreen("gameSettings").findElementByName("main_powerupRespawn");
        fullscreenToggle = nifty.getScreen("settings").findElementByName("main_fullscreen_toggle");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen");
    }

    /**
     * Go to the main menu.
     */
    public void goToMainMenu() {
        nifty.gotoScreen("start");
    }
    
    /**
     * Go to the settings menu.
     */
    public void goToSettingsScreen() {
        nifty.gotoScreen("settings");
        updateSettingsOptions();
    }
    
    /**
     * Go to the player choosing menu.
     */
    public void goToMultiplayerScreen() {
       nifty.gotoScreen("multi");
    }

    /**
     * Go to the game settings menu.
     */
    public void goToGameSettingsMenu() {
        nifty.gotoScreen("gameSettings");
        gameTimeElement.getRenderer(TextRenderer.class).setText("GAMETIME: " + gameTimeMinutes + " MIN");
        setKillsText();
        powerupRespawnElement.getRenderer(TextRenderer.class).setText("POWERUP RESPAWN TIME: " + powerupRespawnSeconds + " SEC");
    }
    
    /**
     * Toggle music on/off.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void toggleMusic() {
        SoundManager.INSTANCE.toggleMusic();
        updateSettingsOptions();
    }
    
    /**
     * Toggle FX on/off.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void toggleFX() {
        SoundManager.INSTANCE.toggleFX();
        updateSettingsOptions();
    }
    
    /**
     * Toggle fullscreen on/off.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void fullscreenToggle() {
        TanksAppAdapter.INSTANCE.toggleFullscreen(fullscreen);
        updateSettingsOptions();
    }
    
    /**
     * Hover effects for menu items.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     * 
     * @param id nifty element id.
     * @param event nifty mouse event.
     */
    @NiftyEventSubscriber(pattern = "main.*")
    public void onHover(String id, NiftyMouseMovedEvent event) {

        if (currentElement == null) { //initial element
            if (event.getElement().getRenderer(TextRenderer.class) != null) {
                currentElement = event.getElement();

                // hover
                TextRenderer renderer1 = currentElement.getRenderer(TextRenderer.class);
                renderer1.setColor(Color.BLACK);

                if (!SoundManager.INSTANCE.isSoundFXMuted()) {
                    sound.play();
                }
            }
        } else {
            if (event.getElement() != currentElement) {
                currentElement.getRenderer(TextRenderer.class).setColor(Color.WHITE);
                currentElement = null;
            }
        }
    }

    /**
     * Bind the screen to Nifty.
     * 
     * @param nifty nifty instance.
     * @param screen screen to bind.
     */
    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("bind( " + screen.getScreenId() + ")");
    }

    /**
     * Exit the game.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void exit() {
        TanksAppAdapter.INSTANCE.stop();
    }
    
    /**
     * Update the visual representation in the settings menu.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void updateSettingsOptions() {
        if (SoundManager.INSTANCE.isMusicMuted()) {
            musicToggle.getRenderer(TextRenderer.class).setText("MUSIC OFF");
        } else {
            musicToggle.getRenderer(TextRenderer.class).setText("MUSIC ON");
        }
        if (SoundManager.INSTANCE.isSoundFXMuted()) {
            fxToggle.getRenderer(TextRenderer.class).setText("SOUND EFFECTS OFF");
        } else {
            fxToggle.getRenderer(TextRenderer.class).setText("SOUND EFFECTS ON");
        }
        if (fullscreen) {
            fullscreenToggle.getRenderer(TextRenderer.class).setText("FULLSCREEN ON");
        } else {
            fullscreenToggle.getRenderer(TextRenderer.class).setText("FULLSCREEN OFF");
        }
    }
    
    /**
     * Update the visual representation in the game settings menu.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void incrementGameTime() {
        gameTimeMinutes += 5;
        if (gameTimeMinutes % 25 == 0) {
            gameTimeMinutes = 5;
        }
        gameTimeElement.getRenderer(TextRenderer.class).setText("GAMETIME: " + gameTimeMinutes + " MIN");
    }
    
    /**
     * Update the visual representation in the game settings menu.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void incrementKillsToWin() {
        killsToWin += 5;
        if (killsToWin % 25 == 0) {
            killsToWin = 0;
        }
        setKillsText();
    }
    
    private void setKillsText() {
        if (killsToWin == 0) {
            killsToWinElement.getRenderer(TextRenderer.class).setText("KILLS TO WIN: INFINITY");
        } else {
            killsToWinElement.getRenderer(TextRenderer.class).setText("KILLS TO WIN: " + killsToWin + " MIN");
        }
    }
    
    /**
     * Update the visual representation in the game settings menu.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void incrementPowerupSpawnTime() {
        powerupRespawnSeconds += 10;
        if (powerupRespawnSeconds % 70 == 0) {
            powerupRespawnSeconds = 10;
        }
        powerupRespawnElement.getRenderer(TextRenderer.class).setText("POWERUP RESPAWN TIME: " + powerupRespawnSeconds + " SEC");
    }
    
    /**
     * Load the game with one player.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void loadOnePlayerGame() {
        playerCount = 1;
        goToGameSettingsMenu();
    }
    
    /**
     * Load the game with two players.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void loadTwoPlayerGame() {
        playerCount = 2;
        goToGameSettingsMenu();
    }
    
    /**
     * Load the game with three players.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void loadThreePlayerGame() {
        playerCount = 3;
        goToGameSettingsMenu();
    }
    
    /**
     * Load the game with four players.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void loadFourPlayerGame() {
        playerCount = 4;
        goToGameSettingsMenu();
    }
    
    /**
     * Start the game with menu settings.
     * 
     * NOTE: Used by the Nifty screen. Shouldn't be used anywhere else.
     */
    public void startGame() {
        for (int i = 1; i <= playerCount; i++) {
            playerNames.add("Player " + i);
        }
        GUIManager.INSTANCE.showLoadingScreen();
        TanksAppAdapter.INSTANCE.detachAppState(this);
    }
    
    /**
     * Get the names of the players in the game.
     * 
     * @return list of player names.
     */
    public ArrayList<String> getPlayerNames() {
        return new ArrayList<String>(playerNames);
    }
    
    /**
     * Get the game time from the menu, inputted by a player.
     * 
     * @return game time in millisec.
     */
    public int getGameTimeInMS() {
        return gameTimeMinutes * 60000;
    }
    
    /**
     * Get the powerup respawn time from the menu, inputted by a player.
     * 
     * @return powerup respawn time in millisec.
     */
    public int getPowerupRespawnTimeMS() {
        return powerupRespawnSeconds * 1000;
    }
    
    /**
     * Get the kills to win from the menu, inputted by a player.
     * 
     * @return kills to win.
     */
    public int getKillsToWin() {
        return killsToWin;
    }
}
