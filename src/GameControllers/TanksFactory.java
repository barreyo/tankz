package GameControllers;

import GameControllers.logic.ViewPortManager;
import GameModel.IPlayer;
import GameModel.IExplodingProjectile;
import GameModel.CanonBallModel;
import GameModel.TankModel;
import App.TanksAppAdapter;
import GameControllers.entitycontrols.HomingProjectileControl;
import GameControllers.entitycontrols.LinearProjectileControl;
import GameControllers.entitycontrols.PowerupControl;
import GameControllers.entitycontrols.TanksVehicleControl;
import GameControllers.logic.GameAppState;
import GameModel.GameSettings;
import GameModel.ITanks;
import GameModel.TanksGameModel;
import GameModel.Player;
import GameModel.HastePowerup;
import GameModel.IPowerup;
import GameModel.IArmedVehicle;
import GameModel.ISpawningPoint;
import GameModel.MissileModel;
import GameModel.MissilePowerup;
import GameModel.SpawningPoint;
import GameUtilities.Util;
import GameView.GUI.HealthView;
import GameView.GUI.PowerupSlotView;
import GameView.GUI.ScoreboardView;
import GameView.GUI.TimerView;
import GameView.Map.GameWorld1;
import GameView.Map.IGameWorld;
import GameView.gameEntity.CanonBallEntity;
import GameView.gameEntity.MissileEntity;
import GameView.gameEntity.PowerupEntity;
import GameView.gameEntity.TankEntity;
import GameView.viewPort.VehicleCamera;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Manages controls.
 *
 * @author Daniel
 */
public final class TanksFactory {

    private TanksFactory() {
    }

