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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public enum TanksAppAdapter {
    /**
     *
     */
    INSTANCE;
    
    private final TanksApp tanksApp = new TanksApp();
    
    private TanksAppAdapter() {}
    
    /**
     *
     * @param bool
     */
    public void setBulletAppStateEnabled(boolean bool) {
        tanksApp.getBulletAppState().setEnabled(bool);
    }
    
    /**
     *
     * @param spatial
     */
    public void attachChildToRootNode(Spatial spatial) {
        tanksApp.getRootNode().attachChild(spatial);
    }

    /**
     *
     * @param light
     */
    public void addLightToRootNode(Light light) {
        tanksApp.getRootNode().addLight(light);
    }
    
    /**
     *
     * @param light
     */
    public void removeLightFromRootNode(Light light) {
        tanksApp.getRootNode().removeLight(light);
    }

    /**
     *
     */
    public void detachAllGUIChildren() {
        tanksApp.getGuiNode().detachAllChildren();
    }

    /**
     *
     */
    public void detachAllRootChildren() {
        tanksApp.getRootNode().detachAllChildren();
    }

    /**
     *
     * @param spatial
     */
    public void attachChildToGUINode(Spatial spatial) {
        tanksApp.getGuiNode().attachChild(spatial);
    }
    
    /**
     *
     * @param appState
     */
    public void attachAppState(AbstractAppState appState) {
        tanksApp.getStateManager().attach(appState);
    }

    /**
     *
     * @param appStaet
     */
    public void detachAppState(AbstractAppState appStaet) {
        tanksApp.getStateManager().detach(appStaet);
    }
    
    /**
     *
     * @return
     */
    public AssetManager getAssetManager() {
        return tanksApp.getAssetManager();
    }

    /**
     *
     * @param path
     * @return
     */
    public Spatial loadModel(String path) {
        return tanksApp.getAssetManager().loadModel(path);
    }

    /**
     *
     * @param path
     * @return
     */
    public Material loadMaterial(String path) {
        return tanksApp.getAssetManager().loadMaterial(path);
    }
    
    /**
     *
     * @param mapping
     * @return
     */
    public boolean hasInputMapping(String mapping) {
        return tanksApp.getInputManager().hasMapping(mapping);
    }

    /**
     *
     * @param mapping
     */
    public void deleteInputMapping(String mapping) {
        tanksApp.getInputManager().deleteMapping(mapping);
    }

    /**
     *
     * @param mapping
     * @param triggers
     */
    public void addInputMapping(String mapping, Trigger... triggers) {
        tanksApp.getInputManager().addMapping(mapping, triggers);
    }

    /**
     *
     * @param actionListener
     * @param mappingNames
     */
    public void addInputListener(InputListener actionListener, String... mappingNames) {
        tanksApp.getInputManager().addListener(actionListener, mappingNames);
    }

    /**
     *
     * @param actionListener
     */
    public void removeInputListener(InputListener actionListener) {
        tanksApp.getInputManager().removeListener(actionListener);
    }
    
    /**
     *
     * @return
     */
    public InputManager getInputManager() {
        return tanksApp.getInputManager();
    }

    /**
     *
     * @param bool
     */
    public void setCursorVisible(boolean bool) {
        tanksApp.getInputManager().setCursorVisible(bool);
    }

    /**
     *
     * @return
     */
    public AudioRenderer getAudioRenderer() {
        return tanksApp.getAudioRenderer();
    }

    /**
     *
     * @return
     */
    public ViewPort getGuiViewPort() {
        return tanksApp.getGuiViewPort();
    }

    /**
     *
     */
    public void stop() {
        tanksApp.stop();
    }

    /**
     *
     * @param processor
     */
    public void removeGuiViewProcessor(SceneProcessor processor) {
        tanksApp.getGuiViewPort().removeProcessor(processor);
    }

    /**
     *
     * @param processor
     */
    public void addGuiViewProcessor(SceneProcessor processor) {
        tanksApp.getGuiViewPort().addProcessor(processor);
    }
    
    /**
     *
     * @return
     */
    public AppSettings getSettings() {
        return tanksApp.getSettings();
    }

    /**
     *
     * @param processor
     */
    public void addViewPortProcessor(SceneProcessor processor) {
        tanksApp.getViewPort().addProcessor(processor);
    }

    /**
     *
     * @return
     */
    public RenderManager getRenderManager() {
        return tanksApp.getRenderManager();
    }

    /**
     *
     * @param s
     */
    public void preloadSpatial(Spatial s) {
        tanksApp.getRenderManager().preloadScene(s);
    }

    /**
     *
     * @param audio
     */
    public void pauseAudioSource(AudioNode audio) {
        tanksApp.getAudioRenderer().pauseSource(audio);
    }

    /**
     *
     * @return
     */
    public Spatial getRootNode() {
        return tanksApp.getRootNode();
    }

    /**
     *
     * @return
     */
    public PhysicsSpace getPhysicsSpace() {
        return tanksApp.getBulletAppState().getPhysicsSpace();
    }

    /**
     *
     * @param spatial
     */
    public void addAllToPhysicsSpace(Spatial spatial) {
        tanksApp.getBulletAppState().getPhysicsSpace().addAll(spatial);
    }

    /**
     *
     * @param spatial
     */
    public void detachChildFromRootNode(Spatial spatial) {
        tanksApp.getRootNode().detachChild(spatial);
    }

    /**
     *
     * @param obj
     */
    public void addToPhysicsSpace(Object obj) {
        tanksApp.getBulletAppState().getPhysicsSpace().add(obj);
    }

    /**
     *
     * @param obj
     */
    public void removeFromPhysicsSpace(Object obj) {
        tanksApp.getBulletAppState().getPhysicsSpace().remove(obj);
    }

    /**
     *
     * @param listener
     */
    public void addPhysiscsCollisionListener(PhysicsCollisionListener listener) {
        tanksApp.getBulletAppState().getPhysicsSpace().addCollisionListener(listener);
    }
    
    /**
     *
     * @return
     */
    public Node getGUINode() {
        return tanksApp.getGuiNode();
    }
    
    /**
     *
     * @return
     */
    public float getScreenWidth() {
        return  tanksApp.getSettings().getWidth();  
    }
    
    /**
     *
     * @return
     */
    public float getScreenHeight() {
        return tanksApp.getSettings().getHeight();
    }

    /**
     *
     * @param caps
     * @return
     */
    public boolean rendererContains(Caps caps) {
        return tanksApp.getRenderer().getCaps().contains(caps);
    }

    /**
     *
     * @return
     */
    public Camera getCamera() {
        return tanksApp.getCamera().clone();
    }

    /**
     *
     * @param loc
     * @param cam
     * @return
     */
    public ViewPort createMainView(String loc, Camera cam) {
        return tanksApp.getRenderManager().createMainView(loc, cam);
    }

    /**
     *
     */
    public void start() {
        tanksApp.start();
    }

    /**
     *
     * @param appSettings
     */
    public void setSettings(AppSettings appSettings) {
        tanksApp.setSettings(appSettings);
    }

    /**
     *
     * @param listener
     */
    public void removePhysiscsCollisionListener(PhysicsCollisionListener listener) {
        tanksApp.getBulletAppState().getPhysicsSpace().removeCollisionListener(listener);
    }

    /**
     *
     */
    public void removeAllPhysics() {
        tanksApp.getBulletAppState().getPhysicsSpace().removeAll(tanksApp.getRootNode());
    }

    /**
     *
     * @param spatial
     * @return
     */
    public boolean isAttachedToRootNode(Spatial spatial) {
        return tanksApp.getRootNode().hasChild(spatial);
    }

    public List<Spatial> getGuiChildren() {
        List<Spatial> newList = new ArrayList<Spatial>();
        for (Spatial spatial : tanksApp.getGuiNode().getChildren()) {
            newList.add(spatial);
        }
        return newList;
    }
}
