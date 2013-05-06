
package GameView.effects;

import App.TanksAppAdapter;
import GameModel.IArmedVehicle;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author Johan Backman
 */
public class SkidmarkBuffer {
    
    private Deque<TireTrack> buffer;
    private IArmedVehicle vehicle;
    private final static int LENGTH = 20;
    
    public SkidmarkBuffer(IArmedVehicle vehicle, String texture) {
        this.vehicle = vehicle;
        buffer = new ArrayDeque<TireTrack>();
        for (int i = 0; i < LENGTH; i++) {
            buffer.add(new TireTrack());
            
            //TanksAppAdapter.INSTANCE.attachChildToRootNode();
        }
    }
    
    public void updateSkidmarkPosition() {
        
    }
}
