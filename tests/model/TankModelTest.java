/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import model.IArmedVehicle.VehicleState;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author perthoresson
 */
public class TankModelTest {
    
    public TankModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        TankModel tank = new TankModel(new ArrayList<CanonBallModel>(),
                new ArrayList<MissileModel>(), new ArrayList<LandmineModel>(),
                new ArrayList<AtomicBombModel>());
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
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
     * Test of applyDamageToKill method, of class TankModel.
     */
    @Test
    public void testApplyDamageToKill() {
        System.out.println("applyDamageToKill");
        int hp = 0;
        TankModel instance = null;
        boolean expResult = false;
        boolean result = instance.applyDamageToKill(hp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shoot method, of class TankModel.
     */
    @Test
    public void testShoot() {
        System.out.println("shoot");
        IPlayer player = null;
        TankModel instance = null;
        instance.shoot(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shootMissile method, of class TankModel.
     */
    @Test
    public void testShootMissile() {
        System.out.println("shootMissile");
        IPlayer player = null;
        TankModel instance = null;
        instance.shootMissile(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dropLandmine method, of class TankModel.
     */
    @Test
    public void testDropLandmine() {
        System.out.println("dropLandmine");
        IPlayer player = null;
        TankModel instance = null;
        instance.dropLandmine(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dropBomb method, of class TankModel.
     */
    @Test
    public void testDropBomb() {
        System.out.println("dropBomb");
        IPlayer player = null;
        TankModel instance = null;
        instance.dropBomb(player);
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
     * Test of getExhaustPosition method, of class TankModel.
     */
    @Test
    public void testGetExhaustPosition() {
        System.out.println("getExhaustPosition");
        TankModel instance = null;
        Vector3f expResult = null;
        Vector3f result = instance.getExhaustPosition();
        assertEquals(expResult, result);
        //Dont know how to test this yet.
        fail("Not implemented yet, dunno how");
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
     * Test of getDefaultMaxSpeed method, of class TankModel.
     */
    @Test
    public void testGetDefaultMaxSpeed() {
        System.out.println("getDefaultMaxSpeed");
        TankModel instance = null;
        float expResult = 0.0F;
        float result = instance.getDefaultMaxSpeed();
        assertEquals(expResult, result, 0.0);
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
     * Test of isShownInWorld method, of class TankModel.
     */
    @Test
    public void testIsShownInWorld() {
        System.out.println("isShownInWorld");
        TankModel instance = null;
        boolean expResult = false;
        boolean result = instance.isShownInWorld();
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

    /**
     * Test of toggleFlame method, of class TankModel.
     */
    @Test
    public void testToggleFlame() {
        System.out.println("toggleFlame");
        boolean state = false;
        TankModel instance = null;
        instance.toggleFlame(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toggleSmoke method, of class TankModel.
     */
    @Test
    public void testToggleSmoke() {
        System.out.println("toggleSmoke");
        boolean state = false;
        TankModel instance = null;
        instance.toggleSmoke(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of heal method, of class TankModel.
     */
    @Test
    public void testHeal() {
        System.out.println("heal");
        int heal = 0;
        TankModel instance = null;
        instance.heal(heal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hideAirCallRing method, of class TankModel.
     */
    @Test
    public void testHideAirCallRing() {
        System.out.println("hideAirCallRing");
        TankModel instance = null;
        instance.hideAirCallRing();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
