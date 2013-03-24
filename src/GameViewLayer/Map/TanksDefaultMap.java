/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.Map;

import GameLogicLayer.Game.GameManager;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

;
/**
 *
 * @author Daniel
 */
public class TanksDefaultMap extends AbstractAppState {
    private AssetManager assetManager;
    
    private Node rootNode;
    
    private GameManager app;
    private GameManager tanksApp;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
      super.initialize(stateManager, app); 
      this.tanksApp = (GameManager)app;       
      rootNode = tanksApp.getRootNode();
      assetManager = tanksApp.getAssetManager();
      
   }

}
