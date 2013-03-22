/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.Vehicle;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * A default tank spatial connected to a node.
 *
 * @author Daniel, Per, Johan, Albin
 */
public class TankSpatial implements IVehicleSpatial{
    private Node vehicleNode;
    private Spatial tank;

    /**
     * Create a default tank spatial.
     *
     * @param assetManager AssetManager used for loading assets
     */
    public TankSpatial(AssetManager assetManager, Spatial tank, float scale) {
        this.tank = tank;
        tank.scale(scale);
        vehicleNode = (Node)tank;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Node getVehicleNode() {
        return vehicleNode;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Spatial getVehicleSpatial() {
        return tank;
    }
}
