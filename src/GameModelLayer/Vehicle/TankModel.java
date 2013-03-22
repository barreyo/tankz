/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModelLayer.Vehicle;

import com.jme3.math.Vector3f;

/**
 *
 * @author Daniel
 */
public class TankModel implements IVehicleModel {
    
    private int health;
    private VehicleState vehicleState;
    
    private Vector3f direction;
    private Vector3f position;
    
    private float accelerationForce;
    private float brakeForce;
    private float steeringValue;
    private float accelerationValue;
    

        /*
     * @inheritdoc
     */
    @Override
    public int getHealth() {
        return health;
    }

     /*
     * @inheritdoc
     */
    @Override
    public Vector3f getDirection() {
        return direction;
    }

     /*
     * @inheritdoc
     */
    @Override
    public Vector3f getPosition() {
        return position;
    }

     /*
     * @inheritdoc
     */
    @Override
    public VehicleState getVehicleState() {
        return vehicleState;
    }

     /*
     * @inheritdoc
     */
    @Override
    public float getAccelerationForce() {
        return accelerationForce;
    }

     /*
     * @inheritdoc
     */
    @Override
    public float getBrakeForce() {
        return brakeForce;
    }

     /*
     * @inheritdoc
     */
    @Override
    public float getSteeringValue() {
        return steeringValue;
    }

     /*
     * @inheritdoc
     */
    @Override
    public float getAccelerationValue() {
        return accelerationValue;
    }

     /*
     * @inheritdoc
     */
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

     /*
     * @inheritdoc
     */
    @Override
    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }

     /*
     * @inheritdoc
     */
    @Override
    public void setPosition(Vector3f position) {
        this.position = position;
    }

     /*
     * @inheritdoc
     */
    @Override
    public void setVehicleState(VehicleState state) {
        this.vehicleState = state;
    }

    /*
     * @inheritdoc
     */
    @Override
    public void setAccelerationForce(float force) {
        this.accelerationForce = force;
    }

     /*
     * @inheritdoc
     */
    @Override
    public void setBrakeForce(float force) {
        this.brakeForce = force;
    }

     /*
     * @inheritdoc
     */
    @Override
    public void setSteeringValue(float value) {
        this.steeringValue = value;
    }

     /*
     * @inheritdoc
     */
    @Override
    public void setAccelerationValue(float value) {
        this.accelerationValue = value;
    }

    /*
     * @inheritdoc
     */
    @Override
    public void incrementAccelerationValue(float force) {
        this.accelerationValue += force;
    }

    /*
     * @inheritdoc
     */
    @Override
    public void decrementAccelerationValue(float force) {
        this.accelerationValue -= force;
    }

    /*
     * @inheritdoc
     */
    @Override
    public void incrementSteeringValue(float value) {
        this.steeringValue += value;
    }

    /*
     * @inheritdoc
     */
    @Override
    public void decrementSteeringValue(float value) {
        this.steeringValue -= value;
    }
    
}
