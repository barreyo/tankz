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

    private final List<IExplodingProjectile> bombs;
    private long activateTimerStart;
    private boolean isActive;
    
    private static final long END_TIME = 15000;
    private int bombCount;
    private static final float INTERVAL = 1.2f;
    
    private static final int DROP_HEIGHT = 40;
    
    private float counter;
    
    private IPlayer player;
//    private IArmedVehicle vehicle;
    
    public AirCallPowerup(List<IExplodingProjectile> bombs) {
         this.bombs = bombs;
         bombCount = bombs.size();
    }

    @Override
    public void usePowerup(IPlayer player) {
        super.usePowerup(player);
        this.player = player;
        
        activateTimerStart = System.currentTimeMillis();
        isActive = true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (isActive) {
            counter = counter + tpf;
            
            if (counter >= INTERVAL) {
                dropBomb();
                counter = 0;
            }
            if (System.currentTimeMillis() - activateTimerStart >= END_TIME || bombCount <= 0) {
                isActive = false;
            }
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

    private void dropBomb() {
        float zRandom = (float) ((Math.random() * 208) - 34);
        float xRandom = (float) ((Math.random() * 256) - 121);
        
        Vector3f initPos = new Vector3f(xRandom, DROP_HEIGHT, zRandom);
        for (int i = 0; i < bombs.size(); i++) {
            if (!bombs.get(i).isShownInWorld()) {
                
                bombs.get(i).launchProjectile(initPos, new Vector3f(0, -1, 0).multLocal(80), Quaternion.ZERO, player);
                bombCount--;
                return; 
            }
        }
    }
}
