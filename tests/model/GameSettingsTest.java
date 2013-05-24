
package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Game settings test.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class GameSettingsTest {
    
    private GameSettings instance;
    
    @Before
    public void setUp() {
        instance = new GameSettings(30000, 10, 1000);
    }
    
    /**
     * Test of getGameEndTimeMS method, of class GameSettings.
     */
    @Test
    public void testGetGameEndTimeMS() {
        System.out.println("getGameEndTimeMS");
        long expResult = 30000;
        long result = instance.getGameEndTimeMS();
        assertEquals(expResult, result);
        
        instance = new GameSettings(-30000, -10, -1000);
        result = instance.getGameEndTimeMS();
        assertEquals(expResult, result);
    }

    /**
     * Test of getKillsToWin method, of class GameSettings.
     */
    @Test
    public void testGetKillsToWin() {
        System.out.println("getKillsToWin");
        int expResult = 10;
        int result = instance.getKillsToWin();
        assertEquals(expResult, result);
        
        instance = new GameSettings(-30000, -10, -1000);
        result = instance.getKillsToWin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPowerupSpawningIntervallMS method, of class GameSettings.
     */
    @Test
    public void testGetPowerupSpawningIntervallMS() {
        System.out.println("getPowerupSpawningIntervallMS");
        long expResult = 1000;
        long result = instance.getPowerupSpawningIntervallMS();
        assertEquals(expResult, result);
        
        instance = new GameSettings(-30000, -10, -1000);
        result = instance.getPowerupSpawningIntervallMS();
        assertEquals(expResult, result);
    }
}
