/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModelLayer.gameEntity.Vehicle;

import GameModelLayer.gameEntity.Powerup.EPowerup;
import GameModelLayer.gameEntity.Powerup.PowerupSlot;
import GameModelLayer.gameEntity.Weapon.IWeapon;
import com.jme3.math.Vector3f;

/**
 *
 * @author Daniel
 */
public class TankModel implements IArmedVehicle {

    private int health;
    private VehicleState vehicleState;
    
    private float accelerationForce;
    private float brakeForce;
    private float steeringValue;
    private float accelerationValue;
    private float maxForwardSpeed;
    private float maxBackSpeed;
    
    private PowerupSlot powerupSlot;
    private IWeapon weaponModel;

    /**
     * @inheritdoc
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * @inheritdoc
     */
    @Override
    public VehicleState getVehicleState() {
        return vehicleState;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getAccelerationForce() {
        return accelerationForce;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getBrakeForce() {
        return brakeForce;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getSteeringValue() {
        return steeringValue;
    }

    /**
     * @inheritdoc
     */
    @Override
    public float getAccelerationValue() {
        return accelerationValue;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setVehicleState(VehicleState state) {
        this.vehicleState = state;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setAccelerationForce(float force) {
        this.accelerationForce = force;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setBrakeForce(float force) {
        this.brakeForce = force;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setSteeringValue(float value) {
        this.steeringValue = value;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setAccelerationValue(float value) {
        this.accelerationValue = value;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void incrementAccelerationValue(float force) {
        this.accelerationValue += force;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void decrementAccelerationValue(float force) {
        this.accelerationValue -= force;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void incrementSteeringValue(float value) {
        this.steeringValue += value;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void decrementSteeringValue(float value) {
        this.steeringValue -= value;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setWeaponModel(IWeapon model) {
        weaponModel = model;
    }

    /**
     * @inheritdoc
     */
    @Override
    public IWeapon getWeaponModel() {
        return weaponModel;
    }

    /**
     * @inheritdoc
     */
    @Override
    public EPowerup getPowerup() {
        return powerupSlot.getPowerup();
    }

    /**
     * @inheritdoc
     */
    @Override
    public void setPowerup(EPowerup powerup) {
        powerupSlot.setPowerup(powerup);
    }

    @Override
    public void setForwardMaxSpeed(float max) {
        this.maxForwardSpeed = max;
    }

    @Override
    public float getForwardMaxSpeed() {
        return maxForwardSpeed;
    }

    @Override
    public float getBackMaxSpeed() {
        return maxBackSpeed;
    }

    @Override
    public void setBackMaxSpeed(float max) {
        this.maxBackSpeed = max;
    }
}
