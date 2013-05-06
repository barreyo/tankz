/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import com.jme3.math.Vector3f;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author backman
 */
public class TanksGameModelTest {
    
    private static List<IPlayer> players;
    private static List<IPowerup> powerups;
    private static List<ISpawningPoint> playerSpawning, puSpawning;
    private static TanksGameModel instance;
    
    @BeforeClass
    public static void setUpClass() {
        players = new ArrayList<IPlayer>();
        powerups = new ArrayList<IPowerup>();
        playerSpawning = new ArrayList<ISpawningPoint>();
        puSpawning = new ArrayList<ISpawningPoint>();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {

        players.clear();
        powerups.clear();
        playerSpawning.clear();
        puSpawning.clear();
        
        for (int i = 0; i < 4; i++) {
            players.add(new Player("Pelle " + i, new TankModel(null, null)));
            powerups.add(new HastePowerup());
            playerSpawning.add(new SpawningPoint(Vector3f.ZERO));
            puSpawning.add(new SpawningPoint(Vector3f.ZERO));
            puSpawning.get(i).setOccupier(new HastePowerup());
            puSpawning.get(i).setOccupied(true);
        }
        instance = new TanksGameModel(players, powerups, puSpawning, playerSpawning, new GameSettings(20f, 2));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPlayers method, of class TanksGameModel.
     */
    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        boolean allPlayer = true;
        boolean namesRight = true;
        
        int i = 0;
        
        for(IPlayer player : players) {
            if (!(player instanceof Player)) {
                allPlayer = false;
            }
            if (!player.getName().equals("Pelle " + i)) {
                namesRight = false;
            }
            i++;
        }
        assertTrue(allPlayer);
        assertTrue(instance.getPlayers().size() == 4);
        assertTrue(namesRight);
    }

    /**
     * Test of getPowerups method, of class TanksGameModel.
     */
    @Test
    public void testGetPowerups() {
        System.out.println("getPowerups");
        boolean allHaste = true;
        for (IPowerup powerup : instance.getPowerups()) {
            if (!(powerup instanceof HastePowerup)) {
                allHaste = false;
            }
        }
        assertTrue(instance.getPowerups().size() == 4);
        assertTrue(allHaste);
    }

    /**
     * Test of getSpawningPoints method, of class TanksGameModel.
     */
    @Test
    public void testGetSpawningPoints() {
        System.out.println("getSpawningPoints");
        assertTrue(instance.getSpawningPoints().size() == 4);
    }

    /**
     * Test of cleanup method, of class TanksGameModel.
     */
    @Test
    public void testCleanup() {
        System.out.println("cleanup");
        instance.cleanup();
        
        assertTrue(players.isEmpty());
        assertTrue(powerups.isEmpty());
    }

    /**
     * Test of powerupPickedUp method, of class TanksGameModel.
     */
    @Test
    public void testPowerupPickedUp() {
        System.out.println("powerupPickedUp");
        for (ISpawningPoint sp : puSpawning) {
            instance.powerupPickedUp((IPowerup)sp.getOccupier());
        }
        boolean allEmpty = true;
        for (ISpawningPoint sp : puSpawning) {
            if (sp.isOccupied()) {
                allEmpty = false;
            }
        }
        assertTrue(allEmpty);
    }

    /**
     * Test of getGameTime method, of class TanksGameModel.
     */
    @Test
    public void testGetGameTime() {
        System.out.println("getGameTime");
        assertTrue(instance.getGameTimeLeft() == 20f);
    }
}
