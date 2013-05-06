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
}
