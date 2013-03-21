/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Vehicle;

import com.jme3.math.Vector3f;

/**
 *
 * @author Daniel
 */
public abstract class AVehicle {
    ;
    protected abstract int getHealth();
    protected abstract Vector3f getDirection();
    protected abstract Vector3f getPosition();
    protected abstract VehicleState getVehicleState();
    protected abstract float getAccelerationForce();
    protected abstract float getBrakeForce();
    protected abstract float getSteeringValue();
    protected abstract float getAccelerationValue();
    
    protected abstract void setHealth(int health);
    protected abstract void setDirection(Vector3f direction);
    protected abstract void setPosition(Vector3f position);
    protected abstract void setVehicleState(VehicleState state);
    protected abstract void setAccelerationForce(float force);
    protected abstract void setBrakeForce(float force);
    protected abstract void setSteeringValue(float value);
    protected abstract void setAccelerationValue(float value);
    
    
    
    protected enum VehicleState {
        
        
    }
}
