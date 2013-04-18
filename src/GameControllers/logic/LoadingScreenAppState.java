package GameControllers.logic;

import GameModel.EGameState;
import App.TanksAppAdapter;
import GameControllers.TanksFactory;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import java.util.Properties;

/**
 *
 * @author Daniel
 */
public final class LoadingScreenAppState extends AbstractAppState implements ScreenController, Controller {
    private static LoadingScreenAppState instance;
    
    private Nifty nifty;
    private Element progressBarElement;
    private Element progressTextElement;
   
    private int frameCount;
    private int nbrOfPlayers;
    
    private static final int FRAME_COUNT = 50;
    
    private boolean isLoaded = false;
    
    /**
     *
     */
    private LoadingScreenAppState() {
        // Get managers
        nifty = GUIManager.INSTANCE.getNifty();
        
        // Register as a screen controller and add scree n to Hud handler Nifty
        nifty.registerScreenController(this);
        nifty.addXml("Interface/Nifty/LoadingScreen.xml"); 
        
        progressTextElement = nifty.getScreen("loadingScreen").findElementByName("loadingtext");
        //progressBarElement = nifty.getScreen("loadingScreen").findElementByName("progress");
        
        System.out.println("Tjena");
    }
    
    public static synchronized LoadingScreenAppState getInstance() {
        if (instance == null) {
            instance = new LoadingScreenAppState();
        }
        return instance;
    }
    
    /**
     *
     */
    public void showLoadingScreen() {
        nifty.gotoScreen("loadingScreen");
        System.out.println("On loadingscreen");
    }
    
    /**
     *
     * @param stateManager
     */
    @Override
    public void stateAttached(AppStateManager stateManager) {
        super.stateAttached(stateManager);
        EGameState.setGameState(EGameState.LOADING_MAP);
        frameCount = 0;
    }
    
    /**
     *
     * @param stateManager
     */
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        nifty.gotoScreen("end");
        isLoaded = true;
    }
    
    /**
     *
     * @param stateManager
     * @param app
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
    }
    
    @Override
    public void update(float tpf) {
        if (frameCount == 1) {
            //load the screen
            showLoadingScreen();
        } else if (frameCount == FRAME_COUNT) { //using 150 as a debug, this is where you load the game

            //at end of loading
            TanksAppAdapter.INSTANCE.detachAppState(this);

            TanksAppAdapter.INSTANCE.setCursorVisible(false);
           
            GameMapManager.INSTANCE.load(GameMapManager.INSTANCE.getCurrentIntMap());
            
            EGameState.setGameState(EGameState.RUNNING);
            System.out.println("Finished loading game");
        }
        //this.setProgress((float)frameCount, "Tanks");
        frameCount++;
    }
    
    /**
     *
     */
    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen");
    }

    /**
     *
     */
    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen");
    }

    /**
     *
     * @param nifty
     * @param screen
     */
    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("bind( " + screen.getScreenId() + ")");
        progressBarElement = nifty.getScreen("loadingScreen").findElementByName("progress");
    }

    public void bind(Nifty nifty, Screen screen, Element element, Properties parameter, Attributes controlDefinitionAttributes) {
        progressBarElement = element.findElementByName("progress");
        progressTextElement = element.findElementByName("progress-text");
    }

    public void init(Properties parameter, Attributes controlDefinitionAttributes) {
        // Not supported
    }

    public void onFocus(boolean getFocus) {
        // Not supported
    }

    public boolean inputEvent(NiftyInputEvent inputEvent) {
        return false; // Not supported
    }
    
    /**
     * Update the progressbar.
     * 
     * @param value 
     * @param nowLoading 
     */
    public void setProgress(final float value, final String nowLoading) {
        float progressTemp = value;
        
        // Fixing faulty vaules if such should come through.
        if (progressTemp < 0.0f) {
            progressTemp = 0.0f;
        } else if (progressTemp > 1.0f) {
            progressTemp = 1.0f;
        }
        // Updates the visual part of the progressbar
        final int MIN_WIDTH = 14;
        int pixelWidth = (int) (MIN_WIDTH + (progressBarElement.getParent().getWidth() - MIN_WIDTH) * progressTemp);
        progressBarElement.setConstraintWidth(new SizeValue(pixelWidth + "px"));
        progressBarElement.getParent().layoutElements();
        
        // Sets the text under the progressbar
        progressTextElement.getRenderer(TextRenderer.class).setText("            Loading " + nowLoading + "... ");
    }
}
