/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

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
 * @author perthoresson
 */
public class PlayerTest {
    Player player;
    
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        player = new Player("Spelare 1", new TankModel
                (new ArrayList<CanonBallModel>(), new ArrayList<MissileModel>()));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Spelare 1";
        String result = player.getName();
        assertEquals(expResult, result);
    }

    /**
     * WTF!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    @Test
    public void testGetVehicle() {
        /*System.out.println("getVehicle");
        Player instance = null;
        IArmedVehicle expResult = null;
        IArmedVehicle result = instance.getVehicle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of getKills method, of class Player.
     */
    @Test
    public void testGetKills() {
        System.out.println("getKills");
        int expResult = 0;
        int result = player.getKills();
        assertEquals(expResult, result);
    }

    /**
     * Test of incrementKills method, of class Player.
     */
    @Test
    public void testIncrementKills() {
        System.out.println("incrementKills");
        player.incrementKills();
        int expResult = 1;
        int result = player.getKills();
        assertEquals(result, expResult);
    }

    /**
     * Test of getDeaths method, of class Player.
     */
    @Test
    public void testGetDeaths() {
        System.out.println("getDeaths");
        int expResult = 0;
        int result = player.getDeaths();
        assertEquals(expResult, result);
    }

    /**
     * Test of incrementDeaths method, of class Player.
     */
    @Test
    public void testIncrementDeaths() {
        System.out.println("incrementDeaths");
        player.incrementDeaths();
        int expResult = 1;
        int result = player.getDeaths();
        assertEquals(result, expResult);
    }

    /**
     * Test of resetStats method, of class Player.
     */
    @Test
    public void testResetStats() {
        System.out.println("resetStats");
        player.resetStats();
        int expResult = 0;
        int resultDeaths = player.getDeaths();
        int resultKills = player.getKills();
        assertEquals(resultDeaths, resultKills, expResult);
    }

    /**
     * Test of isActive method, of class Player.
     */
    @Test
    public void testIsActive() {
        System.out.println("isActive");
        boolean expResult = false;
        boolean result = player.isActive();
        assertEquals(expResult, result);
    }

    /**
     * Test of activatePlayer method, of class Player.
     */
    @Test
    public void testActivatePlayer() {
        System.out.println("activatePlayer");
        player.activatePlayer();
        boolean expResult = true;
        boolean result = player.isActive();
        assertEquals(result, expResult);
    }

    /**
     * Test of deactivatePlayer method, of class Player.
     */
    @Test
    public void testDeactivatePlayer() {
        System.out.println("deactivatePlayer");
        player.deactivatePlayer();
        boolean expResult = false;
        boolean result = player.isActive();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPowerup method, of class Player.
     */
    @Test
    public void testGetPowerup() {
        System.out.println("getPowerup");
        IPowerup expResult = new HastePowerup();
        player.setPowerup(expResult);
        IPowerup result = player.getPowerup();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPowerup method, of class Player.
     */
    @Test
    public void testSetPowerup() {
        System.out.println("setPowerup");
        IPowerup expResult = new MissilePowerup();
        player.setPowerup(expResult);
        IPowerup result = player.getPowerup();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of usePowerup method, of class Player.
     */
    @Test
    public void testUsePowerup() {
        System.out.println("usePowerup");
        player.setPowerup(new HastePowerup());
        player.usePowerup();
        IPowerup expResult = null;
        IPowerup result = player.getPowerup();
        assertEquals(expResult, result);
    }
}