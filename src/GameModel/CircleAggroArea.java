package GameModel;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import java.util.Random;

/**
 *
 * @author Daniel
 */
public class CircleAggroArea implements IAggroArea {

    private Random random = new Random();
    private float centerX;
    private float centerY;
    private float radius;

    public CircleAggroArea(float centerX, float centerY, float radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public boolean isLocationInside(Vector3f location) {
        return FastMath.sqrt(FastMath.sqr(location.getX() - centerX)
                + FastMath.sqr(location.getX() - centerY)) <= radius;
    }
}
