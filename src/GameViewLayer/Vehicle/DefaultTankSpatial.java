/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.Vehicle;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.shape.Cylinder;

/**
 *
 * @author Daniel
 */
public class DefaultTankSpatial implements IVehicleSpatial{
    private Node vehicleNode;
    private Spatial tank;
    private float radius;

    public DefaultTankSpatial(AssetManager assetManager) {
        tank = assetManager.loadModel("Models/tanken/tanken.j3o");
        tank.scale(3f);
              
        vehicleNode = (Node)tank;
    }
    
    @Override
    public Node getVehicleNode() {
        return vehicleNode;
    }

    @Override
    public Spatial getSpatial() {
        return tank;
    }
}
