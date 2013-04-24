
package GameView.GUI;

import App.TanksAppAdapter;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeListener;

/**
 * The fundamental parts of an HUD element. Contains a background, picture,
 * a reference to the AssetManager, assetManager, and the GUI node, guiNode.
 * The class also forces all HUD elements to implement the interface
 * PropertyChangeListener and IHudElement which contains the show() and hide()
 * methods.
 * 
 * @author Johan Backman
 */
public abstract class AHudElement implements PropertyChangeListener, IHudElement {

    protected Picture picture;
    protected AssetManager assetManager = TanksAppAdapter.INSTANCE.getAssetManager();
    protected Node guiNode = TanksAppAdapter.INSTANCE.getGUINode();
    
    /**
     * Make the element visible in the GUI.
     */
    public void show() {
        TanksAppAdapter.INSTANCE.getGUINode().attachChild(picture);
    }
    
    /**
     * Remove the element from the GUI.
     */
    public void hide() {
        TanksAppAdapter.INSTANCE.getGUINode().detachChild(picture);
    }

    /**
     * Format: Pic: #######
     * 
     * @return name of the picture.
     */
    @Override
    public String toString() {
        return "Pic: " + picture.getName();
    }
}
