/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.Map;

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
public class TanksDefaultMap implements ITankMap {
    private AssetManager assetManager;
    private Node mapNode;
    
    /**
     *
     * @param manager
     * @param map
     */
    public TanksDefaultMap(AssetManager manager, Node map) {
        assetManager = manager;
        mapNode = map;
    }
    
    /**
     *
     */
    @Override
    public void initMap() {
        mapNode.attachChild(makeFloor());
    }
    
    private Spatial makeFloor() {
        Box box = new Box(new Vector3f(0, -4, -5), 100, .2f, 100);
        Geometry floor = new Geometry("the Floor", box);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Gray);
        floor.setMaterial(mat1);
        return floor;
    }
}
