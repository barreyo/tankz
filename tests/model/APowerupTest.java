/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Garpetun
 */
public class APowerupTest {
    
    APowerup instance;
    
    @Before
    public void setUp() {
        instance = new APowerupImpl();
    }

    /**
     * Test of showInWorld method, of class APowerup.
     */
    @Test
    public void testShowInWorld() {
        System.out.println("showInWorld");
        instance.showInWorld();
        
        assertTrue(instance.isShownInWorld() == true);
    }

    /**
     * Test of hideFromWorld method, of class APowerup.
     */
    @Test
    public void testHideFromWorld() {
        System.out.println("hideFromWorld");
        instance.hideFromWorld();
        
        assertTrue(instance.isShownInWorld() == false);
    }

    /**
     * Test of powerupWasPickedUp method, of class APowerup.
     */
    @Test
    public void testPlayerPickedUpPowerup() {
        System.out.println("playerPickedUpPowerup");
        instance.powerupWasPickedUp();
        
        assertTrue(instance.isHeldByPlayer() == true);
        assertTrue(instance.isShownInWorld() == false);
    }

    /**
     * Test of getMASS method, of class APowerup.
     */
    @Test
    public void testGetMass() {
        System.out.println("getMass");
        float expResult = 10F;
        float result = instance.getMass();
        
        assertTrue(expResult == result);
    }

    /**
     * Test of getPosition method, of class APowerup.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Vector3f expResult = new Vector3f(1f,1f,1f);
        instance.setPosition(expResult);
        Vector3f result = instance.getPosition();
        
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of setPosition method, of class APowerup.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Vector3f position = new Vector3f(1f,1f,1f);
        instance.setPosition(position);
        
        assertTrue(position.equals(instance.getPosition()));
    }

    /**
     * Test of isHeldByPlayer method, of class APowerup.
     */
    @Test
    public void testIsHeldByPlayer() {
        System.out.println("isHeldByPlayer");
        boolean expResult = false;
        boolean result = instance.isHeldByPlayer();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of isShownInWorld method, of class APowerup.
     */
    @Test
    public void testIsInWorld() {
        System.out.println("isInWorld");
        instance.showInWorld();
        boolean expResult = true;
        boolean result = instance.isShownInWorld();
        
        assertTrue(expResult == result);
    }

    /**
     * Test of setHeldByPlayer method, of class APowerup.
     */
    @Test
    public void testSetHeldByPlayer() {
        System.out.println("setHeldByPlayer");
        boolean expResult = false;
        instance.setHeldByPlayer(expResult);
        
        assertTrue(instance.isHeldByPlayer() == expResult);
    }
    
    /**
     * Test of usePowerup method, of class APowerup.
     */
    @Test
    public void testUsePowerup() {
        System.out.println("usePowerup");
        IPlayer player = null;
        instance.usePowerup(player);
        
        assertTrue(instance.isHeldByPlayer() == false);
    }
    
    /**
     * Test of getMass method, of class APowerup.
     */
    @Test
    public void testGetRotation() {
        System.out.println("getRotation");
        assertTrue(instance.getRotation().equals(Quaternion.DIRECTION_Z));
    }
    
    public class APowerupImpl extends APowerup {   
    }
}
