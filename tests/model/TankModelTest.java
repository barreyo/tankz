
package model;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import model.IArmedVehicle.VehicleState;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Bäckström (Bex)
 */
public class TankModelTest {
    
    private TankModel instance;
    
    public TankModelTest() {
    }

    @Before
    public void setUp() {
        instance = new TankModel(new ArrayList<CannonBallModel>(), 
                new ArrayList<MissileModel>(), new ArrayList<LandmineModel>(),
                new ArrayList<AtomicBombModel>());
    }

    /**
     * Test of getVehicleState method, of class TankModel.
     */
    @Test
    public void testGetVehicleState() {
        System.out.println("getVehicleState");
        instance.showInWorld();
        VehicleState expResult = VehicleState.ALIVE;
        VehicleState result = instance.getVehicleState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMass method, of class TankModel.
     */
    @Test
    public void testGetMass() {
        System.out.println("getMass");
        float expResult = 600.0f;
        float result = instance.getMass();
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of getFirePosition method, of class TankModel.
     */
    @Test
    public void testGetFirePosition() {
        System.out.println("getFirePosition");
        Vector3f expResult = Vector3f.ZERO;
        Vector3f result = instance.getFirePosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExhaustPosition method, of class TankModel.
     */
    @Test
    public void testGetExhaustPosition() {
        System.out.println("getExhaustPosition");
        Vector3f expResult = Vector3f.ZERO;
        Vector3f result = instance.getExhaustPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateDirection method, of class TankModel.
     */
    @Test
    public void testUpdateDirection() {
        System.out.println("updateDirection");
        Vector3f forwardVector = Vector3f.UNIT_X;
        instance.updateDirection(forwardVector);
        Vector3f newVector = instance.getDirection();
        assertEquals(forwardVector, newVector);
    }

    /**
     * Test of updateRotation method, of class TankModel.
     */
    @Test
    public void testUpdateRotation() {
        System.out.println("updateRotation");
        Quaternion rotation = Quaternion.DIRECTION_Z;
        instance.updateRotation(rotation);
        assertEquals(rotation, instance.getRotation());
    }

    /**
     * Test of setMaxSpeed method, of class TankModel.
     */
    @Test
    public void testSetMaxSpeed() {
        System.out.println("setMaxSpeed");
        float maxSpeed = 20.0f;
        instance.setMaxSpeed(maxSpeed);
        assertEquals(maxSpeed, instance.getMaxSpeed(), 0.0001);
    }

    /**
     * Test of getDefaultMaxSpeed method, of class TankModel.
     */
    @Test
    public void testGetDefaultMaxSpeed() {
        System.out.println("getDefaultMaxSpeed");
        float expResult = 80.0f;
        float result = instance.getDefaultMaxSpeed();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of setPosition method, of class TankModel.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Vector3f position = Vector3f.UNIT_X;
        instance.setPosition(position);
        assertEquals(position, instance.getPosition());
    }

    /**
     * Test of showInWorld method, of class TankModel.
     */
    @Test
    public void testShowInWorld() {
        System.out.println("showInWorld");
        instance.showInWorld();
        assertTrue(instance.isShownInWorld());
    }

    /**
     * Test of hideFromWorld method, of class TankModel.
     */
    @Test
    public void testHideFromWorld() {
        System.out.println("hideFromWorld");
        instance.hideFromWorld();
        assertFalse(instance.isShownInWorld());
    }

    /**
     * Test of resetSpeedValues method, of class TankModel.
     */
    @Test
    public void testResetSpeedValues() {
        System.out.println("resetSpeedValues");
        float defaultSpeed = instance.getDefaultMaxSpeed();
        instance.setMaxSpeed(10.0f);
        instance.resetSpeedValues();
        assertEquals(defaultSpeed, instance.getMaxSpeed(), 0.0001);
    }
}
