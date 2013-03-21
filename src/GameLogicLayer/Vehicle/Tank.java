/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.Vehicle;

import GameLogicLayer.Vehicle.AVehicle;
import com.jme3.math.Vector3f;

/**
 *
 * @author Daniel
 */
public class Tank extends AVehicle {
    private int health;
    private Vector3f direction;
    private Vector3f position;
    
    private VehicleState vehicleState;
    
    private float accelerationForce;
    private float brakeForce;
    private float steeringValue;
    private float accelerationValue;

    @Override
    protected int getHealth() {
        return health;
    }

    @Override
    protected Vector3f getDirection() {
        return direction;
    }

    @Override
    protected Vector3f getPosition() {
        return position;
    }

    @Override
    protected VehicleState getVehicleState() {
        return vehicleState;
    }

    @Override
    protected float getAccelerationForce() {
        return accelerationForce;
    }

    @Override
    protected float getBrakeForce() {
        return brakeForce;
    }

    @Override
    protected float getSteeringValue() {
        return steeringValue;
    }

    @Override
    protected float getAccelerationValue() {
        return accelerationValue;
    }

    @Override
    protected void setHealth(int health) {
        this.health = health;
    }

    @Override
    protected void setDirection(Vector3f direction) {
        this.direction = direction;
    }

    @Override
    protected void setPosition(Vector3f position) {
        this.position = position;
    }

    @Override
    protected void setVehicleState(VehicleState state) {
        this.vehicleState = state;
    }

    @Override
    protected void setAccelerationForce(float force) {
        this.accelerationForce = force;
    }

    @Override
    protected void setBrakeForce(float force) {
        this.brakeForce = force;
    }

    @Override
    protected void setSteeringValue(float value) {
        this.steeringValue = value;
    }

    @Override
    protected void setAccelerationValue(float value) {
        this.accelerationValue = value;
    }
    
}