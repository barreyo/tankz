package GameModel;

import GameUtilities.IObservable;

/**
 *
 * @author Daniel
 */
public interface IWorldObject extends IObservable {
    void showInWorld();
    void hideFromWorld();
    boolean isInWorld();
    void update(float tpf);
    void cleanup();
}
