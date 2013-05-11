
package GameControllers.entitycontrols;

import GameControllers.TanksFactory;
import GameControllers.logic.SoundManager;
import GameModel.EApplicationState;
import GameModel.EPlayerInputs;
import GameModel.IPlayer;
import GameView.viewPort.VehicleCamera;
import GameModel.IArmedVehicle;
import App.TanksAppAdapter;
import GameModel.IExplodingProjectile;
import GameModel.IPowerup;
import GameModel.IWorldObject;
import GameUtilities.Commands;
import GameView.Sounds.ESounds;
import GameView.gameEntity.TankEntity;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *  A control of a tank vehicle.
 * 
 * Extends JMonkey predefined VehicleControl class.
 * 
 * @author Daniel
 */
public class TanksVehicleControl extends VehicleControl implements ActionListener, PhysicsCollisionListener, PropertyChangeListener {
    
    // The model for the vehicle
    private IArmedVehicle vehicleModel;
    // View of the vehicle
    private TankEntity tankEntity;
    // The player controlling
    private IPlayer player;
    
    // Cam to be set up behind Vehicle
    private VehicleCamera chaseCam;
 
    /**
     * Creates a control for a tank vehicle.
     * @param entity 
     * @param player 
     */
    public TanksVehicleControl(TankEntity entity, IPlayer player) {  
        super(entity.getCollisionShape(), player.getVehicle().getMass());
        // Save references to model, view and player
        this.tankEntity = entity;
        this.vehicleModel = player.getVehicle();
        this.player = player;
        // Register input mappings
        addInputMappings();
        
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(this);
        this.setEnabled(false);
        // Observe view
        entity.addObserver(this);
    }

    /*
     * @inheritdoc
     */
    @Override
    public void update(float tpf) {
        if (!enabled || EApplicationState.getGameState() != EApplicationState.RUNNING) {
            return;
        }
        super.update(tpf);
        
        vehicleModel.updateCurrentVehicleSpeedKmHour(this.getCurrentVehicleSpeedKmHour());
        vehicleModel.setPosition(spatial.getWorldTranslation());
        vehicleModel.updateDirection(this.getForwardVector(null));
        vehicleModel.updateRotation(spatial.getWorldRotation());
        vehicleModel.update(tpf);

        if (chaseCam != null) {
            chaseCam.setHorizonalLookAt(vehicleModel.getDirection().multLocal(new Vector3f(1, 0, 1)));
        }
    }

    /**
     * Sets the camera that will be used to follow the tank.
     * 
     * @param cam the camera that will follow the tank
     */
    public void setCamera(Camera cam) {
        setUpCam(cam);
    }
    
    /**
     * Initiates the third person camera that follows the vehicle.
     *
     * @param spatial The spatial to be followed by the camera.
     */
    private void setUpCam(Camera cam) {
        chaseCam = TanksFactory.getVehicleChaseCamera(cam, spatial);
    }

    /**
     * @inheritdoc
     */
    public void cleanup() {
        // Remove this as a control and remove inputs
        tankEntity.removeControl(this);
        tankEntity.removeObserver(this);
        removeInputMappings();
    }
    
    // Input related commands
    private EPlayerInputs inputs;
    private String turnLeft;
    private String turnRight;
    private String accelerateForward;
    private String accelerateBack;
    private String reset;
    private String shoot;
    private String powerup;
    private String scoreboard;

    private boolean isFirstLeftKeyPressDone;
    private boolean isFirstRightKeyPressDone;
    private boolean isFirstUpKeyPressDone;
    private boolean isFirstDownKeyPressDone;
    
