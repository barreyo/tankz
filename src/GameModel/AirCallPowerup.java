/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author backman
 */
public class AirCallPowerup extends APowerup {

    private List<IExplodingProjectile> bombs;
    private long activateTimerStart;
    private boolean isActive;
    
    private static final long END_TIME = 10000;
    private static final long INITIAL_DELAY = 200;
    private static final long INTERVAL = 200;
    private static final int BOMB_COUNT = 10;
    
    private static final int DROP_HEIGHT = 400;
    
    private float counter;
    
    private IPlayer player;
    private IArmedVehicle vehicle;

    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        vehicle = player.getVehicle();
        
        for (int i = 0; i < BOMB_COUNT; i++) {
            bombs.add(new AtomicBombModel());
        }
        
        activateTimerStart = System.currentTimeMillis();
        isActive = true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        super.update(tpf);
        
        counter += tpf;
        
        if (counter >= INTERVAL) {
            dropBomb();
            counter = 0;
        }

    }
    
    @Override
    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean isActive() {
        return isActive;
    }

    private void dropBomb() {
        int zRandom = (int)Math.random() * 100;
        int xRandom = (int)Math.random() * 100;
        Vector3f initPos = new Vector3f(xRandom, DROP_HEIGHT, zRandom);
        
        for (int i = 0; i < bombs.size(); i++) {
            if (!bombs.get(i).isShownInWorld()) {
                bombs.get(i).launchProjectile(initPos, new Vector3f(0, -1, 0).multLocal(100), Quaternion.ZERO, player);
                return; 
            }
        }
    }
}
