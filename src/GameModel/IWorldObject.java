package GameModel;

import GameUtilities.IObservable;
import com.jme3.export.Savable;

/**
 *
 * @author Daniel
 */
public interface IWorldObject extends IObservable, Savable{
    void showInWorld();
    void hideFromWorld();
    boolean isInWorld();
    void update(float tpf);
    void cleanup();
}
