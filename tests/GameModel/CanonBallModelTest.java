/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import model.CanonBallModel;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author backman
 */
public class CanonBallModelTest {
    
    private static CanonBallModel cbModel;
/*    
    @BeforeClass
    public static void setUpClass() {
        cbModel = new CanonBallModel(new Vector3f(1, 1, 1), new Vector3f(3,3,3), new Quaternion(1,2,3,4));
    }
*/    
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
     * Test of getDamageOnImpact method, of class CanonBallModel.
     */
    @Test
    public void testGetDamageOnImpact() {
        System.out.println("getDamageOnImpact");
        assertTrue(cbModel.getDamageOnImpact() == 10);
    }

    /**
     * Test of getMass method, of class CanonBallModel.
     */
    @Test
    public void testGetMass() {
        System.out.println("getMass");
        assertTrue(cbModel.getMass() == 0.1f);
    }
}
