package GameLogicLayer.Game;

import GameLogicLayer.Animation.AnimationManager;
import GameLogicLayer.AppStates.TanksAppStateManager;
import GameLogicLayer.Effects.EffectsManager;
import GameLogicLayer.GUI.GUIManager;
import GameLogicLayer.Graphics.GraphicManager;
import GameLogicLayer.Graphics.MaterialManager;
import GameLogicLayer.Map.GameMapManager;
import GameLogicLayer.Physics.PhysicsManager;
import GameLogicLayer.Sounds.SoundManager;
import GameLogicLayer.controls.ControlManager;
import GameLogicLayer.entity.GameEntityManager;
import GameLogicLayer.util.PreloadManager;
import GameLogicLayer.util.UserSettings;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.post.FilterPostProcessor;
import com.jme3.system.AppSettings;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * The main controller of the game TanksGame.
 *
 * @author Daniel, Per, Johan, Albin
 */
public class TanksGame extends SimpleApplication {
    // A reference to this app. This is needed to allow access to managers.
    private static TanksGame tanksApp;
    
    // Managers
    private TanksAppStateManager tanksAppStateManager;
    private GUIManager guiManager;
    private ControlManager controlManager;
    private PhysicsManager physicsManager;
    private EffectsManager effectsManager;
    private SoundManager soundManager;
    private GraphicManager graphicManager;
    private PreloadManager preloadManager;
    private AnimationManager animManager;
    private GameMapManager mapManager;
    private MaterialManager materialManager;
    private GameEntityManager entityManager;
    private UserSettings userSettings;
    private BulletAppState bulletAppState;
    private FilterPostProcessor fpp;
 
    /*
     * Creates a new TanksGame which starts in settings window.
     */
    /**
     *
     */
    public TanksGame() {
        super(new StatsAppState());
    }
    
    /**
     *  Initiates the application.
     */
    @Override
    public void simpleInitApp() {
        tanksApp = this;
        
        fpp = new FilterPostProcessor(assetManager);
        
        // Create a manager for different appstates
        tanksAppStateManager = new TanksAppStateManager();
        
        // Attaching a physics appstate
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        // Creating different essential managers
        guiManager = new GUIManager();
        controlManager = new ControlManager();
        preloadManager = new PreloadManager();
        graphicManager = new GraphicManager();
        materialManager = new MaterialManager();
        effectsManager = new EffectsManager();
        entityManager = new GameEntityManager();
        userSettings = new UserSettings();
        physicsManager = new PhysicsManager();
        soundManager = new SoundManager();
        animManager = new AnimationManager();
        mapManager = new GameMapManager();
        
        // Show the main menu
        guiManager.showMainMenu();
        inputManager.setCursorVisible(true);
        
        // Detach the settings window.
        stateManager.detach(stateManager.getState(StatsAppState.class));
        
        // Used to logger messages for HUD handler Nifty
        Logger.getLogger("de.lessvoid.nifty").setLevel(Level.SEVERE);
        Logger.getLogger("NiftyInputEventHandlingLog").setLevel(Level.SEVERE);
    }
    
    /**
     *
     * @return
     */
    public static TanksGame getApp() {
        return tanksApp;
    }

    /**
     *
     * @return
     */
    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }
    
    /**
     *
     * @return
     */
    public TanksAppStateManager getTanksAppStateManager(){
        return tanksAppStateManager;
    }
    
    /**
     *
     * @return
     */
    public GUIManager getGUIManager() {
        return guiManager;
    }
    
    /**
     *
     * @return
     */
    public ControlManager getControlManager(){
        return controlManager;
    }
    
    /**
     *
     * @return
     */
    public PhysicsManager getPhysicsManager(){
        return physicsManager;
    }
    
    /**
     *
     * @return
     */
    public EffectsManager getEffectsManager(){
        return effectsManager;
    }
    /**
     *
     * @return
     */
    public SoundManager getSoundManager(){
        return soundManager;
    }
    
    /**
     *
     * @return
     */
    public AnimationManager getAnimManager(){
        return animManager;
    }

    /**
     *
     * @return
     */
    public PreloadManager getPreloadManager() {
        return preloadManager;
    }
    
    /**
     *
     * @return
     */
    public AppSettings getSettings() {
        return settings;
    }

    /**
     *
     * @return
     */
    public GameMapManager getMapManager() {
        return mapManager;
    }

    /**
     *
     * @return
     */
    public MaterialManager getMaterialManager() {
        return materialManager;
    }

    /**
     *
     * @return
     */
    public GraphicManager getGraphicManager() {
        return graphicManager;
    }

    /**
     *
     * @return
     */
    public UserSettings getUserSettings() {
        return userSettings;
    }

    /**
     *
     * @return
     */
    public GameEntityManager getEntityManager() {
        return entityManager;
    }

    /**
     *
     * @return
     */
    public FilterPostProcessor getFpp() {
        return fpp;
    }
}
