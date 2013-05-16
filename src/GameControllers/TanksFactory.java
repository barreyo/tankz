package GameControllers;

import GameControllers.logic.ViewPortManager;
import GameModel.IPlayer;
import GameModel.CanonBallModel;
import App.TanksAppAdapter;
import GameControllers.entitycontrols.HomingProjectileControl;
import GameControllers.entitycontrols.LandmineControl;
import GameControllers.entitycontrols.LinearProjectileControl;
import GameControllers.entitycontrols.PowerupControl;
import GameControllers.entitycontrols.TanksVehicleControl;
import GameControllers.logic.GameAppState;
import GameModel.AirCallPowerup;
import GameModel.AtomicBombModel;
import GameModel.BeerPowerup;
import GameModel.GameSettings;
import GameModel.ITanks;
import GameModel.TanksGameModel;
import GameModel.Player;
import GameModel.HastePowerup;
import GameModel.HealthPowerup;
import GameModel.IPowerup;
import GameModel.IArmedVehicle;
import GameModel.IExplodingProjectile;
import GameModel.ISpawningPoint;
import GameModel.LandmineModel;
import GameModel.LandminePowerup;
import GameModel.MissileModel;
import GameModel.MissilePowerup;
import GameModel.SpawningPoint;
import GameModel.TankModel;
import GameUtilities.Constants;
import GameUtilities.Util;
import GameView.GUI.HealthView;
import GameView.GUI.PowerupSlotView;
import GameView.GUI.ScoreboardView;
import GameView.GUI.TimerView;
import GameView.Map.GameWorld1;
import GameView.Map.IGameWorld;
import GameView.gameEntity.CanonBallEntity;
import GameView.gameEntity.LandmineEntity;
import GameView.gameEntity.MissileEntity;
import GameView.gameEntity.NukeEntity;
import GameView.gameEntity.PowerupEntity;
import GameView.gameEntity.TankEntity;
import GameView.physics.ECollisionShapes;
import GameView.viewPort.VehicleCamera;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages controls.
 *
 * @author Daniel
 */
public final class TanksFactory {

    private TanksFactory() {
    }

