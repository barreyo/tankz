package application;

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
 * Adapter between JMonkeyEngine and the game.
 * Gives access to root node, gui node etc. 
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public enum TanksAppAdapter {
    /**
     * Instance of this singleton.
     */
    INSTANCE;
    
    private final TanksApp tanksApp = new TanksApp();
    
    /**
     * Enable or disable bullet physics app state.
     * 
     * @param bool true/false.
     */
    public void setBulletAppStateEnabled(boolean bool) {
        tanksApp.getBulletAppState().setEnabled(bool);
    }
    
    /**
     * Attach a spatial to the root node.
     * 
     * @param spatial spatial to attach.
     */
    public void attachChildToRootNode(Spatial spatial) {
        tanksApp.getRootNode().attachChild(spatial);
    }

    /**
     * Attach a light source to the root node.
     * 
     * @param light light to attach.
     */
    public void addLightToRootNode(Light light) {
        tanksApp.getRootNode().addLight(light);
    }
    
    /**
     * Remove a light source from the root node.
     * 
     * @param light light to remove.
     */
    public void removeLightFromRootNode(Light light) {
        tanksApp.getRootNode().removeLight(light);
    }

    /**
     * Remove all GUI spatials from the GUI node.
     */
    public void detachAllGUIChildren() {
        tanksApp.getGuiNode().detachAllChildren();
    }

    /**
     * Remove all children from the root node.
     */
    public void detachAllRootChildren() {
        tanksApp.getRootNode().detachAllChildren();
    }

    /**
     * Attach a spatial to the GUI node.
     * 
     * @param spatial spatial to attach.
     */
    public void attachChildToGUINode(Spatial spatial) {
        tanksApp.getGuiNode().attachChild(spatial);
    }
    
    /**
     * Attach an appstate through the appstate manager.
     * 
     * @param appState appstate to attach.
     */
    public void attachAppState(AbstractAppState appState) {
        tanksApp.getStateManager().attach(appState);
    }

    /**
     * Detach an appstate through the appstate manager.
     * 
     * @param appState appstate to detach.
     */
    public void detachAppState(AbstractAppState appState) {
        tanksApp.getStateManager().detach(appState);
    }
    
    /**
     * Returns the game engine Asset Manager.
     *  
     * @return assetManager.
     */
    public AssetManager getAssetManager() {
        return tanksApp.getAssetManager();
    }

    /**
     * Load a model into the spatial buffer.
     * 
     * @param path path to the spatial.
     * @return the loaded spatial.
     */
    public Spatial loadModel(String path) {
        return tanksApp.getAssetManager().loadModel(path);
    }

    /**
     * Load a material.
     * 
     * @param path path to the material.
     * @return the loaded material.
     */
    public Material loadMaterial(String path) {
        return tanksApp.getAssetManager().loadMaterial(path);
    }
    
    /**
     * Determines if an object got a input mapping loaded in the input manager.
     * 
     * @param mapping string map.
     * @return true if the object got a mapping, otherwise false.
     */
    public boolean hasInputMapping(String mapping) {
        return tanksApp.getInputManager().hasMapping(mapping);
    }

    /**
     * Delete input mapping in the input manager.
     * 
     * @param mapping mapping to be removed.
     */
    public void deleteInputMapping(String mapping) {
        tanksApp.getInputManager().deleteMapping(mapping);
    }

    /**
     * Add input mapping for triggers.
     * 
     * @param mapping mapping name.
     * @param triggers buttons mapping to.
     */
    public void addInputMapping(String mapping, Trigger... triggers) {
        tanksApp.getInputManager().addMapping(mapping, triggers);
    }

    /**
     * Add an input listener to the input manager.
     * 
     * @param actionListener listener to be added.
     * @param mappingNames mapping names.
     */
    public void addInputListener(InputListener actionListener, String... mappingNames) {
        tanksApp.getInputManager().addListener(actionListener, mappingNames);
    }

    /**
     * Remove an input listener from the input manager.
     * 
     * @param actionListener listener to be removed.
     */
    public void removeInputListener(InputListener actionListener) {
        tanksApp.getInputManager().removeListener(actionListener);
    }
    
    /**
     * Returns a reference to the input manager.
     * 
     * @return inputManager.
     */
    public InputManager getInputManager() {
        return tanksApp.getInputManager();
    }

    /**
     * Hide or show the cursor.
     * 
     * @param bool true: show, false: hide;
     */
    public void setCursorVisible(boolean bool) {
        tanksApp.getInputManager().setCursorVisible(bool);
    }

    /**
     * Returns the Audio Renderer.
     * 
     * @return audioRenderer.
     */
    public AudioRenderer getAudioRenderer() {
        return tanksApp.getAudioRenderer();
    }

    /**
     * Returns the GUI viewport.
     * 
     * @return viewport.
     */
    public ViewPort getGuiViewPort() {
        return tanksApp.getGuiViewPort();
    }

    /**
     * Finish the game, kills all manager processes etc.
     */
    public void stop() {
        tanksApp.stop();
    }

    /**
     * Remove a GUI viewport processor.
     * 
     * @param processor processor to be removed.
     */
    public void removeGuiViewProcessor(SceneProcessor processor) {
        tanksApp.getGuiViewPort().removeProcessor(processor);
    }

    /**
     * Add a GUI viewport processor. In this application we use Nifty GUI to 
     * create processors.
     * 
     * @param processor
     */
    public void addGuiViewProcessor(SceneProcessor processor) {
        tanksApp.getGuiViewPort().addProcessor(processor);
    }
    
    /**
     * Returns the video settings.
     *
     * @return vide settings.
     */
    public AppSettings getSettings() {
        return tanksApp.getSettings();
    }

    /**
     * Add a viewport processor to the viewport.
     * 
     * @param processor processor to be added.
     */
    public void addViewPortProcessor(SceneProcessor processor) {
        tanksApp.getViewPort().addProcessor(processor);
    }

    /**
     * Returns the render manager.
     * 
     * @return render manager.
     */
    public RenderManager getRenderManager() {
        return tanksApp.getRenderManager();
    }

    /**
     * Load a spatial into the render manager buffer.
     * 
     * @param s spatial to be loaded.
     */
    public void preloadSpatial(Spatial s) {
        tanksApp.getRenderManager().preloadScene(s);
    }

    /**
     * Pause the audio on a AudioNode.
     * 
     * @param audio node to be paused.
     */
    public void pauseAudioSource(AudioNode audio) {
        tanksApp.getAudioRenderer().pauseSource(audio);
    }

    /**
     * Returns the root node.
     * 
     * @return root node.
     */
    public Spatial getRootNode() {
        return tanksApp.getRootNode();
    }

    /**
     * Returns the physics space.
     * 
     * @return physics space.
     */
    public PhysicsSpace getPhysicsSpace() {
        return tanksApp.getBulletAppState().getPhysicsSpace();
    }

    /**
     * Add spatial to physics space.
     * 
     * @param spatial
     */
    public void addAllToPhysicsSpace(Spatial spatial) {
        tanksApp.getBulletAppState().getPhysicsSpace().addAll(spatial);
    }

    /**
     * Detach a spatial from the root node in other words hiding it.
     * 
     * @param spatial spatial to be detached.
     */
    public void detachChildFromRootNode(Spatial spatial) {
        tanksApp.getRootNode().detachChild(spatial);
    }

    /**
     * Add an object to the physics space.
     * 
     * @param obj object to be added.
     */
    public void addToPhysicsSpace(Object obj) {
        tanksApp.getBulletAppState().getPhysicsSpace().add(obj);
    }

    /**
     * Remove an object from the physics space.
     * 
     * @param obj object to be removed.
     */
    public void removeFromPhysicsSpace(Object obj) {
        tanksApp.getBulletAppState().getPhysicsSpace().remove(obj);
    }

    /**
     * Add a phyics collision listener to the physics space. 
     * 
     * @param listener to be added.
     */
    public void addPhysiscsCollisionListener(PhysicsCollisionListener listener) {
        tanksApp.getBulletAppState().getPhysicsSpace().addCollisionListener(listener);
    }
    
    /**
     * Returns the GUI node.
     * 
     * @return gui node.
     */
    public Node getGUINode() {
        return tanksApp.getGuiNode();
    }
    
    /**
     * Returns the current screen width. Sat through the app settings.
     * 
     * @return screen width.
     */
    public float getScreenWidth() {
        return tanksApp.getSettings().getWidth();  
    }
    
    /**
     * Returns the current screen height. Sat through the app settings.
     * 
     * @return screen height.
     */
    public float getScreenHeight() {
        return tanksApp.getSettings().getHeight();
    }

    /**
     * Determine if a renderer contains the specific shader, API or feature
     * version.
     * 
     * @param caps graphics properties.
     * @return true is it contains the propertie, otherwiese false.
     */
    public boolean rendererContains(Caps caps) {
        return tanksApp.getRenderer().getCaps().contains(caps);
    }

    /**
     * Returns the game camera.
     * 
     * @return camera.
     */
    public Camera getCamera() {
        return tanksApp.getCamera().clone();
    }

    /**
     * Create the main view.
     * 
     * @param loc view path.
     * @param cam camera.
     * @return a viewport.
     */
    public ViewPort createMainView(String loc, Camera cam) {
        return tanksApp.getRenderManager().createMainView(loc, cam);
    }

    /**
     * Start the application.
     */
    public void start() {
        tanksApp.start();
    }

    /**
     * Set the application settings, like resolution, AA, BPP etc.
     * 
     * @param appSettings settings.
     */
    public void setSettings(AppSettings appSettings) {
        tanksApp.setSettings(appSettings);
    }

    /**
     * Remove a physics collision listener.
     * 
     * @param listener listener.
     */
    public void removePhysiscsCollisionListener(PhysicsCollisionListener listener) {
        tanksApp.getBulletAppState().getPhysicsSpace().removeCollisionListener(listener);
    }

    /**
     * Remove all physic spaces.
     */
    public void removeAllPhysics() {
        tanksApp.getBulletAppState().getPhysicsSpace().removeAll(tanksApp.getRootNode());
    }

    /**
     * Determine if a spatial is attached to the root node.
     * 
     * @param spatial spatial.
     * @return true if attached, otherwise false.
     */
    public boolean isAttachedToRootNode(Spatial spatial) {
        return tanksApp.getRootNode().hasChild(spatial);
    }

    /**
     * Get all the current GUI children in a cloned list.
     * 
     * @return cloned list of GUI children.
     */
    public List<Spatial> getGuiChildren() {
        return new ArrayList<Spatial>(tanksApp.getGuiNode().getChildren());
    }
}
