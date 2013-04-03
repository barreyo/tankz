/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.controls;

import GameLogicLayer.Game.TanksGame;
import GameModelLayer.gameEntity.Vehicle.IArmedVehicle;
import GameViewLayer.gameEntity.EGameEntities;
import GameViewLayer.gameEntity.MissileProjectile;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Garpetun
 */
public class ShootingThread extends Thread {
    
    private VehicleControl vehicle;
    private Spatial spatial;
    private TanksGame app;
    private Node rootNode;
    
    public ShootingThread (VehicleControl vehicle, Spatial spatial, TanksGame app, Node rootNode) {
        this.vehicle = vehicle;
        this.spatial = spatial;
        this.app = app;
        this.rootNode = rootNode;
    }
    
    public void run() {
            MissileProjectile projectileEntity = (MissileProjectile) app.getEntityManager().create(EGameEntities.MISSILE_PROJECTILE);
            projectileEntity.setDirection(vehicle.getForwardVector(null));
            Spatial projectile = projectileEntity.getSpatial();
            projectile.setLocalTranslation(spatial.getWorldTranslation().addLocal(0, 1, 0).addLocal(vehicle.getForwardVector(null).multLocal(3f)));
            projectile.setLocalRotation(spatial.getWorldRotation());
            projectileEntity.finalise();
            // Attach to world and phsysicsSpace
            rootNode.attachChild(projectile);
    }
}
