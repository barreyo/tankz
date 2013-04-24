package App;

import com.jme3.app.state.AbstractAppState;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioRenderer;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.input.InputManager;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.Trigger;
import com.jme3.light.Light;
import com.jme3.material.Material;
import com.jme3.post.SceneProcessor;
import com.jme3.renderer.Camera;
import com.jme3.renderer.Caps;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;

/**
 *
 * @author Daniel
 */
public enum TanksAppAdapter {
    INSTANCE;
    
    private final TanksApp tanksApp = new TanksApp();
    
    private TanksAppAdapter() {}
    
    public void setBulletAppStateEnabled(boolean bool) {
        tanksApp.getBulletAppState().setEnabled(bool);
    }
    
    public void attachChildToRootNode(Spatial spatial) {
        tanksApp.getRootNode().attachChild(spatial);
    }

    public void addLightToRootNode(Light light) {
        tanksApp.getRootNode().addLight(light);
    }
    
    public void removeLightFromRootNode(Light light) {
        tanksApp.getRootNode().removeLight(light);
    }

    public void detachAllGUIChildren() {
        tanksApp.getGuiNode().detachAllChildren();
    }

    public void detachAllRootChildren() {
        tanksApp.getRootNode().detachAllChildren();
    }

    public void attachChildToGUINode(Spatial spatial) {
        tanksApp.getGuiNode().attachChild(spatial);
    }
    
    public void attachAppState(AbstractAppState appState) {
        tanksApp.getStateManager().attach(appState);
    }

    public void detachAppState(AbstractAppState appStaet) {
        tanksApp.getStateManager().detach(appStaet);
    }
    
    public AssetManager getAssetManager() {
        return tanksApp.getAssetManager();
    }

    public Spatial loadModel(String path) {
        return tanksApp.getAssetManager().loadModel(path);
    }

    public Material loadMaterial(String path) {
        return tanksApp.getAssetManager().loadMaterial(path);
    }
    
    public boolean hasInputMapping(String mapping) {
        return tanksApp.getInputManager().hasMapping(mapping);
    }

    public void deleteInputMapping(String mapping) {
        tanksApp.getInputManager().deleteMapping(mapping);
    }

    public void addInputMapping(String mapping, Trigger... triggers) {
        tanksApp.getInputManager().addMapping(mapping, triggers);
    }

    public void addInputListener(InputListener actionListener, String... mappingNames) {
        tanksApp.getInputManager().addListener(actionListener, mappingNames);
    }

    public void removeInputListener(InputListener actionListener) {
        tanksApp.getInputManager().removeListener(actionListener);
    }
    
    public InputManager getInputManager() {
        return tanksApp.getInputManager();
    }

    public void setCursorVisible(boolean bool) {
        tanksApp.getInputManager().setCursorVisible(bool);
    }

    public AudioRenderer getAudioRenderer() {
        return tanksApp.getAudioRenderer();
    }

    public ViewPort getGuiViewPort() {
        return tanksApp.getGuiViewPort();
    }

    public void stop() {
        tanksApp.stop();
    }

    public void removeGuiViewProcessor(SceneProcessor processor) {
        tanksApp.getGuiViewPort().removeProcessor(processor);
    }

    public void addGuiViewProcessor(SceneProcessor processor) {
        tanksApp.getGuiViewPort().addProcessor(processor);
    }
    
    public AppSettings getSettings() {
        return tanksApp.getSettings();
    }

    public void addViewPortProcessor(SceneProcessor processor) {
        tanksApp.getViewPort().addProcessor(processor);
    }

    public RenderManager getRenderManager() {
        return tanksApp.getRenderManager();
    }

    public void preloadSpatial(Spatial s) {
        tanksApp.getRenderManager().preloadScene(s);
    }

    public void pauseAudioSource(AudioNode audio) {
        tanksApp.getAudioRenderer().pauseSource(audio);
    }

    public Spatial getRootNode() {
        return tanksApp.getRootNode();
    }

    public PhysicsSpace getPhysicsSpace() {
        return tanksApp.getBulletAppState().getPhysicsSpace();
    }

    public void addAllToPhysicsSpace(Spatial spatial) {
        tanksApp.getBulletAppState().getPhysicsSpace().addAll(spatial);
    }

    public void detachChildFromRootNode(Spatial spatial) {
        tanksApp.getRootNode().detachChild(spatial);
    }

    public void addToPhysicsSpace(Object obj) {
        tanksApp.getBulletAppState().getPhysicsSpace().add(obj);
    }

    public void removeFromPhysicsSpace(Object obj) {
        tanksApp.getBulletAppState().getPhysicsSpace().remove(obj);
    }

    public void addPhysiscsCollisionListener(PhysicsCollisionListener listener) {
        tanksApp.getBulletAppState().getPhysicsSpace().addCollisionListener(listener);
    }
    
    public Node getGUINode() {
        return tanksApp.getGuiNode();
    }
    
    public float getScreenWidth() {
        return  tanksApp.getSettings().getWidth();  
    }
    
    public float getScreenHeight() {
        return tanksApp.getSettings().getHeight();
    }

    public boolean rendererContains(Caps caps) {
        return tanksApp.getRenderer().getCaps().contains(caps);
    }

    public Camera getCamera() {
        return tanksApp.getCamera().clone();
    }

    public ViewPort createMainView(String loc, Camera cam) {
        return tanksApp.getRenderManager().createMainView(loc, cam);
    }

    public void start() {
        tanksApp.start();
    }

    public void setSettings(AppSettings appSettings) {
        tanksApp.setSettings(appSettings);
    }

    public void removePhysiscsCollisionListener(PhysicsCollisionListener listener) {
        tanksApp.getBulletAppState().getPhysicsSpace().removeCollisionListener(listener);
    }

    public void removeAllPhysics() {
        tanksApp.getBulletAppState().getPhysicsSpace().removeAll(tanksApp.getRootNode());
    }
}
