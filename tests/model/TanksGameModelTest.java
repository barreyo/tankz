
package model;

import com.jme3.math.Vector3f;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class TanksGameModelTest {
    
    private static List<IPlayer> players;
    private static List<IPowerup> powerups;
    private static List<ISpawningPoint> playerSpawning, puSpawning;
    private static TanksGameModel instance;
    private static GameSettings gameSettings;
    
    @BeforeClass
    public static void setUpClass() {
        players = new ArrayList<IPlayer>();
        powerups = new ArrayList<IPowerup>();
        playerSpawning = new ArrayList<ISpawningPoint>();
        puSpawning = new ArrayList<ISpawningPoint>();
        gameSettings = new GameSettings(300000, 5, 20000);
    }
    
    @Before
    public void setUp() {

        players.clear();
        powerups.clear();
        playerSpawning.clear();
        puSpawning.clear();
        
        for (int i = 0; i < 4; i++) {
            players.add(new Player("Pelle " + i, null));
            powerups.add(new HastePowerup());
            playerSpawning.add(new SpawningPoint(Vector3f.ZERO));
            puSpawning.add(new SpawningPoint(Vector3f.ZERO));
            puSpawning.get(i).setOccupier(new HastePowerup());
            puSpawning.get(i).setOccupied(true);
        }
        instance = new TanksGameModel(players, powerups, puSpawning, playerSpawning, gameSettings);
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
}
