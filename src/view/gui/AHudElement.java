
package view.gui;

import application.TanksAppAdapter;
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
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public abstract class AHudElement implements PropertyChangeListener, IHudElement {

    /**
     * The picture which is the background image of the element.
     */
    protected Picture picture;
    
    /**
     * Manages assets.
     */
    protected AssetManager assetManager = TanksAppAdapter.INSTANCE.getAssetManager();
    
    /**
     * This is the node where all the gui is attatched.
     */
    protected Node guiNode = TanksAppAdapter.INSTANCE.getGUINode();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        TanksAppAdapter.INSTANCE.getGUINode().attachChild(picture);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
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
