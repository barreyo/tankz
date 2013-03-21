/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Controllers;

import GameViewLayer.Maps.ITankMap;
import GameViewLayer.Maps.TanksDefaultMap;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;

/**
 *
 * @author Daniel
 */
public class MainController extends SimpleApplication {
    // Map related fields
    private Node mapNode = new Node("Map");
    private ITankMap map;

    @Override
    public void simpleInitApp() {
        // set temp speed of fly cam
        flyCam.setMoveSpeed(50);
        
        // create new Default map and init it
        map = new TanksDefaultMap(assetManager, mapNode);
        initMap();
        //Add a light source so we can see the model
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
        rootNode.addLight(dl);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void initMap() {
        map.initMap();
        rootNode.attachChild(mapNode);
    }
}

