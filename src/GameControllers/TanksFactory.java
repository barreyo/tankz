
package GameControllers;

import GameControllers.logic.ViewPortManager;
import GameModel.Player.IPlayer;
import GameModel.gameEntity.Projectile.IExplodingProjectile;
import GameModel.gameEntity.Projectile.MissileModel;
import GameModel.gameEntity.Vehicle.TankModel;
import App.TanksAppAdapter;
import GameControllers.entitycontrols.MissileControl;
import GameControllers.entitycontrols.PowerupControl;
import GameControllers.entitycontrols.TanksVehicleControl;
import GameControllers.logic.GameAppState;
import GameModel.Game.ITanks;
import GameModel.Game.TanksGameModel;
import GameModel.Game.ApplicationSettings;
import GameModel.gameEntity.Powerup.HastePowerup;
import GameModel.gameEntity.Powerup.IPowerup;
import GameUtilities.Util;
import GameView.Map.GameWorld1;
import GameView.Map.IGameWorld;
import GameView.gameEntity.MissileProjectileEntity;
import GameView.gameEntity.PowerupEntity;
import GameView.gameEntity.TankEntity;
import GameView.viewPort.VehicleCamera;
import com.jme3.bounding.BoundingBox;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.List;

/**
 * Manages controls.
 * 
 * @author Daniel
 */
public final class TanksFactory {
    
    private TanksFactory() {}
    
    public static void createTank(IPlayer player, Vector3f startPos) {
        // Create a tank for each player
        TankEntity entity = new TankEntity(player.getVehicle());
        // Move to startpos
        entity.setPosition(startPos);
        
        Node carNode = (Node)entity.getSpatial();

        TanksVehicleControl vehicle = new TanksVehicleControl(entity, player);
        vehicle.setSuspensionCompression(TankModel.TANK_COMP_VALUE * 2.0f
                                * FastMath.sqrt(TankModel.TANK_STIFFNESS));
        vehicle.setSuspensionDamping(TankModel.TANK_DAMP_VALUE * 2.0f 
                                * FastMath.sqrt(TankModel.TANK_STIFFNESS));
        vehicle.setSuspensionStiffness(TankModel.TANK_STIFFNESS);
        vehicle.setMaxSuspensionForce(TankModel.TANK_MAX_SUSPENSION_FORCE);
        
        Geometry wheel_fr = Util.findGeom(carNode, "WheelFrontRight");
        wheel_fr.center();
        BoundingBox box = (BoundingBox) wheel_fr.getModelBound();
        float wheelRadius = box.getYExtent();
        vehicle.addWheel(wheel_fr.getParent(), new Vector3f(TankModel.TANK_WHEEL_X_OFF,
                TankModel.TANK_WHEEL_Y_OFF, TankModel.TANK_WHEEL_Z_OFF),
                TankModel.TANK_WHEEL_DIRECTION, TankModel.TANK_WHEEL_AXIS, 
                TankModel.TANK_WHEEL_REST_LENGTH, wheelRadius, true);

        Geometry wheel_fl = Util.findGeom(carNode, "WheelFrontLeft");
        wheel_fl.center();
        box = (BoundingBox) wheel_fl.getModelBound();
        vehicle.addWheel(wheel_fl.getParent(), new Vector3f(-TankModel.TANK_WHEEL_X_OFF,
                TankModel.TANK_WHEEL_Y_OFF, TankModel.TANK_WHEEL_Z_OFF),
                TankModel.TANK_WHEEL_DIRECTION, TankModel.TANK_WHEEL_AXIS, 
                TankModel.TANK_WHEEL_REST_LENGTH, wheelRadius, true);

        Geometry wheel_br = Util.findGeom(carNode, "WheelBackRight");
        wheel_br.center();
        box = (BoundingBox) wheel_br.getModelBound();
        vehicle.addWheel(wheel_br.getParent(), new Vector3f(TankModel.TANK_WHEEL_X_OFF,
                TankModel.TANK_WHEEL_Y_OFF, -TankModel.TANK_WHEEL_Z_OFF),
                TankModel.TANK_WHEEL_DIRECTION, TankModel.TANK_WHEEL_AXIS, 
                TankModel.TANK_WHEEL_REST_LENGTH, wheelRadius, false);

        Geometry wheel_bl = Util.findGeom(carNode, "WheelBackLeft");
        wheel_bl.center();
        box = (BoundingBox) wheel_bl.getModelBound();
        vehicle.addWheel(wheel_bl.getParent(), new Vector3f(-TankModel.TANK_WHEEL_X_OFF,
                TankModel.TANK_WHEEL_Y_OFF, -TankModel.TANK_WHEEL_Z_OFF),
                TankModel.TANK_WHEEL_DIRECTION, TankModel.TANK_WHEEL_AXIS, 
                TankModel.TANK_WHEEL_REST_LENGTH, wheelRadius, false);
        entity.addControl(vehicle);
        
        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(vehicle);
        
        // Get the right viewport for the player and enable it
        ViewPort viewPort = ViewPortManager.INSTANCE.getViewportForPlayer(player);
        viewPort.setEnabled(true);
        // Give the tank a refernce to the camera of the viewport
        vehicle.setCamera(viewPort.getCamera());
    }
    
    public static void createNewMissile(Vector3f position, Vector3f direction, Quaternion rotation) {
        IExplodingProjectile projectileModel = new MissileModel(position, direction, rotation);
        
        MissileProjectileEntity projectileEntity = new MissileProjectileEntity(projectileModel);
        
        MissileControl control = new MissileControl(projectileEntity, projectileModel);
        
        control.setCcdMotionThreshold(0.1f);
        control.setKinematic(true);
        
        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(control);
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(control);
        
        projectileEntity.addControl(control);
    }
    
    public static void createNewPowerup(Vector3f position) {
        IPowerup model = new HastePowerup();
        model.setPosition(position);
        PowerupEntity view = new PowerupEntity(model);
        PowerupControl control = new PowerupControl(view, model);
        
        control.setKinematic(true);

        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(control);
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(control);
        
        view.addControl(control);
    }
    
    public static VehicleCamera getVehicleChaseCamera(Camera cam, Spatial spatial) {
        VehicleCamera chaseCam = new VehicleCamera(cam, spatial, TanksAppAdapter.INSTANCE.getInputManager());
        chaseCam.setMaxDistance(25);
        chaseCam.setMinDistance(15);
        chaseCam.setDefaultDistance(20);
        chaseCam.setChasingSensitivity(50f);
        chaseCam.setSmoothMotion(true); //automatic following
        chaseCam.setUpVector(Vector3f.UNIT_Y);
        chaseCam.setTrailingEnabled(true);
        chaseCam.setDefaultVerticalRotation(0.3f);
        return chaseCam;
    }
    
    public static GameAppState getNewGame(int intWorld) {
        List<IPlayer> players = ApplicationSettings.INSTANCE.getPlayers();
        ITanks game = new TanksGameModel(players);
        IGameWorld gameWorld = null;
        switch (intWorld) {
            case 1:
                gameWorld = new GameWorld1(game);
                break;
            default: 
                gameWorld = new GameWorld1(game);
                break;
        }
        return new GameAppState(game, gameWorld);
    }
}
