
package model;

import model.HastePowerup;
import model.Player;
import model.IArmedVehicle;
import model.IPlayer;
import model.TankModel;
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
    private float tmpForce;

    @Before
    public void setUp() {
        player = new Player("TestName", new TankModel(null,null, null, null));
        vehicle = player.getVehicle();
        instance = new HastePowerup();
        System.out.println(System.currentTimeMillis());
    }
    
    /**
     * Test of usePowerup method, of class HastePowerup.
     */
    @Test
    public void testUsePowerup() {
        System.out.println("usePowerup");
        instance.usePowerup(player);
        assertTrue(vehicle.getDefaultMaxSpeed() * 3f == vehicle.getMaxSpeed());
    }

    /**
     * Test of update method, of class HastePowerup.
     * This test is not complete
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        float tpf = 1f;
        instance.usePowerup(player);
        
        for (int i = 0; i < 5; i++) {
            instance.update(tpf);
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(vehicle.getMaxSpeed());
        System.out.println(vehicle.getDefaultMaxSpeed());
        assertTrue(vehicle.getDefaultMaxSpeed() == vehicle.getMaxSpeed());
    }
}