    public static CanonBallModel createNewCanonBall(Vector3f position, Vector3f direction, Quaternion rotation) {
        CanonBallModel projectileModel = new CanonBallModel(position, direction, rotation);

        CanonBallEntity projectileEntity = new CanonBallEntity(projectileModel);

        LinearProjectileControl control = new LinearProjectileControl(projectileEntity, projectileModel);

        control.setCcdMotionThreshold(0.1f);
        control.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_01);
        control.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_01 | PhysicsCollisionObject.COLLISION_GROUP_02);
        //control.setKinematic(true);

        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(control);
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(control);

        projectileEntity.addControl(control);
        return projectileModel;
    }
    
    public static void createNewMissile(Vector3f position, Vector3f direction, Quaternion rotation, TanksVehicleControl sender) {
        MissileModel projectileModel = new MissileModel(position, direction, rotation);

        MissileEntity projectileEntity = new MissileEntity(projectileModel);

        HomingProjectileControl control = new HomingProjectileControl(projectileEntity, projectileModel, sender);

        control.setCcdMotionThreshold(0.1f);
        control.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_01);
        control.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_01 | PhysicsCollisionObject.COLLISION_GROUP_02);
        //control.setKinematic(true);

        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(control);
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(control);

        projectileEntity.addControl(control);
    }

    private static List<IPowerup> getNewPowerups(List<ISpawningPoint> spawns, List<IPlayer> players) {
        List<IPowerup> tmp = new ArrayList<IPowerup>();
        for (int i = 1; i < spawns.size() + players.size() + 1; i++) {
            if (i%2 == 1) {
                tmp.add(getNewHastePowerup());
            } else {
                tmp.add(getNewMissilePowerup());
            }
        }
        return tmp;
    }
    
    private static HastePowerup getNewHastePowerup() {
        HastePowerup model = new HastePowerup();
        PowerupEntity view = new PowerupEntity(model);
        PowerupControl control = new PowerupControl(view, model);

        control.setKinematic(true);

        view.addControl(control);
        return model;
    }
    
    private static MissilePowerup getNewMissilePowerup() {
        MissilePowerup model = new MissilePowerup();
        PowerupEntity view = new PowerupEntity(model);
        PowerupControl control = new PowerupControl(view, model);

        control.setKinematic(true);

        view.addControl(control);
        return model;
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

    public static GameAppState getNewGame(int intWorld, Collection<String> playerNames) {

        GameSettings settings = new GameSettings(100f, 10);

        int numberOfPlayers = playerNames.size();
        List<IPlayer> players = new ArrayList<IPlayer>();
        
        // Create one player for each name
        for (String name : playerNames) {
            // Create one vehicleModel per player
            IArmedVehicle vehicleModel = new TankModel();
            Player player = new Player(name, vehicleModel);

            // Set up vehicle
            TankEntity entity = new TankEntity(vehicleModel);

            Node carNode = (Node) entity.getSpatial();

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
            
            // Get the right viewport for the player and enable it
            ViewPort viewPort = ViewPortManager.INSTANCE.getViewportForPlayer(player.getName());
            viewPort.setEnabled(true);
            // Give the tank a refernce to the camera of the viewport
            vehicle.setCamera(viewPort.getCamera());
            
            vehicle.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_02);
            vehicle.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_01 | PhysicsCollisionObject.COLLISION_GROUP_02);

            // set up gui for each player
            PowerupSlotView pView = new PowerupSlotView(player,
                    ViewPortManager.INSTANCE.getViewportForPlayer(player.getName()), numberOfPlayers);
            HealthView v = new HealthView(player,
                    ViewPortManager.INSTANCE.getViewportForPlayer(player.getName()), numberOfPlayers);
            pView.show();
            v.show();

            players.add(player);
        }
        
        // Setting spawningpoints, different on each map
        List<ISpawningPoint> playerSpawningPoints = new ArrayList<ISpawningPoint>();
        List<ISpawningPoint> powerupSpawningPoints = new ArrayList<ISpawningPoint>();
        List<IPowerup> powerups = new ArrayList<IPowerup>();
        
        switch (intWorld) {
            case 1:
                playerSpawningPoints.add(new SpawningPoint(new Vector3f(10, 3, 10)));
                playerSpawningPoints.add(new SpawningPoint(new Vector3f(-90, 3, 12)));
                playerSpawningPoints.add(new SpawningPoint(new Vector3f(30, 3, 10)));
                playerSpawningPoints.add(new SpawningPoint(new Vector3f(35, 3, 9)));

                powerupSpawningPoints.add(new SpawningPoint(new Vector3f(-90, 3, 7)));
                powerupSpawningPoints.add(new SpawningPoint(new Vector3f(80, 3, 7)));
                powerupSpawningPoints.add(new SpawningPoint(new Vector3f(8, 3, 7)));
                powerupSpawningPoints.add(new SpawningPoint(new Vector3f(20, 3, 20)));
                
            default:
                playerSpawningPoints.add(new SpawningPoint(new Vector3f(10, 3, 10)));
                playerSpawningPoints.add(new SpawningPoint(new Vector3f(-90, 3, 12)));
                playerSpawningPoints.add(new SpawningPoint(new Vector3f(30, 3, 10)));
                playerSpawningPoints.add(new SpawningPoint(new Vector3f(35, 3, 9)));

                powerupSpawningPoints.add(new SpawningPoint(new Vector3f(-90, 3, 7)));
                powerupSpawningPoints.add(new SpawningPoint(new Vector3f(80, 3, 7)));
                powerupSpawningPoints.add(new SpawningPoint(new Vector3f(8, 3, 7)));
                powerupSpawningPoints.add(new SpawningPoint(new Vector3f(20, 3, 20)));

        }
        powerups = TanksFactory.getNewPowerups(powerupSpawningPoints, players);
        
        // Creating model and view of the game, view depending on which map it is
        ITanks game = new TanksGameModel(players, powerups, powerupSpawningPoints, playerSpawningPoints, settings);
        IGameWorld gameWorld = null;
        
        switch (intWorld) {
            case 1:
                gameWorld = new GameWorld1(game);
                break;
            default:
                gameWorld = new GameWorld1(game);
                break;
        }

        // set up timerView
        TimerView timerView = new TimerView(game);
        timerView.show();
        
        ScoreboardView sbv = new ScoreboardView(ViewPortManager.INSTANCE.getViewportForPlayer(players.get(0).getName()), players.size());
//        sbv.show();
        
        return new GameAppState(game, gameWorld);
    }
}
