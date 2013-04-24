/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import GameModel.IArmedVehicle.VehicleState;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Per
 */
public class TankModelTest {
    TankModel instance;
    
    public TankModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new TankModel(new ArrayList<CanonBallModel>(), new ArrayList<MissileModel>());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getHealth method, of class TankModel.
     */
    @Test
    public void testGetHealth() {
        System.out.println("getHealth");
        int expResult = 100;
        int result = instance.getHealth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVehicleState method, of class TankModel.
     */
    @Test
    public void testGetVehicleState() {
        System.out.println("getVehicleState");
        TankModel instance = null;
        VehicleState expResult = null;
        VehicleState result = instance.getVehicleState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccelerationForce method, of class TankModel.
     */
    @Test
    public void testGetAccelerationForce() {
        System.out.println("getAccelerationForce");
        TankModel instance = null;
        float expResult = 0.0F;
        float result = instance.getAccelerationForce();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBrakeForce method, of class TankModel.
     */
    @Test
    public void testGetBrakeForce() {
        System.out.println("getBrakeForce");
        TankModel instance = null;
        float expResult = 0.0F;
        float result = instance.getBrakeForce();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSteeringValue method, of class TankModel.
     */
    @Test
    public void testGetSteeringValue() {
        System.out.println("getSteeringValue");
        TankModel instance = null;
        float expResult = 0.0F;
        float result = instance.getSteeringValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccelerationValue method, of class TankModel.
     */
    @Test
    public void testGetAccelerationValue() {
        System.out.println("getAccelerationValue");
        TankModel instance = null;
        float expResult = 0.0F;
        float result = instance.getAccelerationValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decrementHealth method, of class TankModel.
     */
    @Test
    public void testDecrementHealth() {
        System.out.println("decrementHealth");
        int hp = 0;
        TankModel instance = null;
        instance.decrementHealth(hp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shoot method, of class TankModel.
     */
    @Test
    public void testShoot() {
        System.out.println("shoot");
        TankModel instance = null;
        instance.shoot();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shootMissile method, of class TankModel.
     */
    @Test
    public void testShootMissile() {
        System.out.println("shootMissile");
        TankModel instance = null;
        instance.shootMissile();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addObserver method, of class TankModel.
     */
    @Test
    public void testAddObserver() {
        System.out.println("addObserver");
        PropertyChangeListener l = null;
        TankModel instance = null;
        instance.addObserver(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeObserver method, of class TankModel.
     */
    @Test
    public void testRemoveObserver() {
        System.out.println("removeObserver");
        PropertyChangeListener l = null;
        TankModel instance = null;
        instance.removeObserver(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class TankModel.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        float tpf = 0.0F;
        TankModel instance = null;
        instance.update(tpf);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accelerateForward method, of class TankModel.
     */
    @Test
    public void testAccelerateForward() {
        System.out.println("accelerateForward");
        TankModel instance = null;
        instance.accelerateForward();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accelerateBack method, of class TankModel.
     */
    @Test
    public void testAccelerateBack() {
        System.out.println("accelerateBack");
        TankModel instance = null;
        instance.accelerateBack();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of steerLeft method, of class TankModel.
     */
    @Test
    public void testSteerLeft() {
        System.out.println("steerLeft");
        TankModel instance = null;
        instance.steerLeft();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of steerRight method, of class TankModel.
     */
    @Test
    public void testSteerRight() {
        System.out.println("steerRight");
        TankModel instance = null;
        instance.steerRight();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFrictionForce method, of class TankModel.
     */
    @Test
    public void testGetFrictionForce() {
        System.out.println("getFrictionForce");
        TankModel instance = null;
        float expResult = 0.0F;
        float result = instance.getFrictionForce();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCurrentVehicleSpeedKmHour method, of class TankModel.
     */
    @Test
    public void testUpdateCurrentVehicleSpeedKmHour() {
        System.out.println("updateCurrentVehicleSpeedKmHour");
        float currentVehicleSpeedKmHour = 0.0F;
        TankModel instance = null;
        instance.updateCurrentVehicleSpeedKmHour(currentVehicleSpeedKmHour);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMass method, of class TankModel.
     */
    @Test
    public void testGetMass() {
        System.out.println("getMass");
        TankModel instance = null;
        float expResult = 0.0F;
        float result = instance.getMass();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePosition method, of class TankModel.
     */
    @Test
    public void testUpdatePosition() {
        System.out.println("updatePosition");
        Vector3f pos = null;
        TankModel instance = null;
        instance.updatePosition(pos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirePosition method, of class TankModel.
     */
    @Test
    public void testGetFirePosition() {
        System.out.println("getFirePosition");
        TankModel instance = null;
        Vector3f expResult = null;
        Vector3f result = instance.getFirePosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSmokePosition method, of class TankModel.
     */
    @Test
    public void testGetSmokePosition() {
        System.out.println("getSmokePosition");
        TankModel instance = null;
        Vector3f expResult = null;
        Vector3f result = instance.getSmokePosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDirection method, of class TankModel.
     */
    @Test
    public void testUpdateDirection() {
        System.out.println("updateDirection");
        Vector3f forwardVector = null;
        TankModel instance = null;
        instance.updateDirection(forwardVector);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirection method, of class TankModel.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        TankModel instance = null;
        Vector3f expResult = null;
        Vector3f result = instance.getDirection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRotation method, of class TankModel.
     */
    @Test
    public void testGetRotation() {
        System.out.println("getRotation");
        TankModel instance = null;
        Quaternion expResult = null;
        Quaternion result = instance.getRotation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRotation method, of class TankModel.
     */
    @Test
    public void testUpdateRotation() {
        System.out.println("updateRotation");
        Quaternion rotation = null;
        TankModel instance = null;
        instance.updateRotation(rotation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of applyFriction method, of class TankModel.
     */
    @Test
    public void testApplyFriction() {
        System.out.println("applyFriction");
        TankModel instance = null;
        instance.applyFriction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaxSpeed method, of class TankModel.
     */
    @Test
    public void testSetMaxSpeed() {
        System.out.println("setMaxSpeed");
        float maxSpeed = 0.0F;
        TankModel instance = null;
        instance.setMaxSpeed(maxSpeed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccelerationForce method, of class TankModel.
     */
    @Test
    public void testSetAccelerationForce() {
        System.out.println("setAccelerationForce");
        float accelerationForce = 0.0F;
        TankModel instance = null;
        instance.setAccelerationForce(accelerationForce);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxSpeed method, of class TankModel.
     */
    @Test
    public void testGetMaxSpeed() {
        System.out.println("getMaxSpeed");
        TankModel instance = null;
        float expResult = 0.0F;
        float result = instance.getMaxSpeed();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gotHitBy method, of class TankModel.
     */
    @Test
    public void testGotHitBy() {
        System.out.println("gotHitBy");
        IExplodingProjectile projectile = null;
        TankModel instance = null;
        instance.gotHitBy(projectile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanup method, of class TankModel.
     */
    @Test
    public void testCleanup() {
        System.out.println("cleanup");
        TankModel instance = null;
        instance.cleanup();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosition method, of class TankModel.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Vector3f position = null;
        TankModel instance = null;
        instance.setPosition(position);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showInWorld method, of class TankModel.
     */
    @Test
    public void testShowInWorld() {
        System.out.println("showInWorld");
        TankModel instance = null;
        instance.showInWorld();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hideFromWorld method, of class TankModel.
     */
    @Test
    public void testHideFromWorld() {
        System.out.println("hideFromWorld");
        TankModel instance = null;
        instance.hideFromWorld();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosition method, of class TankModel.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        TankModel instance = null;
        Vector3f expResult = null;
        Vector3f result = instance.getPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInWorld method, of class TankModel.
     */
    @Test
    public void testIsInWorld() {
        System.out.println("isInWorld");
        TankModel instance = null;
        boolean expResult = false;
        boolean result = instance.isInWorld();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetSpeedValues method, of class TankModel.
     */
    @Test
    public void testResetSpeedValues() {
        System.out.println("resetSpeedValues");
        TankModel instance = null;
        instance.resetSpeedValues();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class TankModel.
     */
    @Test
    public void testWrite() throws Exception {
        System.out.println("write");
        JmeExporter ex = null;
        TankModel instance = null;
        instance.write(ex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class TankModel.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        JmeImporter im = null;
        TankModel instance = null;
        instance.read(im);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
