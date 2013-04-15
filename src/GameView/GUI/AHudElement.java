/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView.GUI;

import GameUtilities.TankAppAdapter;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;
import java.beans.PropertyChangeListener;

/**
 *
 * @author backman
 */
public abstract class AHudElement implements PropertyChangeListener, IHudElement {

    protected Picture picture;
    protected AssetManager assetManager = TankAppAdapter.INSTANCE.getAssetManager();
    protected Node guiNode = TankAppAdapter.INSTANCE.getGUINode();
    
    /**
     * Make the element visible in the GUI.
     */
    public void show() {
        TankAppAdapter.INSTANCE.getGUINode().attachChild(picture);
    }
    
    /**
     * Remove the element from the GUI.
     */
    public void hide() {
        TankAppAdapter.INSTANCE.getGUINode().detachChild(picture);
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
