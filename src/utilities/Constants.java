package utilities;

import com.jme3.math.Vector3f;

/**
 * Useful constants for the Tankz game.
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public final class Constants {
    private Constants() {}
    
    /**
     * User data model constant.
     */
    public static final String USER_DATA_MODEL = "USER_DATA_MODEL";
    
    public static final String MAP_POWERUP_SPAWN_GEOM_NAME = "PowerupSpawn";
    public static final String MAP_PLAYER_SPAWN_GEOM_NAME = "PlayerSpawn";
    
    // This is physics-related information about the Tank
    /**
     * 
     */
    public static final float TANK_STIFFNESS = 100.0f;//200=f1 car
    /**
     *
     */
    public static final float TANK_COMP_VALUE = 0.2f; //(should be lower than damp)
    /**
     *
     */
    public static final float TANK_DAMP_VALUE = 0.5f; 
    /**
     *
     */
    public static final float TANK_MAX_BACK_SPEED = 34.0f;
    /**
     *
     */
    public static final float TANK_FRICTION_FORCE = 10.0f;
    /**
     *
     */
    public static final float TANK_MAX_SUSPENSION_FORCE = 9000.0f;
    /**
     *
     */
    public static final Vector3f TANK_WHEEL_DIRECTION = new Vector3f(0, -1, 0); // was 0, -1, 0
    /**
     *
     */
    public static final Vector3f TANK_WHEEL_AXIS = new Vector3f(-1, 0, 0); // was -1, 0, 0
    /**
     *
     */
    public static final float TANK_WHEEL_REST_LENGTH = 0.2f;
    /**
     *
     */
    public static final float TANK_WHEEL_Y_OFF = 0.7f;
    /**
     *
     */
    public static final float TANK_WHEEL_X_OFF = 0.9f;
    /**
     *
     */
    public static final float TANK_WHEEL_Z_OFF = 0.7f;
    
    public static final float CCD_MOTION_THRESHOLD = 0.1f;
    public static final int NUMBER_OF_EACH_POWERUP = 20;
    public static final int LANDMINES_PER_PLAYER = 30;
    public static final int MISSILES_PER_PLAYER = 5;
    public static final int CANNONBALLS_PER_PLAYER = 10;
    public static final int BOMBS_IN_AIRCALL = 20;
    public static final float NUKE_DROP_HEIGHT = 40f;
    public static final float CAM_MAX_DISTANCE = 28f;
    public static final float CAM_MIN_DISTANCE = 18f;
    public static final float CAM_DEFAULT_DISTANCE = 24f;
    public static final float CAM_CHASING_SENSITIVITY = 50f;
    public static final float CAM_DEFAULT_VERTICAL_ROTATION = 0.3f;
    
    public static final float ATOMIC_BOMB_MASS = 20f;
    public static final int ATOMIC_BOMB_DAMAGE = 20;
    public static final int ATOMIC_BOMB_EXPLOSION_END_TIME = 2000;
    public static final int ATOMIC_BOMB_LIFE_TIME = 5000;
    
    public static final float CANNON_BALL_MASS = 0.1f;
    public static final int CANNON_BALL_DAMAGE = 10;
    public static final int CANNON_BALL_EXPLOSION_END_TIME = 2000;
    public static final int CANNON_BALL_LIFE_TIME = 5000;
    
    public static final float LANDMINE_MASS = 500f;
    public static final int LANDMINE_DAMAGE = 30;
    public static final int LANDMINE_EXPLOSION_END_TIME = 2000;
    public static final int LANDMINE_LIFE_TIME = 300000;
    
    public static final float MISSILE_MASS = 0.1f;
    public static final int MISSILE_DAMAGE = 20;
    public static final int MISSILE_EXPLOSION_END_TIME = 2000;
    public static final int MISSILE_LIFE_TIME = 10000;
    
    
    public static final String RIGHT_FRONT_WHEEL_MODEL_NAME = "WheelFrontRight";
    public static final String LEFT_FRONT_WHEEL_MODEL_NAME = "WheelFrontLeft";
    public static final String RIGHT_BACK_WHEEL_MODEL_NAME = "WheelBackRight";
    public static final String LEFT_BACK_WHEEL_MODEL_NAME = "WheelBackLeft";
    
    public static final Vector3f LIGHT_DIRECTION = new Vector3f(-4.9236743f, -1.27054665f, 5.896916f);;
}
