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
public class DefaultTankView implements ITankSpatial{
    private Node vehicleNode;
    private Spatial tank;
    private Node rightFrontWheel;
    private Node leftFrontWheel;
    private Node rightBackWheel;
    private Node leftBackWheel;
    private float radius;

    public DefaultTankView(AssetManager assetManager) {
        tank = assetManager.loadModel("Models/tanken/tanken.j3o");
        tank.scale(3f);
              
        vehicleNode = (Node)tank;
        
        //Radius of wheels ?
        radius = 0.3f;

        Cylinder wheelMesh = new Cylinder(16, 16, radius, radius * 0.6f, true);
        
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", new ColorRGBA(0,0,0,0));
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        
        Node node1 = new Node("wheel 1 node");
        Geometry wheels1 = new Geometry("wheel 1", wheelMesh);
        node1.attachChild(wheels1);
        wheels1.rotate(0, FastMath.HALF_PI, 0);
        wheels1.setMaterial(mat);
        wheels1.setQueueBucket(RenderQueue.Bucket.Transparent);

        Node node2 = new Node("wheel 2 node");
        Geometry wheels2 = new Geometry("wheel 2", wheelMesh);
        wheels2.setQueueBucket(RenderQueue.Bucket.Transparent);
        node2.attachChild(wheels2);
        wheels2.rotate(0, FastMath.HALF_PI, 0);
        wheels2.setMaterial(mat);

        Node node3 = new Node("wheel 3 node");
        Geometry wheels3 = new Geometry("wheel 3", wheelMesh);
        wheels3.setQueueBucket(RenderQueue.Bucket.Transparent);
        node3.attachChild(wheels3);
        wheels3.rotate(0, FastMath.HALF_PI, 0);
        wheels3.setMaterial(mat);

        Node node4 = new Node("wheel 4 node");
        Geometry wheels4 = new Geometry("wheel 4", wheelMesh);
        wheels4.setQueueBucket(RenderQueue.Bucket.Transparent);
        node4.attachChild(wheels4);
        wheels4.rotate(0, FastMath.HALF_PI, 0);
        wheels4.setMaterial(mat);

        vehicleNode.attachChild(node1);
        vehicleNode.attachChild(node2);
        vehicleNode.attachChild(node3);
        vehicleNode.attachChild(node4);
    }
    
    public Node getVehicleNode() {
        return vehicleNode;
    }

    public Spatial getSpatial() {
        return tank;
    }

    public Node getRightFrontWheel() {
        return rightFrontWheel;
    }

    public Node getLeftFrontWheel() {
        return leftFrontWheel;
    }

    public Node getRightBackWheel() {
        return rightBackWheel;
    }

    public Node getLeftBackWheel() {
        return leftBackWheel;
    }
}
