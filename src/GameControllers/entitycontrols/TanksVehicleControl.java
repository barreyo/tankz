
package GameControllers.entitycontrols;

import GameControllers.TanksFactory;
import GameControllers.logic.SoundManager;
import GameModel.Game.EGameState;
import GameModel.Player.EPlayerInputs;
import GameModel.Player.IPlayer;
import GameView.viewPort.VehicleCamera;
import GameModel.gameEntity.Vehicle.IArmedVehicle;
import App.TanksAppAdapter;
import GameModel.gameEntity.Powerup.EPowerup;
import GameView.GUI.FloatingNameControl;
import GameView.Sounds.ESounds;
import GameView.gameEntity.IGameEntity;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;

/**
 *  A control of a tank vehicle.
 * 
 * Extends JMonkey predefined VehicleControl class.
 * 
 * @author Daniel
 */
public class TanksVehicleControl extends VehicleControl implements ActionListener {
    
    // The model for the vehicle
    private IArmedVehicle vehicleModel;
    // View of the vehicle
    private IGameEntity entity;
    // The player controlling
    private IPlayer player;
    
    // Cam to be set up behind Vehicle
    private VehicleCamera chaseCam;
 
    /**
     * Creates a control for a tank vehicle.
     */
    public TanksVehicleControl(IGameEntity entity, IPlayer player) {  
        super(entity.getCollisionShape(), player.getVehicle().getMass());
        
        // Save references to model, view and player
        this.entity = entity;
        this.vehicleModel = player.getVehicle();
        this.player = player;
        
        // Register input mappings
        addInputMappings();
    }

    /*
     * @inheritdoc
     */
    @Override
    public void update(float tpf) {
        if (!enabled || EGameState.getGameState() != EGameState.RUNNING) {
            return;
        }
        super.update(tpf);
        
        vehicleModel.updateCurrentVehicleSpeedKmHour(this.getCurrentVehicleSpeedKmHour());
        vehicleModel.update(tpf);
        
        // Accelerate the vehicle accordning to the model
        this.accelerate(vehicleModel.getAccelerationValue());

        if (chaseCam != null) {
            chaseCam.setHorizonalLookAt(this.getForwardVector(null).multLocal(new Vector3f(1, 0, 1)));
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        if (spatial != null) {
            initControl();
        }  
    }
    
    private void initControl() {
        addVehicleControl();
        // ?
        spatial.addControl(new FloatingNameControl(spatial,
                TanksAppAdapter.INSTANCE.getAssetManager()));
    }

    private void addVehicleControl() {
        // Add vehicle to physics space
        TanksAppAdapter.INSTANCE.addToPhysicsSpace(this);
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
        spatial.removeControl(this);
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

    private boolean isFirstLeftKeyPressDone;
    private boolean isFirstRightKeyPressDone;
    private boolean isFirstUpKeyPressDone;
    private boolean isFirstDownKeyPressDone;
    /**
     * @inheritdoc
     */
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (!enabled || EGameState.getGameState() != EGameState.RUNNING) {
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
            this.steer(vehicleModel.getSteeringValue());
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
            this.steer(vehicleModel.getSteeringValue());
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
                this.brake(vehicleModel.getFrictionForce());
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
                this.brake(vehicleModel.getFrictionForce());
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
                // Shoot by creating a new missile with the right direction, position and rotation
                TanksFactory.createNewMissile(spatial.getWorldTranslation().addLocal(0, 1, 0).addLocal(this.getForwardVector(null).multLocal(3f)),
                                this.getForwardVector(null), spatial.getWorldRotation());
                SoundManager.INSTANCE.play(ESounds.MISSILE_LAUNCH_SOUND);
            }
        } else if (name.equals(powerup)) {
            System.out.println("USED POWERUP BUTTON!");
            if (!isPressed) {
                player.usePowerup();
            }
        }
    }

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
        
        // Specifies mappingnames for input
        turnLeft = "" + left;
        turnRight = "" + right;
        accelerateForward = "" + up;
        accelerateBack = "" + down;
        reset = "" + resetI;
        shoot = "" + shootI;
        powerup = "" + powerupI;
        
        // Adds the mappings to inputmanager
        TanksAppAdapter.INSTANCE.addInputMapping(turnLeft, new KeyTrigger(left));
        TanksAppAdapter.INSTANCE.addInputMapping(turnRight, new KeyTrigger(right));
        TanksAppAdapter.INSTANCE.addInputMapping(accelerateForward, new KeyTrigger(up));
        TanksAppAdapter.INSTANCE.addInputMapping(accelerateBack, new KeyTrigger(down));
        TanksAppAdapter.INSTANCE.addInputMapping(reset, new KeyTrigger(resetI));
        TanksAppAdapter.INSTANCE.addInputMapping(shoot, new KeyTrigger(shootI));
        TanksAppAdapter.INSTANCE.addInputMapping(powerup, new KeyTrigger(powerupI));
        // Registers this as an listener for the specified mappingnames
        TanksAppAdapter.INSTANCE.addInputListener(this, turnLeft, turnRight, accelerateForward, accelerateBack, reset, shoot, powerup);
        
        // These mappings are now in use and cant be used by other players
        inputs.setInUse(true);
    }

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
        TanksAppAdapter.INSTANCE.removeInputListener(this);

        inputs.setInUse(false);
    }
    
    public IPlayer getPlayer() {
        return player;
    }
    
    private boolean isListening;
    
    public void collision(PhysicsCollisionEvent event) {
        if (space == null) {
            return;
        }
        if (event.getObjectA() instanceof PowerupControl && event.getObjectB() == this) {
            player.setPowerup(EPowerup.HASTE);
        }
        else if (event.getObjectB() instanceof PowerupControl && event.getObjectA() == this) {
            player.setPowerup(EPowerup.HASTE);
        }
    }
}
