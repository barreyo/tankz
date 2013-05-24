
package model;

import com.jme3.math.Vector3f;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class SpawningPointTest {
    
    private SpawningPoint sp1, sp2;
    
    public SpawningPointTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        sp1 = new SpawningPoint(Vector3f.ZERO);
        sp2 = new SpawningPoint(new Vector3f(1, 3, 37));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setOccupied method, of class SpawningPoint.
     */
    @Test
    public void testSetOccupied() {
        System.out.println("setOccupied");
        sp1.setOccupied(true);
        boolean expResult = true;
        boolean result = sp1.isOccupied();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition method, of class SpawningPoint.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Vector3f expResult = Vector3f.ZERO;
        Vector3f result = sp1.getPosition();
        assertEquals(expResult, result);
        
        expResult = new Vector3f(1, 3, 37);
        result = sp2.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class SpawningPoint.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        
        sp1 = new SpawningPoint(new Vector3f(1, 3, 37));
        
        int sp1Hash = sp1.hashCode();
        int sp2Hash = sp2.hashCode();
        assertEquals(sp1.equals(sp2), sp1Hash == sp2Hash);
    }

    /**
     * Test of equals method, of class SpawningPoint.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        assertTrue(!sp1.equals(sp2));
    }

    /**
     * Test of getOccupier method, of class SpawningPoint.
     */
    @Test
    public void testGetOccupier() {
        System.out.println("getOccupier");
        IWorldObject expResult = null;
        IWorldObject result = sp1.getOccupier();
        assertEquals(expResult, result);
    }
}
