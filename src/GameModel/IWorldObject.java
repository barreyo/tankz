package GameModel;

import GameUtilities.IObservable;
import com.jme3.export.Savable;

/**
 *
 * @author Daniel
 */
public interface IWorldObject extends IObservable, Savable{
    /**
     * 
     */
    public void showInWorld();
    
    /**
     * 
     */
    public void hideFromWorld();
    /**
     *
     * @return
     */
    public boolean isInWorld();
    /**
     *
     * @param tpf
     */
    public void update(float tpf);
    /**
     *
     */
    public void cleanup();
}