    /**
     * @inheritdoc
     * 
     * Calls for the method in the model, according to the button pressed.
     */
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (!enabled || EApplicationState.getGameState() != EApplicationState.RUNNING) {
            return;
        }
        // Steering related
        if (name.equals(turnLeft)) {
            if (isPressed) {
                if (!isFirstLeftKeyPressDone) {
                    isFirstLeftKeyPressDone = true;
                }
                vehicleModel.steerLeft();
            } else {
                if (!isFirstLeftKeyPressDone) {
                    return;
                }
                vehicleModel.steerRight();
            }
        } else if (name.equals(turnRight)) {
            if (isPressed) {
                if (!isFirstRightKeyPressDone) {
                    isFirstRightKeyPressDone = true;
                }
                vehicleModel.steerRight();
            } else {
                if (!isFirstRightKeyPressDone) {
                    return;
                }
                vehicleModel.steerLeft();
            }
        } else if (name.equals(accelerateForward)) {
            if (isPressed) {
                if (!isFirstUpKeyPressDone) {
                    isFirstUpKeyPressDone = true;
                }
                vehicleModel.accelerateForward();     
            } else {
                if (!isFirstUpKeyPressDone) {
                    return;
                }
                vehicleModel.applyFriction();
                vehicleModel.accelerateBack();
            }
        } else if (name.equals(accelerateBack)) {
            if (isPressed) {
                if (!isFirstDownKeyPressDone) {
                    isFirstDownKeyPressDone = true;
                }
                vehicleModel.accelerateBack();
            } else {
                if (!isFirstDownKeyPressDone) {
                    return;
                }
                vehicleModel.applyFriction();
                vehicleModel.accelerateForward();
            }
        } else if (name.equals(reset)) {
            if (isPressed) {
                this.setPhysicsLocation(Vector3f.ZERO);
                this.setPhysicsRotation(new Matrix3f());
                this.setLinearVelocity(Vector3f.ZERO);
                this.setAngularVelocity(Vector3f.ZERO);
                this.resetSuspension();
            }
        } else if (name.equals(shoot)) {
            if (!isPressed) {
                vehicleModel.shoot(player);
            }
        } else if (name.equals(powerup)) {
            if (!isPressed) {
                player.usePowerup();
            }
        } else if (name.equals(scoreboard)) {
            if (isPressed) {
                player.showScoreboard();
            } else {
                player.hideScoreboard();
            }
        }
    }

    /**
     * Adds inputmappings for this control, depends on what the variables are set to.
     */
    private void addInputMappings() {
        // Looks for unused input mappings
        for (EPlayerInputs playerInputs : EPlayerInputs.values()) {
            if (!playerInputs.isInUse()) {
                inputs = playerInputs;
                break;
            }
        }
        if (inputs == null) {
            return;
        }
        
        // Gets input keycodes
        int left = inputs.getLeftKey();
        int right = inputs.getRightKey();
        int up = inputs.getUpKey();
        int down = inputs.getDownKey();
        int resetI = inputs.getResetKey();
        int shootI = inputs.getShootKey();
        int powerupI = inputs.getPowerupKey();
        int scoreboardI = inputs.getScoreboardKey();
        
        // Specifies mappingnames for input
        turnLeft = "" + left;
        turnRight = "" + right;
        accelerateForward = "" + up;
        accelerateBack = "" + down;
        reset = "" + resetI;
        shoot = "" + shootI;
        powerup = "" + powerupI;
        scoreboard = "" + scoreboardI;
        
        // Adds the mappings to inputmanager
        TanksAppAdapter.INSTANCE.addInputMapping(turnLeft, new KeyTrigger(left));
        TanksAppAdapter.INSTANCE.addInputMapping(turnRight, new KeyTrigger(right));
        TanksAppAdapter.INSTANCE.addInputMapping(accelerateForward, new KeyTrigger(up));
        TanksAppAdapter.INSTANCE.addInputMapping(accelerateBack, new KeyTrigger(down));
        TanksAppAdapter.INSTANCE.addInputMapping(reset, new KeyTrigger(resetI));
        TanksAppAdapter.INSTANCE.addInputMapping(shoot, new KeyTrigger(shootI));
        TanksAppAdapter.INSTANCE.addInputMapping(powerup, new KeyTrigger(powerupI));
        TanksAppAdapter.INSTANCE.addInputMapping(scoreboard, new KeyTrigger(scoreboardI));
        // Registers this as an listener for the specified mappingnames
        TanksAppAdapter.INSTANCE.addInputListener(this, turnLeft, turnRight, 
                accelerateForward, accelerateBack, reset, shoot, powerup, scoreboard);
        
        // These mappings are now in use and cant be used by other players
        inputs.setInUse(true);
    }
    
    /**
     * Removes the inputmappings for this control.
     */
    private void removeInputMappings() {
        if (inputs == null) {
            return;
        }
        TanksAppAdapter.INSTANCE.deleteInputMapping(turnLeft);
        TanksAppAdapter.INSTANCE.deleteInputMapping(turnRight);
        TanksAppAdapter.INSTANCE.deleteInputMapping(accelerateForward);
        TanksAppAdapter.INSTANCE.deleteInputMapping(accelerateBack);
        TanksAppAdapter.INSTANCE.deleteInputMapping(reset);
        TanksAppAdapter.INSTANCE.deleteInputMapping(shoot);
        TanksAppAdapter.INSTANCE.deleteInputMapping(powerup);
        TanksAppAdapter.INSTANCE.deleteInputMapping(scoreboard);
        TanksAppAdapter.INSTANCE.removeInputListener(this);
        inputs.setInUse(false);
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        String command = evt.getPropertyName();
        Object source = evt.getSource();
        if (source == vehicleModel) {
            if (command.equals(Commands.STEER)) {
                // Steer the vehicle according to the model
                this.steer((Float)evt.getNewValue());
            } else if (command.equals(Commands.ACCELERATE)) {
                // Accelerate the vehicle accordning to the model
                this.accelerate((Float)evt.getNewValue());
            } else if (command.equals(Commands.FRICTION)) {
                // Brake the vehicle according to the friction passed by model
                this.brake((Float)evt.getNewValue());
            } else if (command.equals(Commands.SHOW)) {
                this.setEnabled(true); 
                isFirstLeftKeyPressDone = false;
                isFirstRightKeyPressDone = false;
                isFirstUpKeyPressDone = false;
                isFirstDownKeyPressDone = false;
                this.setPhysicsLocation(vehicleModel.getPosition());
            } else if (command.equals(Commands.HIDE)) {
                this.setPhysicsRotation(new Matrix3f());
                this.setLinearVelocity(Vector3f.ZERO);
                this.setAngularVelocity(Vector3f.ZERO);
                this.resetSuspension();
                this.setEnabled(false);                      
            } else if (command.equals(Commands.CLEANUP)) {
                this.cleanup();
            }
        }
    }

    /**
     *
     * @param event
     */
    @Override
    public void collision(PhysicsCollisionEvent event) {
        if (event.getNodeA() != null && event.getNodeB() != null) {
            IWorldObject objA = event.getNodeA().getUserData("Model");
            IWorldObject objB = event.getNodeB().getUserData("Model");
            if (objA == vehicleModel) {
                if (objB instanceof IPowerup) {
                    player.setPowerup((IPowerup) objB);
                } 
            } else if (objB == vehicleModel) {
                if (objA instanceof IPowerup) {
                    player.setPowerup((IPowerup) objB);
                }
            }
        }
    }
}
