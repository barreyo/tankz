package GameUtilities;

import com.jme3.math.Vector3f;

/**
 *
 * @author Daniel
 */
public final class Constants {
    private Constants() {}
    
    // This is physics-related information about the Tank
    /**
     *
     */
    public static final float TANK_STIFFNESS = 80.0f;//200=f1 car
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
    public static final float TANK_MAX_BACK_SPEED = 30.0f;
    /**
     *
     */
    public static final float TANK_FRICTION_FORCE = 10.0f;
    /**
     *
     */
    public static final float TANK_MAX_SUSPENSION_FORCE = 999000.0f;
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
    
    public static final int LANDMINES_PER_PLAYER = 10;
    public static final int MISSILES_PER_PLAYER = 5;
    public static final int CANNONBALLS_PER_PLAYER = 10;
    public static final int CANNONBALLS_IN_AIRCALL = 100;
    public static final float CAM_MAX_DISTANCE = 25f;
    public static final float CAM_MIN_DISTANCE = 15f;
    public static final float CAM_DEFAULT_DISTANCE = 20f;
    public static final float CAM_CHASING_SENSITIVITY = 50f;
    public static final float CAM_DEFAULT_VERTICAL_ROTATION = 0.3f;
    
    
    public static final String RIGHT_FRONT_WHEEL_MODEL_NAME = "WheelFrontRight";
    public static final String LEFT_FRONT_WHEEL_MODEL_NAME = "WheelFrontLeft";
    public static final String RIGHT_BACK_WHEEL_MODEL_NAME = "WheelBackRight";
    public static final String LEFT_BACK_WHEEL_MODEL_NAME = "WheelBackLeft";
}
