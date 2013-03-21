/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Vehicle;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;

/**
 *
 * @author Daniel
 */
public abstract class AVehicle implements ActionListener {
    
    private InputManager inputManager;
    
    public AVehicle(InputManager inputManager) {
        this.inputManager = inputManager;
        setupKeys();
    }
    
    public abstract int getHealth();
    public abstract Vector3f getDirection();
    public abstract Vector3f getPosition();
    public abstract VehicleState getVehicleState();
    public abstract float getAccelerationForce();
    public abstract float getBrakeForce();
    public abstract float getSteeringValue();
    public abstract float getAccelerationValue();
    
    public abstract void setHealth(int health);
    public abstract void setDirection(Vector3f direction);
    public abstract void setPosition(Vector3f position);
    public abstract void setVehicleState(VehicleState state);
    public abstract void setAccelerationForce(float force);
    public abstract void setBrakeForce(float force);
    public abstract void setSteeringValue(float value);
    public abstract void setAccelerationValue(float value);
    
    private void setupKeys() {
        inputManager.addMapping("Lefts", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("Rights", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("Ups", new KeyTrigger(KeyInput.KEY_U));
        inputManager.addMapping("Downs", new KeyTrigger(KeyInput.KEY_J));
        //inputManager.addMapping("Space", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("Reset", new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addListener(this, "Lefts");
        inputManager.addListener(this, "Rights");
        inputManager.addListener(this, "Ups");
        inputManager.addListener(this, "Downs");
        //inputManager.addListener(this, "Space");
        inputManager.addListener(this, "Reset");
    }
    
    protected enum VehicleState {
        MOVING, IDLE, DESTROYED;
    }
}
