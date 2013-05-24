/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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
    
    Player instance;
    
    public PlayerTest() {
    }
    
    
    @Before
    public void setUp() {
        instance = new Player("test player", new TankModel(
            new ArrayList<CannonBallModel>(), new ArrayList<MissileModel>(),
            new ArrayList<LandmineModel>(),new ArrayList<AtomicBombModel>()));
    }

    /**
     * Test of getKills method, of class Player.
     */
    @Test
    public void testGetKills() {
        System.out.println("getKills");
        int expResult = 0;
        int result = instance.getKills();
        assertEquals(expResult, result);
    }

    /**
     * Test of incrementKills method, of class Player.
     */
    @Test
    public void testIncrementKills() {
        System.out.println("incrementKills");
        instance.incrementKills();
        int expResult = 1;
        int result = instance.getKills();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeaths method, of class Player.
     */
    @Test
    public void testGetDeaths() {
        System.out.println("getDeaths");
        int expResult = 0;
        int result = instance.getDeaths();
        assertEquals(expResult, result);
    }

    /**
     * Test of incrementDeaths method, of class Player.
     */
    @Test
    public void testIncrementDeaths() {
        System.out.println("incrementDeaths");
        instance.incrementDeaths();
        int expResult = 1;
        int result = instance.getDeaths();
        assertEquals(expResult, result);
    }

    /**
     * Test of resetStats method, of class Player.
     */
    @Test
    public void testResetStats() {
        System.out.println("resetStats");
        instance.resetStats();
        int expDeaths = instance.getDeaths();
        int expKills = instance.getKills();
        int expResult = 0;
        assertEquals(expDeaths, expKills, expResult);
    }



    /**
     * Test of equals method, of class Player.
     */
    @Test
    public void testEqualsAndHashCode() {
        System.out.println("equals");
        Player temp = new Player("test player", new TankModel(
                new ArrayList<CannonBallModel>(), new ArrayList<MissileModel>(),
                new ArrayList<LandmineModel>(),new ArrayList<AtomicBombModel>()));
        boolean expResult = true;
        boolean result = instance.equals(temp);
        assertEquals(expResult, result);
        
        //If two object is equal, hashcode should be equal
        int tempHashCode = temp.hashCode();
        int instanceHashCode = instance.hashCode();
        assertEquals(tempHashCode, instanceHashCode);
        
        
        Player temp2 = new Player("not equal", new TankModel(
                new ArrayList<CannonBallModel>(), new ArrayList<MissileModel>(),
                new ArrayList<LandmineModel>(),new ArrayList<AtomicBombModel>()));
        boolean expResult2 = false;
        boolean result2 = instance.equals(temp2);
        assertEquals(result2, expResult2);
    }

    /**
     * Test of update method, of class Player.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        float tpf = 0.0F;
        instance.update(tpf);
        fail("Dont know how to test this, any idea?");
    }
}