    private static CanonBallModel getNewCanonBall() {
        CanonBallModel projectileModel = new CanonBallModel();

        CanonBallEntity projectileEntity = new CanonBallEntity(projectileModel);

        RigidBodyControl physicsControl = new RigidBodyControl(projectileEntity.getCollisionShape(), projectileModel.getMass());
        physicsControl.setCcdMotionThreshold(0.1f);
        physicsControl.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_01);
        physicsControl.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_01
                | PhysicsCollisionObject.COLLISION_GROUP_02
                | PhysicsCollisionObject.COLLISION_GROUP_03
                | PhysicsCollisionObject.COLLISION_GROUP_04
                | PhysicsCollisionObject.COLLISION_GROUP_05);

        LinearProjectileControl control = new LinearProjectileControl(projectileEntity, projectileModel, physicsControl);

        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(control);

        projectileEntity.addControl(control);
        return projectileModel;
    }
    
    private static AtomicBombModel getNewAtomicBomb() {
        AtomicBombModel projectileModel = new AtomicBombModel();

        NukeEntity projectileEntity = new NukeEntity(projectileModel);

        RigidBodyControl physicsControl = new RigidBodyControl(ECollisionShapes.NUKE_PROJECTILE.createCollisionShape(), projectileModel.getMass());
        physicsControl.setCcdMotionThreshold(0.1f);
        physicsControl.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_01);
        physicsControl.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_01
                | PhysicsCollisionObject.COLLISION_GROUP_02
                | PhysicsCollisionObject.COLLISION_GROUP_03
                | PhysicsCollisionObject.COLLISION_GROUP_04
                | PhysicsCollisionObject.COLLISION_GROUP_05);

        LinearProjectileControl control = new LinearProjectileControl(projectileEntity, projectileModel, physicsControl);

        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(control);

        projectileEntity.addControl(control);
        return projectileModel;
    }
    
    private static LandmineModel getNewLandmine() {
        LandmineModel landmine = new LandmineModel();

        LandmineEntity landmineEntity = new LandmineEntity(landmine);

        RigidBodyControl physicsControl = new RigidBodyControl(landmineEntity.getCollisionShape(), landmine.getMass());
        physicsControl.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_01);
        physicsControl.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_02
                | PhysicsCollisionObject.COLLISION_GROUP_03
                | PhysicsCollisionObject.COLLISION_GROUP_04
                | PhysicsCollisionObject.COLLISION_GROUP_05);

        LandmineControl control = new LandmineControl(landmine, landmineEntity, physicsControl);

        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(control);

        landmineEntity.addControl(control);
        return landmine;
    }

    private static MissileModel getNewMissile(int senderCollisionGroupMask) {
        MissileModel projectileModel = new MissileModel();

        MissileEntity projectileEntity = new MissileEntity(projectileModel);

        RigidBodyControl physicsControl = new RigidBodyControl(projectileEntity.getCollisionShape(), projectileModel.getMass());
        physicsControl.setCcdMotionThreshold(0.1f);
        physicsControl.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_01);
        physicsControl.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_01
                | PhysicsCollisionObject.COLLISION_GROUP_02
                | PhysicsCollisionObject.COLLISION_GROUP_03
                | PhysicsCollisionObject.COLLISION_GROUP_04
                | PhysicsCollisionObject.COLLISION_GROUP_05);

        GhostControl aggroGhost = new GhostControl(new SphereCollisionShape(200));
        aggroGhost.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_10);
        // Collide with all tanks except the senders --> aggro only other tanks.
        aggroGhost.setCollideWithGroups((PhysicsCollisionObject.COLLISION_GROUP_02
                | PhysicsCollisionObject.COLLISION_GROUP_03
                | PhysicsCollisionObject.COLLISION_GROUP_04
                | PhysicsCollisionObject.COLLISION_GROUP_05) & ~senderCollisionGroupMask);

        HomingProjectileControl control = new HomingProjectileControl(projectileEntity, projectileModel, physicsControl, aggroGhost);

        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(control);

        projectileEntity.addControl(control);
        return projectileModel;
    }

    private static List<IPowerup> getNewPowerups(List<ISpawningPoint> spawns, List<IPlayer> players) {
        List<IPowerup> tmp = new ArrayList<IPowerup>();
        for (int i = 0; i < 100; i++) {
            tmp.add(getNewPowerup(HastePowerup.class));
            tmp.add(getNewPowerup(MissilePowerup.class));
            tmp.add(getNewPowerup(LandminePowerup.class));
            tmp.add(getNewBeerPowerup(players));
            tmp.add(getNewPowerup(HealthPowerup.class));
            if (i > 5) {
                tmp.add(getNewAirCallPowerup());
            }
        }
        return tmp;
    }
     
    private static BeerPowerup getNewBeerPowerup(List<IPlayer> players) {
        BeerPowerup model = new BeerPowerup(players);
        PowerupEntity view = new PowerupEntity(model);
        RigidBodyControl physicsControl = new RigidBodyControl(view.getCollisionShape(), model.getMass());
        physicsControl.setKinematic(true);
        physicsControl.setCollideWithGroups((PhysicsCollisionObject.COLLISION_GROUP_02
                | PhysicsCollisionObject.COLLISION_GROUP_03
                | PhysicsCollisionObject.COLLISION_GROUP_04
                | PhysicsCollisionObject.COLLISION_GROUP_05));

        PowerupControl control = new PowerupControl(view, model, physicsControl);

        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(control);

        view.addControl(control);
        view.addControl(physicsControl);
        return model;
    }
    
    private static AirCallPowerup getNewAirCallPowerup() {
        List<IExplodingProjectile> balls = new ArrayList<IExplodingProjectile>();
        for (int i = 0; i < Constants.BOMBS_IN_AIRCALL; i++) {
            balls.add(getNewAtomicBomb());
         }
        AirCallPowerup model = new AirCallPowerup(balls);
        
        PowerupEntity view = new PowerupEntity(model);
        RigidBodyControl physicsControl = new RigidBodyControl(view.getCollisionShape(), model.getMass());
        physicsControl.setKinematic(true);
        physicsControl.setCollideWithGroups((PhysicsCollisionObject.COLLISION_GROUP_02
                | PhysicsCollisionObject.COLLISION_GROUP_03
                | PhysicsCollisionObject.COLLISION_GROUP_04
                | PhysicsCollisionObject.COLLISION_GROUP_05));

        PowerupControl control = new PowerupControl(view, model, physicsControl);

        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(control);

        view.addControl(control);
        view.addControl(physicsControl);
        return model;
    }
    
    private static IPowerup getNewPowerup(Class<? extends IPowerup> powerupClass) {
        IPowerup model = null;
        try {
            model = powerupClass.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(TanksFactory.class.getName()).log(Level.SEVERE, "Unable to instansiate IPowerup", ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TanksFactory.class.getName()).log(Level.SEVERE, "Do not have access to IPowerup", ex);
        }
        PowerupEntity view = new PowerupEntity(model);
        RigidBodyControl physicsControl = new RigidBodyControl(view.getCollisionShape(), model.getMass());
        physicsControl.setKinematic(true);
        physicsControl.setCollideWithGroups((PhysicsCollisionObject.COLLISION_GROUP_02
                | PhysicsCollisionObject.COLLISION_GROUP_03
                | PhysicsCollisionObject.COLLISION_GROUP_04
                | PhysicsCollisionObject.COLLISION_GROUP_05));

        PowerupControl control = new PowerupControl(view, model, physicsControl);

        TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(control);

        view.addControl(control);
        view.addControl(physicsControl);
        return model;
    }


    /**
     *
     * @param cam
     * @param spatial
     * @return
     */
    public static VehicleCamera getVehicleChaseCamera(Camera cam, Spatial spatial) {
        VehicleCamera chaseCam = new VehicleCamera(cam, spatial, TanksAppAdapter.INSTANCE.getInputManager());
        chaseCam.setMaxDistance(Constants.CAM_MAX_DISTANCE);
        chaseCam.setMinDistance(Constants.CAM_MIN_DISTANCE);
        chaseCam.setDefaultDistance(Constants.CAM_DEFAULT_DISTANCE);
        chaseCam.setChasingSensitivity(Constants.CAM_CHASING_SENSITIVITY);
        chaseCam.setSmoothMotion(true); //automatic following
        chaseCam.setUpVector(Vector3f.UNIT_Y);
        chaseCam.setTrailingEnabled(true);
        chaseCam.setDefaultVerticalRotation(Constants.CAM_DEFAULT_VERTICAL_ROTATION);
        return chaseCam;
    }

    /**
     *
     * @param worldMapClass the visual world map to be instansiated and used as map for the game.
     * @param playerNames the names of the players to be created.
     */
    public static GameAppState getNewGame(Class<? extends IGameWorld> worldMapClass, Collection<String> playerNames) {

        GameSettings settings = new GameSettings(1200000, 10, 5000);

        int numberOfPlayers = playerNames.size();
        List<IPlayer> players = new ArrayList<IPlayer>();

        int playerNumber = 1;
        // Create one player for each name
        for (String name : playerNames) {

            int collisionGroup = (playerNumber == 1 ? PhysicsCollisionObject.COLLISION_GROUP_02
                    : playerNumber == 2 ? PhysicsCollisionObject.COLLISION_GROUP_03
                    : playerNumber == 3 ? PhysicsCollisionObject.COLLISION_GROUP_04
                    : PhysicsCollisionObject.COLLISION_GROUP_05);

            List<CanonBallModel> canonBalls = new ArrayList<CanonBallModel>();

            for (int i = 0; i < Constants.CANNONBALLS_PER_PLAYER; i++) {
                canonBalls.add(getNewCanonBall());
            }

            List<MissileModel> missiles = new ArrayList<MissileModel>();

            for (int i = 0; i < Constants.MISSILES_PER_PLAYER; i++) {
                missiles.add(getNewMissile(collisionGroup));
            }
            
            List<LandmineModel> landmines = new ArrayList<LandmineModel>();

            for (int i = 0; i < Constants.LANDMINES_PER_PLAYER; i++) {
                landmines.add(getNewLandmine());
            }

            // Create one vehicleModel per player
            IArmedVehicle vehicleModel = new TankModel(canonBalls, missiles, landmines);
            Player player = new Player(name, vehicleModel);

            // Set up vehicle
            TankEntity entity = new TankEntity(vehicleModel);

            Node carNode = (Node) entity.getSpatial();

            TanksVehicleControl vehicle = new TanksVehicleControl(entity, player);
            vehicle.setSuspensionCompression(Constants.TANK_COMP_VALUE * 2.0f
                    * FastMath.sqrt(Constants.TANK_STIFFNESS));
            vehicle.setSuspensionDamping(Constants.TANK_DAMP_VALUE * 2.0f
                    * FastMath.sqrt(Constants.TANK_STIFFNESS));
            vehicle.setSuspensionStiffness(Constants.TANK_STIFFNESS);
            vehicle.setMaxSuspensionForce(Constants.TANK_MAX_SUSPENSION_FORCE);

            Geometry wheel_fr = Util.findGeom(carNode, Constants.RIGHT_FRONT_WHEEL_MODEL_NAME);
            wheel_fr.center();
            BoundingBox box = (BoundingBox) wheel_fr.getModelBound();
            float wheelRadius = box.getYExtent();
            vehicle.addWheel(wheel_fr.getParent(), new Vector3f(Constants.TANK_WHEEL_X_OFF,
                    Constants.TANK_WHEEL_Y_OFF, Constants.TANK_WHEEL_Z_OFF),
                    Constants.TANK_WHEEL_DIRECTION, Constants.TANK_WHEEL_AXIS,
                    Constants.TANK_WHEEL_REST_LENGTH, wheelRadius, true);

            Geometry wheel_fl = Util.findGeom(carNode, Constants.LEFT_FRONT_WHEEL_MODEL_NAME);
            wheel_fl.center();
            box = (BoundingBox) wheel_fl.getModelBound();
            vehicle.addWheel(wheel_fl.getParent(), new Vector3f(-Constants.TANK_WHEEL_X_OFF,
                    Constants.TANK_WHEEL_Y_OFF, Constants.TANK_WHEEL_Z_OFF),
                    Constants.TANK_WHEEL_DIRECTION, Constants.TANK_WHEEL_AXIS,
                    Constants.TANK_WHEEL_REST_LENGTH, wheelRadius, true);

            Geometry wheel_br = Util.findGeom(carNode, Constants.RIGHT_BACK_WHEEL_MODEL_NAME);
            wheel_br.center();
            box = (BoundingBox) wheel_br.getModelBound();
            vehicle.addWheel(wheel_br.getParent(), new Vector3f(Constants.TANK_WHEEL_X_OFF,
                    Constants.TANK_WHEEL_Y_OFF, -Constants.TANK_WHEEL_Z_OFF),
                    Constants.TANK_WHEEL_DIRECTION, Constants.TANK_WHEEL_AXIS,
                    Constants.TANK_WHEEL_REST_LENGTH, wheelRadius, false);

            Geometry wheel_bl = Util.findGeom(carNode, Constants.LEFT_BACK_WHEEL_MODEL_NAME);
            wheel_bl.center();
            box = (BoundingBox) wheel_bl.getModelBound();
            vehicle.addWheel(wheel_bl.getParent(), new Vector3f(-Constants.TANK_WHEEL_X_OFF,
                    Constants.TANK_WHEEL_Y_OFF, -Constants.TANK_WHEEL_Z_OFF),
                    Constants.TANK_WHEEL_DIRECTION, Constants.TANK_WHEEL_AXIS,
                    Constants.TANK_WHEEL_REST_LENGTH, wheelRadius, false);

            vehicle.setCollisionGroup(collisionGroup);
            vehicle.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_01
                    | PhysicsCollisionObject.COLLISION_GROUP_02);

            TanksAppAdapter.INSTANCE.addPhysiscsCollisionListener(vehicle);

            entity.addControl(vehicle);

            // Get the right viewport for the player and enable it
            ViewPort viewPort = ViewPortManager.INSTANCE.getViewportForPlayer(player.getName());
            viewPort.setEnabled(true);
            // Give the tank a refernce to the camera of the viewport
            vehicle.setCamera(viewPort.getCamera());


            // set up gui for each player
            PowerupSlotView pView = new PowerupSlotView(player,
                    ViewPortManager.INSTANCE.getViewportForPlayer(player.getName()), numberOfPlayers);
            HealthView v = new HealthView(vehicleModel,
                    ViewPortManager.INSTANCE.getViewportForPlayer(player.getName()), numberOfPlayers);
            pView.show();
            v.show();

            players.add(player);
            playerNumber++;
        }

        // this can't be done in the loop above since the players list need to be fully filled.
        for (IPlayer p : players) {
            // set up scoreboard for each player
            new ScoreboardView(ViewPortManager.INSTANCE.getViewportForPlayer(p.getName()), players, p);
        }

        // Setting spawningpoints, different on each map
        List<ISpawningPoint> playerSpawningPoints = new ArrayList<ISpawningPoint>();
        playerSpawningPoints.add(new SpawningPoint(new Vector3f(10, 3, 10)));
        playerSpawningPoints.add(new SpawningPoint(new Vector3f(-90, 3, 12)));
        playerSpawningPoints.add(new SpawningPoint(new Vector3f(30, 3, 10)));
        playerSpawningPoints.add(new SpawningPoint(new Vector3f(35, 3, 9)));

        List<ISpawningPoint> powerupSpawningPoints = new ArrayList<ISpawningPoint>();
        powerupSpawningPoints.add(new SpawningPoint(new Vector3f(-90, 3, 7)));
        powerupSpawningPoints.add(new SpawningPoint(new Vector3f(80, 3, 7)));
        powerupSpawningPoints.add(new SpawningPoint(new Vector3f(8, 3, 7)));
        powerupSpawningPoints.add(new SpawningPoint(new Vector3f(20, 3, 20)));

        List<IPowerup> powerups = TanksFactory.getNewPowerups(powerupSpawningPoints, players);

        // Creating model and view of the game, view depending on which map it is
        ITanks game = new TanksGameModel(players, powerups, powerupSpawningPoints, playerSpawningPoints, settings);
        IGameWorld gameWorld = null;
        try {
            gameWorld = worldMapClass.getDeclaredConstructor(ITanks.class).newInstance(game);
        } catch (Exception ex) {
            Logger.getLogger(TanksFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        // set up timerView
        TimerView timerView = new TimerView(game);
        timerView.show();

        return new GameAppState(game, gameWorld);
    }
}
