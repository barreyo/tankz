/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Garpetun
 */
public class HastePowerupTest {
    
    public HastePowerupTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of usePowerup method, of class HastePowerup.
     */
    @Test
    public void testUsePowerup() {
        System.out.println("usePowerup");
        IPlayer player = new Player("TestName", new TankModel());
        float tmpSpeed = player.getVehicle().getMaxSpeed();
        float tmpForce = player.getVehicle().getAccelerationForce();
        HastePowerup instance = new HastePowerup();
        instance.usePowerup(player);
        
        assertTrue(instance.isActivated() == true);
        assertTrue(instance.getTimer() == 0);
        assertTrue(player.getVehicle().getMaxSpeed() == tmpSpeed * 3f);
        assertTrue(player.getVehicle().getAccelerationForce() == tmpForce * 10f);
    }

    /**
     * Test of update method, of class HastePowerup.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        float tpf = 1f;
        HastePowerup instance = new HastePowerup();
        IPlayer player = new Player("TestName", new TankModel());
        float tmpSpeed = player.getVehicle().getMaxSpeed();
        float tmpForce = player.getVehicle().getAccelerationForce();
        player.getVehicle().setAccelerationForce(0);
        player.getVehicle().setMaxSpeed(0);
        instance.usePowerup(player);
        
        for (int i = 0; i < 5; i++) {
            instance.update(tpf);
        }
        
        assertTrue(instance.getTimer() == 5);
        assertTrue(instance.isActive == false);
        assertTrue(player.getVehicle().getMaxSpeed() == tmpSpeed);
        assertTrue(player.getVehicle().getAccelerationForce() == tmpForce);
    }
}
