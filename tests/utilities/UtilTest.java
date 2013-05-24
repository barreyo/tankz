/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import application.TanksAppAdapter;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.entity.EGraphics;

/**
 * Utilities test.
 * 
 * @author Garpetun
 */
public class UtilTest {
    
    public UtilTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of findGeom method, of class Util.
     * 100% working, but not finished. If code that is commented out works
     * this is finished. Currently they generate nullpointers since assetManager
     * is not instantiated.
     */
    @Test
    public void testFindGeom() {
        System.out.println("findGeom");
        Spatial spatial = null;
        String name = "";
        Geometry expResult = null;
        Geometry result = Util.findGeom(spatial, name);
        
        assertEquals(expResult, result);
        /*
        spatial = TanksAppAdapter.INSTANCE.loadModel(EGraphics.TANK.getPath());
        name = "Weapon";
        
        assertTrue(Util.findGeom(spatial, name) != null);
        
        Geometry testGeo = (Geometry) spatial;
        
        assertTrue(Util.findGeom(spatial, name) != testGeo &&
                   Util.findGeom(spatial, name) != null);
        */
    }
}
