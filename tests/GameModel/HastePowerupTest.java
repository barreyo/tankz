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
    
    private IPlayer player;
    private IArmedVehicle vehicle;
    private HastePowerup instance;
    private float tmpSpeed;
    private float tmpForce;

    @Before
    public void setUp() {
        player = new Player("TestName", new TankModel(null,null));
        vehicle = player.getVehicle();
        instance = new HastePowerup();
        tmpSpeed = player.getVehicle().getMaxSpeed();
        tmpForce = player.getVehicle().getAccelerationForce();
    }
    
    /**
     * Test of usePowerup method, of class HastePowerup.
     */
    @Test
    public void testUsePowerup() {
        System.out.println("usePowerup");
        instance.usePowerup(player);
        
        assertTrue(vehicle.getMaxSpeed() == tmpSpeed * 3f);
        assertTrue(vehicle.getAccelerationForce() == tmpForce * 10f);
    }

    /**
     * Test of update method, of class HastePowerup.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        float tpf = 1f;
        instance.usePowerup(player);
        
        for (int i = 0; i < 5; i++) {
            instance.update(tpf);
        }
        
        assertTrue(vehicle.getMaxSpeed() == tmpSpeed);
        assertTrue(vehicle.getAccelerationForce() == tmpForce);
    }
}
