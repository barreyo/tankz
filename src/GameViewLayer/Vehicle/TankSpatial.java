/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.Vehicle;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
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
    
    private Vector3f direction;
    private Vector3f position;

    /**
     * Create a default tank spatial.
     *
     * @param tank 
     * @param scale 
     */
    public TankSpatial(Spatial tank, float scale) {
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

    /*
     * @inheritdoc
     */
    @Override
    public synchronized Vector3f getDirection() {
        return direction;
    }

     /*
     * @inheritdoc
     */
    @Override
    public synchronized Vector3f getPosition() {
        return position;
    }

     /*
     * @inheritdoc
     */
    @Override
    public synchronized void setDirection(Vector3f direction) {
        this.direction = direction;
    }

     /*
     * @inheritdoc
     */
    @Override
    public synchronized void setPosition(Vector3f position) {
        this.position = position;
    }
}
