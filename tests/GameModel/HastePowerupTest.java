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
        IPlayer player = null;
        HastePowerup instance = new HastePowerup();
        instance.usePowerup(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class HastePowerup.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        float tpf = 0.0F;
        HastePowerup instance = new HastePowerup();
        instance.update(tpf);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
